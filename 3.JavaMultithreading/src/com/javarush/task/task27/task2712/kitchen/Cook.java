package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable {
    private String name;
    private LinkedBlockingQueue<Order> queue;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }


    public void startCookingOrder(Order order) throws InterruptedException {

        System.out.println("Start cooking - " + order + ", cooking time " + ((Order) order).getTotalCookingTime() + "min");
        StatisticManager.getInstance().register(new CookedOrderEventDataRow(order.getTablet().toString(), name, ((Order) order).getTotalCookingTime(), ((Order) order).getDishes()));
        setChanged();
        notifyObservers(order);
        Thread.sleep(10 * order.getTotalCookingTime());

    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                while (queue.isEmpty())
                    Thread.sleep(10);
                startCookingOrder(queue.poll());
            }
        } catch (InterruptedException e) {
        }
    }
}
