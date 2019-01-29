package com.javarush.task.task16.task1602;

/* 
My second thread
*/

public class Solution {
    public static void main(String[] args) {
            for (int i = 0; i < 100; i++) {
            new Thread(()-> System.out.println("Работа потока"+Thread.currentThread().getName())).start();
        }
    }

}
