package com.javarush.task.task27.task2711;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/* 
CountDownLatch
*/
public class Solution {
    public static int getCount() {
        return count.incrementAndGet();
    }

    public final static AtomicInteger count = new AtomicInteger(0);

    private static final CountDownLatch latch = new CountDownLatch(6);

    public void someMethod() throws InterruptedException {
        latch.countDown();
        latch.await();
        retrieveValue();

    }

    void retrieveValue() {
        System.out.println("Value retrieved.");
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        Thread thread1;
        for (int i = 0; i < 5; i++) {
            thread1 = new Thread(Integer.toString(Solution.getCount())){

                @Override
                public void run() {
                    try {
                        this.sleep(500);
                        System.out.println(Thread.currentThread().getName());
                        solution.someMethod();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread1.start();
        }


    }
}
