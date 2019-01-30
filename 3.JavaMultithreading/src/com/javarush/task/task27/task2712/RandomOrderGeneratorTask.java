package com.javarush.task.task27.task2712;

import java.util.List;

public class RandomOrderGeneratorTask implements Runnable {

    private List<Tablet> tabletList;
    private int orderInterval;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval){
        this.tabletList = tablets;
        this.orderInterval = interval;
    }

    @Override
    public void run() {
        int randomNumber = (int) (Math.random() * tabletList.size());
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(orderInterval);
                tabletList.get(randomNumber).createTestOrder();
            } catch (InterruptedException e) {

            }
        }
    }
}
