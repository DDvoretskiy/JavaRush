package com.javarush.task.task18.task1826;

/* 
Шифровка
*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(args[1]);
        FileOutputStream fileOutputStream = new FileOutputStream(args[2]);
        fileOutputStream.write(cryptor(fileInputStream, args[0]));
        fileInputStream.close();
        fileOutputStream.close();
    }

    public static byte[] cryptor(FileInputStream input, String key) throws IOException {
        byte[] buf = new byte[input.available()];
        input.read(buf);
        if (key.equals("-e")) {
            for (int i = 0; i < buf.length; i++) {
                buf[i] = (byte) (buf[i] + 5);
            }
        } else for (int i = 0; i < buf.length; i++) {
            buf[i] = (byte) (buf[i] - 5);
        }
        return buf;
    }
}


