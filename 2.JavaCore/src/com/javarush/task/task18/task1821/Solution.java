package com.javarush.task.task18.task1821;

/* 
Встречаемость символов
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        //      FileInputStream inputStream = new FileInputStream(new File(args[0]));
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream inputStream = new FileInputStream(new File(rd.readLine()));

        ArrayList<Integer> symbols = new ArrayList<Integer>();
        while (inputStream.available() > 0) {
            symbols.add(inputStream.read());
        }
        Collections.sort(symbols, Comparator.reverseOrder());
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (Integer x : symbols) {
            map.put(x, Collections.frequency(symbols, x));

        }
        for (int bt : map.keySet()) {
            System.out.println( (char)bt + " - " + map.get(bt));
        }
        inputStream.close();
    }


}
