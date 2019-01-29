package com.javarush.task.task19.task1904;

import java.io.IOException;
import java.util.*;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) {

    }

    public static class PersonScannerAdapter implements PersonScanner{
        private Scanner fileScanner;

        public PersonScannerAdapter(Scanner fileScanner) {
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException {
           String data = fileScanner.nextLine();
            ArrayList<String > list = new ArrayList<String>();
            for (String s :
                    data.split(" ")) {
                list.add(s);
            }
            Calendar calendar = new GregorianCalendar(Integer.parseInt(list.get(5)), Integer.parseInt(list.get(4)) - 1, Integer.parseInt(list.get(3)));
            Date date = calendar.getTime();
            return new Person(list.get(1),list.get(2),list.get(0),date);
        }

        @Override
        public void close() throws IOException {
            fileScanner.close();
        }
    }
}
