package com.javarush.task.task18.task1817;

/* 
Пробелы
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) throws Exception {
        int i = 0, j = 0;

        FileInputStream fileInputStream = new FileInputStream(args[0]);
        while (fileInputStream.available()>0) {
            j++;
            if (fileInputStream.read() == Integer.valueOf(' ')){
            i++;}
        }
        fileInputStream.close();
        double res = (double) i/j*100;
        System.out.printf("%.2f",res);
    }
}
