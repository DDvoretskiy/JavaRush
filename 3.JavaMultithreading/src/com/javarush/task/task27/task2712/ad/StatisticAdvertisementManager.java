package com.javarush.task.task27.task2712.ad;

import java.util.Set;
import java.util.stream.Collectors;

public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager instance;
    private AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();

    private StatisticAdvertisementManager() {

    }

    public static synchronized StatisticAdvertisementManager getInstance() {
        if (instance == null) {
            instance = new StatisticAdvertisementManager();
        }
        return instance;
    }

    public Set<Advertisement> getActiveVideosSet() {
        return advertisementStorage.list().stream().filter(advertisement -> advertisement.getHits()>0).collect(Collectors.toSet());
    }

    public Set<Advertisement> getArchivedVideoSet() {
        return advertisementStorage.list().stream().filter(advertisement -> advertisement.getHits()==0).collect(Collectors.toSet());
    }
}
