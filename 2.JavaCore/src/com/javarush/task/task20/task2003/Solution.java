package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/* 
Знакомство с properties
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();
    public static Properties props = new Properties();

    public void fillInPropertiesMap() throws Exception {
        //implement this method - реализуйте этот метод
        BufferedReader fileName = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream inputStream = new FileInputStream(fileName.readLine());
        fileName.close();
        load(inputStream);

    }

    public void save(OutputStream outputStream) throws Exception {
        PrintWriter printWriter = new PrintWriter(outputStream);
        if (properties.size() > 0) {
            props.putAll(properties);
            props.store (printWriter,"");
        }
        printWriter.close();
    }

    public void load(InputStream inputStream) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        props.load(inputStream);
        Set<String> list = props.stringPropertyNames();
        for (String current : list)
            properties.put(current, props.getProperty(current));
        bufferedReader.close();
    }

    public static void main(String[] args) {

    }
}
