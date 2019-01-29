package com.javarush.task.task18.task1819;

/* 
Объединение файлов
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String file1 = br.readLine();
        String file2 = br.readLine();
        br.close();
        FileInputStream input1 = new FileInputStream(file1);
        FileInputStream input2 = new FileInputStream(file2);
        byte[] buf1 = new byte[input1.available()] ;
        input1.read(buf1);
        input1.close();
        byte[] buf2 = new byte[input2.available()];
        input2.read(buf2);
        input2.close();
        FileOutputStream outputStream = new FileOutputStream(file1);
        outputStream.write(buf2);
        outputStream.write(buf1);
        outputStream.close();

    }
}
