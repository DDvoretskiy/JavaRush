package com.javarush.task.task06.task0613;

/* 
Кот и статика
*/

import java.util.concurrent.atomic.AtomicInteger;

public class Solution {
    public static void main(String[] args) {
        Cat cat;
        for (int i = 0; i < 10; i++) {
            cat = new Cat();

        }
        System.out.println(Cat.getCatCount());
        // Создай 10 котов

        // Выведи значение переменной catCount
    }

    public static class Cat {
        private static AtomicInteger catCount = new AtomicInteger(0);
        // Создай статическую переменную catCount

        public Cat() {
            catCount.incrementAndGet();
        }

        public static AtomicInteger getCatCount() {
            return catCount;
        }
// Создай конструктор
    }
}
