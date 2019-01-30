package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.ArrayList;

public class TestOrder extends Order {
    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    @Override
    protected void initDishes() throws IOException {
        dishes = new ArrayList<>();
        Dish[] dishesarray = Dish.values();
        for (int i = 0; i < (int) Math.random() * 4; i++) {
            int index = (int) Math.random() * 4;
            dishes.add(dishesarray[index]);
        }
    }
}
