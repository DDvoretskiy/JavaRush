package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.OrderManager;
import com.javarush.task.task27.task2712.Tablet;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;

public class Cook extends Observable {
    private String name;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }


    public void startCookingOrder(Order order){
        System.out.println("Start cooking - " + order+", cooking time "+((Order)order ).getTotalCookingTime()+"min");
        StatisticManager.getInstance().register(new CookedOrderEventDataRow(OrderManager.getTablet().toString(),name,((Order) order).getTotalCookingTime(),((Order) order).getDishes()));
        setChanged();
        notifyObservers(order);
    }

}
