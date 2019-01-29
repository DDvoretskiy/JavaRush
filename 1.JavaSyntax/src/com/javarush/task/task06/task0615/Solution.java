package com.javarush.task.task06.task0615;

/* 
Феншуй и статики
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println("Program starts");

        try {
            System.out.println("Before method1 calling");
            method1();
            System.out.println("After method1 calling. Never will be shown");
            for (StackTraceElement stacktrace: Thread.currentThread().getStackTrace()
                 ) {
                System.out.println(stacktrace.getMethodName());
            }
        } catch (Exception e) {
            System.out.println("Exception has been caught");
        }

        System.out.println("Program is still running");
    }

    public static void method1() {
        int a = 100;
        int b = 1;
        System.out.println(a / b);
    }
}
//https://empires-and-puzzles.info/empires-puzzles-best-of-the-best-heroes/
//https://empires-and-puzzles.info/empires-puzzles-best-of-the-best-heroes/