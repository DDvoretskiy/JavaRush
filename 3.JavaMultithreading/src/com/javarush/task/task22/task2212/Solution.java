package com.javarush.task.task22.task2212;

import java.util.ArrayList;

/*
Проверка номера телефона
*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        if (telNumber == null) return false;
        return  (telNumber.matches("^\\+(\\d[\\-\\(\\)]?){11}\\d$") || telNumber.matches("^[\\(\\d]-?(\\d[\\-\\)]?){8}\\d$"))
                && telNumber.matches("^(\\+)?(\\d)*(\\(\\d{3}\\))?(\\d+-?\\d+-?)?\\d+$");
    }

    public static void main(String[] args) {
        ArrayList<String> numbers = new ArrayList<>();
        numbers.add("+380501234567");
        numbers.add("+38(050)1234567");
        numbers.add("+38050123-45-67");
        numbers.add("050123-4567");
        numbers.add("+38)050(1234567");
        numbers.add("+38(050)1-23-45-6-7");
        numbers.add("050ххх4567");
        numbers.add("050123456");
        numbers.add("(0)501234567");

        for (String num :  numbers){
            System.out.println(num+" "+checkTelNumber(num));
        }

    }
}
