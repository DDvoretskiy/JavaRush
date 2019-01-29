package com.javarush.task.task27.task2712.kitchen;

public enum Dish {
    Fish(25), Steak(30), Soup(15), Juice(5), Water(3);

    public int getDuration() {
        return duration;
    }

    private int duration;
    private Dish(int duration){
        this.duration = duration;
    }

    public static String allDishesToString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (Dish dish :
                Dish.values()) {
            stringBuilder.append(dish+", ");
        }
        return stringBuilder.substring(0,stringBuilder.lastIndexOf(","));
    }

}
