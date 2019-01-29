package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.Map;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {
      /*  Map<String, String> map = new HashMap<>();
        map.put("name", "Ivanov");
        map.put("city", null);
        map.put("age", "45");
        System.out.println(getQuery(map));
*/
    }

    public static String getQuery(Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> map :
                params.entrySet()) {
            if (map.getValue() != null) {
                if (map.getValue() != null) {
                    if (stringBuilder.toString().equals(""))
                        stringBuilder.append(map.getKey()).append(" = '").append(map.getValue()).append("'");
                    else
                        stringBuilder.append(" and ").append(map.getKey()).append(" = '").append(map.getValue()).append("'");
                }

            }
        }

        return stringBuilder.toString();
    }
}
