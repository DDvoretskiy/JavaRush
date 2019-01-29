package com.javarush.task.task16.task1604;

/* 
Вывод стек-трейса
*/

public class Solution {
    public static void main(String[] args) throws InterruptedException {

        ThreadExample t1 = new ThreadExample();
        ThreadExample t2 = new ThreadExample();

        t1.start();


 /*Второй поток t2 начнет выполнение только после того, как будет завершен
       (или бросит исключение) первый поток - t1*/
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();

        //Главный поток продолжит работу только после того, как t1 и t2 завершат работу
        try {

            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Все потоки закончили работу, программа завершена");
    }

    public static class ThreadExample extends Thread {

        @Override
        public void run() {

            System.out.println("Начало работы потока " + getName());

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Поток " + getName() + " завершил работу.");
        }
    }
}