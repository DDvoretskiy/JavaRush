package com.javarush.task.task30.task3008.client;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BotClient extends Client {
    @Override
    protected String getUserName() {
        return "date_bot_" + (byte) (Math.random() * 100);
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    public class BotSocketThread extends Client.SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            super.processIncomingMessage(message);
            if (message != null && !message.isEmpty() && message.contains(":")) {
                String[] string = message.split(":");
                SimpleDateFormat dateFormat = new SimpleDateFormat("d.MM.YYY");
                Calendar calendar = new GregorianCalendar();
                String textInfo = "Информация для ";

                if (string[1].trim().equals("дата"))
                    sendTextMessage(textInfo + string[0] + ": " + dateFormat.format(calendar.getTime()));
                else if (string[1].trim().equals("день")) {
                    dateFormat = new SimpleDateFormat("d");
                    sendTextMessage(textInfo + string[0] + ": " + dateFormat.format(calendar.getTime()));
                } else if (string[1].trim().equals("месяц")) {
                    dateFormat = new SimpleDateFormat("MMMM");
                    sendTextMessage(textInfo + string[0] + ": " + dateFormat.format(calendar.getTime()));
                } else if (string[1].trim().equals("год")) {
                    dateFormat = new SimpleDateFormat("YYYY");
                    sendTextMessage(textInfo + string[0] + ": " + dateFormat.format(calendar.getTime()));
                } else if (string[1].trim().equals("время")) {
                    dateFormat = new SimpleDateFormat("H:mm:ss");
                    sendTextMessage(textInfo + string[0] + ": " + dateFormat.format(calendar.getTime()));
                } else if (string[1].trim().equals("час")) {
                    dateFormat = new SimpleDateFormat("H");
                    sendTextMessage(textInfo + string[0] + ": " + dateFormat.format(calendar.getTime()));
                } else if (string[1].trim().equals("минуты")) {
                    dateFormat = new SimpleDateFormat("m");
                    sendTextMessage(textInfo + string[0] + ": " + dateFormat.format(calendar.getTime()));
                } else if (string[1].trim().equals("секунды")) {
                    dateFormat = new SimpleDateFormat("s");
                    sendTextMessage(textInfo + string[0] + ": " + dateFormat.format(calendar.getTime()));
                }
            }

        }
    }


    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }
}
