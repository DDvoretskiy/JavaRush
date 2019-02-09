package com.javarush.task.task06.task0613;

/* 
Кот и статика
*/

import java.util.concurrent.atomic.AtomicInteger;

public class Solution {
    public static void main(String[] args) {


    }
@FunctionalInterface
    public interface Int2{
        public void worUt();
        default void dosmthing(String str){
            System.out.println(str);
        }
    }

}
