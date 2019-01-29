package com.javarush.task.task18.task1820;

/* 
Округление чисел
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = bufferedReader.readLine();
        String file2 = bufferedReader.readLine();
        bufferedReader.close();
        FileInputStream fin = new FileInputStream(file1);
        BufferedWriter fot = new BufferedWriter(new FileWriter(file2));
        ArrayList<String> list = new ArrayList<String>();
        byte[] file = new byte[fin.available()];
        fin.read(file);
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < file.length; i++) {
            if ((char) file[i] != '\n' && (char) file[i] != '\r') str.append(String.valueOf((char) file[i]));
        }
        String res = str.toString();
        for (String s :
                res.split(" ")) {
            list.add(String.valueOf(Math.round(Float.valueOf(s))));
        }
        for (int i = 0; i < list.size(); i++) {
            fot.write(list.get(i) + " ");
        }
        fin.close();
        fot.close();
    }

}
