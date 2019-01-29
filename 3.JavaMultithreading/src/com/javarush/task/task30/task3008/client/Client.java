package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.net.Socket;

public class Client {
    protected Connection connection;
    private volatile boolean clientConnected = false;

    protected String getServerAddress() {
        ConsoleHelper.writeMessage("Ввеедите адрес сервера");
        return ConsoleHelper.readString();
    }

    protected int getServerPort() {
        ConsoleHelper.writeMessage("Ввеедите порт сервера");
        return ConsoleHelper.readInt();
    }

    protected String getUserName() {
        ConsoleHelper.writeMessage("Введите имя пользователя");
        return ConsoleHelper.readString();
    }

    protected boolean shouldSendTextFromConsole() {
        return true;
    }

    public void run() {
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Ошибка");
                System.exit(0);
            }
        }
        if (clientConnected) System.out.println("Соединение установлено. Для выхода наберите команду 'exit'.");
        else System.out.println("Произошла ошибка во время работы клиента.");
        while (clientConnected) {
            String text = ConsoleHelper.readString();
            if (text.equals("exit")) break;
            else if (shouldSendTextFromConsole()) sendTextMessage(text);
        }
    }

    protected SocketThread getSocketThread() {
        return new SocketThread();
    }

    protected void sendTextMessage(String text) {
        try {
            connection.send(new Message(MessageType.TEXT, text));
        } catch (IOException e) {
            System.out.println("Соединение разорвано!");
            clientConnected = false;
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }

    public class SocketThread extends Thread {
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName) {
            ConsoleHelper.writeMessage("Участник с именем " + userName + " присоединился к чату");
        }

        protected void informAboutDeletingNewUser(String userName) {
            ConsoleHelper.writeMessage("Участник с именем " + userName + " покинул чат");
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            synchronized (Client.this) {
                Client.this.clientConnected = clientConnected;
                Client.this.notify();
            }
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException {
            while (true) {
                Message incomingMessage = connection.receive();
                if (incomingMessage.getType() == MessageType.NAME_REQUEST)
                    connection.send(new Message(MessageType.USER_NAME, getUserName()));
                else if (incomingMessage.getType() == MessageType.NAME_ACCEPTED) {
                    notifyConnectionStatusChanged(true);
                    return;
                } else throw new IOException("Unexpected MessageType");
            }
        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            while (true) {
                Message incomingMessage = connection.receive();
                if (incomingMessage.getType() == MessageType.TEXT)
                    processIncomingMessage(incomingMessage.getData());
                else if (incomingMessage.getType() == MessageType.USER_ADDED)
                    informAboutAddingNewUser(incomingMessage.getData());
                else if (incomingMessage.getType() == MessageType.USER_REMOVED)
                    informAboutDeletingNewUser(incomingMessage.getData());
                else throw new IOException("Unexpected MessageType");
            }
        }

        @Override
        public void run() {
            Socket socket = null;
            String serverAddress = getServerAddress();
            int serverPort = getServerPort();
            try {
                socket = new Socket(serverAddress, serverPort);
                Client.this.connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            } catch (IOException | ClassNotFoundException e) {
                notifyConnectionStatusChanged(false);
            }

        }
    }
}
