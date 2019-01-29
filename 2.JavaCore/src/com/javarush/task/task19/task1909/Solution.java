package com.javarush.task.task19.task1909;

/* 
Замена знаков
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader file1 = new BufferedReader(new FileReader(bufferedReader.readLine()));
        BufferedWriter file2 = new BufferedWriter(new FileWriter(bufferedReader.readLine()));
        bufferedReader.close();
        String str = "";
        while (file1.ready()) {
            str = str + file1.readLine();
        }
        file1.close();
        str=str.replaceAll("\\Q.\\E","!");
        file2.write(str);
        file2.close();
    }
}
