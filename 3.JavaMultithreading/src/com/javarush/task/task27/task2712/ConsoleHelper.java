package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
   static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return bufferedReader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        String selectedDish;
        List<Dish> dishesForOrder = new ArrayList<>();
        while (true) {
            writeMessage(Dish.allDishesToString());
            writeMessage("Выберите блюда из списка. Для выхода нажмите Exit");
            selectedDish = readString();
            try {
                dishesForOrder.add(Dish.valueOf(selectedDish));
            } catch (IllegalArgumentException e) {
                if (selectedDish.equals("exit"))
                    return dishesForOrder;
            }
        }

    }
}
