package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;

public class Cook extends Observable {
    private String name;
    private boolean busy;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }


    public void startCookingOrder(Order order) throws InterruptedException {
        busy = true;
        System.out.println("Start cooking - " + order + ", cooking time " + ((Order) order).getTotalCookingTime() + "min");
        StatisticManager.getInstance().register(new CookedOrderEventDataRow(order.getTablet().toString(), name, ((Order) order).getTotalCookingTime(), ((Order) order).getDishes()));
        setChanged();
        notifyObservers(order);
        Thread.sleep(10 * order.getTotalCookingTime());
        busy = false;
    }

    public boolean isBusy() {
        return busy;
    }
}
