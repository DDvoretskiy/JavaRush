package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.Util;
import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

public class StatisticManager {
    private static StatisticManager instance;
    private Set<Cook> cooks = new HashSet<>();

    private StatisticManager() {
    }

    private StatisticStorage statisticStorage = new StatisticStorage();

    public static synchronized StatisticManager getInstance() {
        if (instance == null) {
            instance = new StatisticManager();
        }
        return instance;
    }

    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }

    public void register(Cook cook) {
        cooks.add(cook);
    }

    public void printAdvertisementProfit() {
        HashMap<String, Long> advMap = processAdvertisementProfit();
        long profitValue = 0;
        for (Map.Entry<String, Long> mapEntry :
                advMap.entrySet()) {
            System.out.printf(mapEntry.getKey() + " - %.2f\n", mapEntry.getValue() / 100.00);
            profitValue += mapEntry.getValue();
        }
        System.out.printf("Total - %.2f", profitValue / 100.00);
        System.out.println();
    }


    private HashMap<String, Long> processAdvertisementProfit() {
        HashMap<String, Long> advMap = new HashMap<>();
        VideoSelectedEventDataRow videoSelectedEventDataRow;
        for (EventDataRow advertisement :
                statisticStorage.getStorage(EventType.SELECTED_VIDEOS)) {
            videoSelectedEventDataRow = (VideoSelectedEventDataRow) advertisement;
            advMap.merge(Util.dateformat(videoSelectedEventDataRow.getDate()), videoSelectedEventDataRow.getAmount(), (oldval, newVal) -> oldval + newVal);
        }
        return advMap;
    }

    public void printCookWorkloading() {
        HashMap<String, HashMap<String, Integer>> processCookTime = processCookTime();

        for (String mapEntry :
                processCookTime.keySet()) {
            System.out.println(mapEntry);
           HashMap<String,Integer> entrySet = processCookTime.get(mapEntry);
            for (String key :
                    entrySet.keySet()) {
                if(entrySet.get(key)>0)
                System.out.println(key+" - "+((int)entrySet.get(key))+" min" );
            }
            System.out.println();

        }

    }

    private HashMap<String, HashMap<String, Integer>> processCookTime() {
        HashMap<String, HashMap<String, Integer>> processCookTime = new HashMap<>();
        for (EventDataRow cookData :
                statisticStorage.getStorage(EventType.COOKED_ORDER)) {
            if (processCookTime.containsKey(Util.dateformat(cookData.getDate())))
                processCookTime.get(Util.dateformat(cookData.getDate())).merge(((CookedOrderEventDataRow) cookData).getCookName(), cookData.getTime(), (oldVal, newVal) -> oldVal + newVal);
            else {
                HashMap<String, Integer> entryMap = new HashMap<>();
                entryMap.put(((CookedOrderEventDataRow) cookData).getCookName(), cookData.getTime());
                processCookTime.put(Util.dateformat(cookData.getDate()), entryMap);
            }
        }
        return processCookTime;
    }


    public void printActiveVideoSet() {
        StatisticAdvertisementManager.getInstance().getActiveVideosSet().stream().sorted(((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))).forEach(ins->{
            System.out.println(ins.getName()+" - "+ins.getHits());
        });
    }

    public void printArchivedVideoSet() {
        StatisticAdvertisementManager.getInstance().getArchivedVideoSet().stream().sorted(((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))).forEach(ins->{
            System.out.println(ins.getName());
        });
    }


    private static class StatisticStorage {
        private HashMap<EventType, List<EventDataRow>> storage = new HashMap<>();

        private StatisticStorage() {
            for (EventType type : EventType.values()) {
                storage.put(type, new ArrayList<EventDataRow>());
            }
        }

        private void put(EventDataRow data) {
            storage.get(data.getType()).add(data);
        }

        public List<EventDataRow> getStorage(EventType eventType) {
            return storage.get(eventType);
        }
    }

}
