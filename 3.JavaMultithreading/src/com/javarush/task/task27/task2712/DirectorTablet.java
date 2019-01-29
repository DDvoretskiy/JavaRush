package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.statistic.StatisticManager;

public class DirectorTablet {
    public void printAdvertisementProfit(){
        StatisticManager.getInstance().printAdvertisementProfit();
    }
    public void printCookWorkloading(){
        StatisticManager.getInstance().printCookWorkloading();
    }
    public void printActiveVideoSet(){
        StatisticManager.getInstance().printActiveVideoSet();
    }
    public void printArchivedVideoSet(){
        StatisticManager.getInstance().printArchivedVideoSet();
    }
}
