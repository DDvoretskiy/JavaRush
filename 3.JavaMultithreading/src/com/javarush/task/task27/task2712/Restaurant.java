package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();
    public static void main(String[] args) {
        Cook cook = new Cook("Вася");
        cook.setQueue(orderQueue);
        Cook cook2 = new Cook("Петя");
        cook2.setQueue(orderQueue);
        Waiter waiter = new Waiter();
        cook.addObserver(waiter);
        cook2.addObserver(waiter);
        ArrayList<Tablet> tablets = new ArrayList<>();
        Tablet tablet;
        for (int i = 0; i < 5; i++) {
            tablet = new Tablet(i);
            tablet.setQueue(orderQueue);
            tablets.add(tablet);
        }
        ThreadGroup threadGroup = new ThreadGroup("Демоны");
        Thread threadCook = new Thread(threadGroup,cook);
        Thread threadCook2 = new Thread(threadGroup,cook2);
        Thread thread = new Thread(threadGroup,new RandomOrderGeneratorTask(tablets,ORDER_CREATING_INTERVAL));
        threadGroup.setDaemon(true);
        thread.start();
        threadCook.start();
        threadCook2.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();

        //ConsoleHelper.writeMessage(String.format("Заказ под номером %d", 1));
        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}
