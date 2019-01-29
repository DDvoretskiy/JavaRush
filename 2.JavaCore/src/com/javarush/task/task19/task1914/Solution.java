package com.javarush.task.task19.task1914;

/* 
Решаем пример
*/


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream startStream = System.out;
        ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
        PrintStream newStream = new PrintStream(byteArrayInputStream);
        System.setOut(newStream);
        testString.printSomething();
        System.setOut(startStream);
        String res = byteArrayInputStream.toString();
        Pattern p = Pattern.compile(" ");
        String[] test = p.split(res);
        for (int i = 0; i < 4; i++) {
            System.out.print(test[i] + " ");
        }
        switch (test[1]) {
            case "+":
                System.out.println(Integer.valueOf(test[0]) + Integer.valueOf(test[2]));
                break;
            case "-":
                System.out.println(Integer.valueOf(test[0]) - Integer.valueOf(test[2]));
                break;
            case "*":
                System.out.println(Integer.valueOf(test[0]) * Integer.valueOf(test[2]));
        }
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

