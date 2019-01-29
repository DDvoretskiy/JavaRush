package com.javarush.task.task19.task1918;

/* 
Знакомство с тегами
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader file = new BufferedReader(new FileReader(bufferedReader.readLine()));
        bufferedReader.close();
        StringBuilder str = new StringBuilder();
        while (file.ready()) {
            str.append(file.readLine());
        }
        file.close();
        String data = str.toString().replaceAll("\r|\n", "");
        String teg = args[0];
        String start = "<" + teg;
        String end = "</" + teg + ">";
        int a = 1, b = 0;
        int check = 1, index = 0;
        Pattern p = Pattern.compile(start);
        Matcher m = p.matcher(data);
        ArrayList<Integer> startlist = new ArrayList<Integer>();
        while (m.find()) {
            startlist.add(m.start());
        }
        Pattern e = Pattern.compile(end);
        Matcher n = e.matcher(data);
        ArrayList<Integer> endlist = new ArrayList<Integer>();
        while (n.find()) {
            endlist.add(n.start());
        }
        TreeMap<Integer, String> map = new TreeMap<Integer, String>();
        for (int i = 0; i < endlist.size(); i++) {
            map.put(startlist.get(i), "s");
            map.put(endlist.get(i), "e");
        }
        System.out.println(data);
        System.out.println(map);
        for (Map.Entry<Integer, String> mp :
                map.entrySet()) {
            int i = 0, count = 0;
            for (Map.Entry<Integer, String> nmp :
                    map.entrySet()) {
                if ((mp.getValue().equals(nmp.getValue()))&&(mp.getKey()!=nmp.getKey())) {
                    count++;
                } else count--;
                if (count == 0) {
                    System.out.println(data.substring(mp.getKey(), nmp.getKey() + 3 + args[0].length()));
                }
            }
        }
    }
}
