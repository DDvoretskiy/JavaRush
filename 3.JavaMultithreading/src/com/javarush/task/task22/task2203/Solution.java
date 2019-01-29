package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/
public class Solution {
    public static String getPartOfString(String string) throws  TooShortStringException{
        int index = 0, pos = 0, count = 0;
        if (string == null || string.isEmpty()) throw new TooShortStringException();
        while (pos != -1) {
            pos = string.indexOf("\t", pos);
            if (pos != -1) {
                count++;
                if (count == 2) {
                    index = pos;
                }
                pos++;
            }
        }
        if (count < 2) {
            throw new TooShortStringException();
        }
        String s = string.substring(string.indexOf("\t") + 1, index);
        return s;

    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис "));
    }
}
