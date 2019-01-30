package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final Tablet tablet;

    protected List<Dish> dishes;

    public List<Dish> getDishes() {
        return dishes;
    }

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        initDishes();
    }

    public int getTotalCookingTime() {
        int totalCoockingTime = 0;
        for (Dish dish :
                dishes) {
            totalCoockingTime += dish.getDuration();
        }
        return totalCoockingTime;
    }

    public boolean isEmpty() {
        return dishes.isEmpty();
    }

    @Override
    public String toString() {
        if (dishes.isEmpty()) return "";
        else {
            StringBuilder stringBuilder = new StringBuilder("Your order:[");
            for (Dish dish :
                    dishes) {
                stringBuilder.append(dish + ", ");
            }
            return stringBuilder.substring(0, stringBuilder.lastIndexOf(",")) + "] of " + tablet;
        }

    }
    protected void initDishes() throws IOException{
        dishes = ConsoleHelper.getAllDishesForOrder();
    }
}
