package com.javarush.task.task19.task1908;

/* 
Выделяем числа
*/

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader file1 = new BufferedReader(new FileReader(bufferedReader.readLine()));
        BufferedWriter file2 = new BufferedWriter(new FileWriter(bufferedReader.readLine()));
        bufferedReader.close();
        String str = "", res = "";
        while (file1.ready()) {
            str = str + file1.readLine();
        }
        file1.close();

        for (String s :
                str.split(" ")) {
            if (s.matches("\\d+")) {
                res = res + s + " ";
            }
        }
        file2.write(res);
        file2.close();
    }
}
