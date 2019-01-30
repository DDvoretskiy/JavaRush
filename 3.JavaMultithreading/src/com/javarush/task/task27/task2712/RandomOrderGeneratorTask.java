package com.javarush.task.task27.task2712;

import java.util.List;

public class RandomOrderGeneratorTask implements Runnable {

    private List<Tablet> tabletList;
    private int orderInterval;

    public RandomOrderGeneratorTask(List<Tablet> tabletList, int orderInterval) {
        this.tabletList = tabletList;
        this.orderInterval = orderInterval;
    }

    @Override
    public void run() {
        int randomNumber = (int) (Math.random() * tabletList.size());
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(orderInterval);
                tabletList.get(randomNumber).createOrder();
            } catch (InterruptedException e) {

            }
        }
    }
}
