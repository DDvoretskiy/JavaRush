package com.javarush.task.task18.task1818;

/* 
Два в одном
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String file1 = br.readLine();
        String file2 = br.readLine();
        String file3 = br.readLine();
        br.close();

        FileOutputStream fileOutputStream = new FileOutputStream(file1);
        FileInputStream fileInputStream2 = new FileInputStream(file2);
        FileInputStream fileInputStream3 = new FileInputStream(file3);

        byte[] buffer2 = new byte[fileInputStream2.available()];
        fileInputStream2.read(buffer2);
        fileInputStream2.close();

        byte[] buffer3 = new byte[fileInputStream3.available()];
        fileInputStream3.read(buffer3);

        fileOutputStream.write(buffer2);
        fileOutputStream.write(buffer3);

        fileOutputStream.close();
        fileInputStream2.close();
        fileInputStream3.close();


    }

}
