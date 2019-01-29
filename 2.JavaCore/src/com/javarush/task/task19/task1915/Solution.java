package com.javarush.task.task19.task1915;

/* 
Дублируем текст
*/

import java.io.*;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        FileOutputStream file = new FileOutputStream(bufferedReader.readLine());
        bufferedReader.close();
        PrintStream startStream = System.out;
        ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
        PrintStream newStream = new PrintStream(byteArrayInputStream);
        System.setOut(newStream);
        testString.printSomething();
        file.write(byteArrayInputStream.toByteArray());
        file.close();
        System.setOut(startStream);
        System.out.println(byteArrayInputStream.toString());

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}

