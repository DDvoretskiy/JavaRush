package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.util.Locale;

public class Restaurant {
    private static int ORDER_CREATING_INTERVAL = 100;
    public static void main(String[] args) {

        Tablet tablet = new Tablet(1);
        Cook cook = new Cook("Вася");
        Waiter waiter = new Waiter();
        tablet.addObserver(cook);
        cook.addObserver(waiter);
        ConsoleHelper.writeMessage(String.format("Заказ под номером %d", 1));
        tablet.createOrder();

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();

    }
}
