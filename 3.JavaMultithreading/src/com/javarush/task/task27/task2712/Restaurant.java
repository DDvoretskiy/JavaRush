package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.ArrayList;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;

    public static void main(String[] args) {
        Cook cook = new Cook("Вася");
        Cook cook2 = new Cook("Петя");
        StatisticManager.getInstance().register(cook);
        StatisticManager.getInstance().register(cook2);
        OrderManager orderManager = new OrderManager();
        Waiter waiter = new Waiter();
        cook2.addObserver(waiter);
        ArrayList<Tablet> tablets = new ArrayList<>();
        Tablet tablet;
        for (int i = 0; i < 5; i++) {
            tablet = new Tablet(i);
            tablet.addObserver(orderManager);
            tablets.add(tablet);
        }
        Thread thread = new Thread(new RandomOrderGeneratorTask(tablets,ORDER_CREATING_INTERVAL));
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        /*
        ConsoleHelper.writeMessage(String.format("Заказ под номером %d", 1));
        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet(); */

    }
}
