package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис ;lk"));
    }

    public static String getPartOfString(String string) {
        int index = 0, pos = 0, count = 0;
        if (string == null || string.isEmpty()) throw new TooShortStringException();
        while (pos != -1) {
            pos = string.indexOf(" ", pos);
            if (pos != -1) {
                count++;
                if (count == 5) {
                    index = pos;
                }
                pos++;
            }
        }
        if (count < 5) {
            index = string.length();
        }
        String s = string.substring(string.indexOf(" ") + 1, index);
        if (count < 4) {
            throw new TooShortStringException();
        }
        return s;

    }

    public static class TooShortStringException extends RuntimeException {
    }
}
