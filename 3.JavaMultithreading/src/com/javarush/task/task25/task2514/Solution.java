package com.javarush.task.task25.task2514;


/*
Первый закон Финэйгла: если эксперимент удался, что-то здесь не так...
*/
public class Solution {
    public static class YieldRunnable implements Runnable {
        private int index;

        public YieldRunnable(int index) {
            this.index = index;
        }

        public void run() {
            System.out.println("begin-" + index);
            Thread.yield();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end-" + index);
        }
    }

    public static void main(String[] args) {
        Solution.YieldRunnable thred1 = new Solution.YieldRunnable(1);
        Solution.YieldRunnable thred2 = new Solution.YieldRunnable(2);
        Solution.YieldRunnable thred3 = new Solution.YieldRunnable(3);
        thred1.run();
        thred2.run();
        thred3.run();

    }
}
