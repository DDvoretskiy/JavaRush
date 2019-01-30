package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;

public class OrderManager implements Observer {
    private LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    public OrderManager() {

        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    while (orderQueue.isEmpty())
                        Thread.sleep(10);
                    for (Cook cook : StatisticManager.getInstance().getCooks())
                        if (!cook.isBusy())
                            new Thread(() -> {
                                if (!orderQueue.isEmpty())
                                    try {
                                        cook.startCookingOrder(orderQueue.poll());
                                    } catch (InterruptedException e) {

                                    }
                            }).start();
                }
            } catch (InterruptedException e) {
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            orderQueue.put((Order) arg);

        } catch (InterruptedException e) {

        }
    }
}
