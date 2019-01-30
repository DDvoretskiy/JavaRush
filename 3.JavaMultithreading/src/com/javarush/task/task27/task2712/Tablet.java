package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.TestOrder;

import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet extends Observable {
    static Logger logger = Logger.getLogger((Tablet.class.getName()));
    final int number;


    public Tablet(int number) {
        this.number = number;
    }

    public Order createOrder() {
        Order order = null;
        try {
            order = new Order(this);

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
            order = null;
        }
        if (!order.isEmpty()) {
            AdvertismentManagerProcessVideos(order);
        }
        return order;
    }

    private void AdvertismentManagerProcessVideos(Order order) {
        AdvertisementManager advertisementManager = new AdvertisementManager(order.getTotalCookingTime()*60);
        try {
            advertisementManager.processVideos();
        } catch (NoVideoAvailableException e) {
            logger.log(Level.INFO,"No video is available for the order "+order);
        }
        setChanged();
        notifyObservers(order);
    }

    public void createTestOrder(){
        TestOrder order = null;
        try {
            order = new TestOrder(this);

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
            order = null;
        }
        if (!order.isEmpty()) {
            AdvertismentManagerProcessVideos(order);
        }
    }
    @Override
    public String toString() {
        return "Tablet{number=" + number + "}";
    }

}
