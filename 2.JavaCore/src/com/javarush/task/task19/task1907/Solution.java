package com.javarush.task.task19.task1907;

/* 
Считаем слово
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new FileReader(bufferedReader.readLine()));
        bufferedReader.close();
        String str="";
        ArrayList<String> list = new ArrayList<String>();
        while (fileReader.ready()) {
            list.add(fileReader.readLine());
        }
        int count = 0;
        for (String s :
                list) {
            for (String s1 :
                    s.split(" ")) {
                if (s1.equals("world") )count++;
            }
        }
        fileReader.close();
        System.out.println(count);
    }
}
