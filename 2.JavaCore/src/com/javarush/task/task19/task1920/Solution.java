package com.javarush.task.task19.task1920;

/* 
Самый богатый
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static TreeMap<String, Double> res = new TreeMap<String, Double>();

    public static void main(String[] args) throws Exception {
        BufferedReader file = new BufferedReader(new FileReader(args[0]));
        String str = "",name="";
        Double max = Double.MIN_VALUE;
        while (file.ready()) {
            str = file.readLine();
            createMap(str);
        }
        file.close();
        for (Map.Entry<String, Double> map :
                res.entrySet()) {
            if (map.getValue()>max){
                max=map.getValue();
            }
        }
        for (Map.Entry<String, Double> map :
                res.entrySet()) {
            if (map.getValue() == max){
                System.out.println(map.getKey());
            }
        }
    }

    public static void createMap(String str) {
        Double db = 0.0;
        if (res.containsKey(str.split(" ")[0])) {
            db = res.get(str.split(" ")[0]).doubleValue() + Double.valueOf(str.split(" ")[1]);
            res.replace(str.split(" ")[0], db);
        } else res.put(str.split(" ")[0], Double.valueOf(str.split(" ")[1]));

    }
}
