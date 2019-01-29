package com.javarush.task.task18.task1816;

/* 
Английские буквы
*/

import java.io.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) throws Exception {
        int i = 0;
        Set<String> set = new HashSet<String>();
        Collections.addAll(set, "a b c d e f g h i j k l m n o p q r s t u v w x y z".split(" "));
        BufferedReader fileInputStream = new BufferedReader(new FileReader(new File(args[0])));
        int c =0;
        String s = "";
        while ((c = fileInputStream.read()) != -1) {
            if (set.contains(String.valueOf((char) c).toLowerCase())) {
                i++;
            }
        }
        fileInputStream.close();
        System.out.println(i);


    }
}
