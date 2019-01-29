package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            ConsoleHelper.writeMessage("Установлено соединение с удаленным адресом " + socket.getRemoteSocketAddress());
            String newUserName = null;
            try (Connection connection = new Connection(socket)) {
                newUserName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, newUserName));
                sendListOfUsers(connection, newUserName);
                serverMainLoop(connection, newUserName);
                if (!newUserName.isEmpty()) {
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED, newUserName));
                    connectionMap.remove(newUserName);
                    ConsoleHelper.writeMessage("Соединение с удаленным сервером закрыто");
                }
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Ошибка при обмене данными с удаленным адресом");
            } catch (ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Ошибка при обмене данными с удаленным адресом");
            }
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message receivedMessage = connection.receive();
                MessageType receivedMessageType = receivedMessage.getType();
                String receivedMessageData = receivedMessage.getData();
                if (receivedMessageType == MessageType.USER_NAME) {
                    if (receivedMessageData != null && !receivedMessageData.isEmpty() && !connectionMap.containsKey(receivedMessageData)) {
                        connectionMap.put(receivedMessageData, connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        return receivedMessageData;
                    }
                }
            }

        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            for (String name :
                    connectionMap.keySet()) {
                if (!name.equals(userName))
                    connection.send(new Message(MessageType.USER_ADDED, name));

            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message receivedMessage = connection.receive();
                if (receivedMessage.getType() == MessageType.TEXT) {
                    sendBroadcastMessage(new Message(MessageType.TEXT, userName + ": " + receivedMessage.getData()));
                } else ConsoleHelper.writeMessage("Ошибка в типе сообщения");
            }
        }
    }

    public static void main(String[] args) {
        //ConsoleHelper.writeMessage(new Integer(ConsoleHelper.readInt()).toString());
        ConsoleHelper.writeMessage("Введите порт сервера");
        int serverSocketNumber = ConsoleHelper.readInt();
        try (ServerSocket serverSocket = new ServerSocket(serverSocketNumber)) {
            ConsoleHelper.writeMessage("Сервер запущен");
            while (true) {
                Handler handler = new Handler(serverSocket.accept());
                handler.start();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void sendBroadcastMessage(Message message) {
        connectionMap.forEach((k, v) -> {
            try {
                v.send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Невозможно отправить сообщение");
            }
        });
    }


}
