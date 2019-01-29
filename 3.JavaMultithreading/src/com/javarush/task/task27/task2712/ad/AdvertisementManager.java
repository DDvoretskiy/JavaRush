package com.javarush.task.task27.task2712.ad;


import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AdvertisementManager {
    private AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException {
        int timeUnits = timeSeconds;
        List<Advertisement> validatedList = storage.list();
        validatedList = validatedList.stream().filter(p1 -> p1.getDuration() <= timeSeconds && p1.getHits() > 0).collect(Collectors.toList());
        Comparator<Advertisement> comparator = Comparator.comparing(advertisement -> advertisement.getAmountPerOneDisplaying());
        comparator = comparator.reversed().thenComparing(advertisement -> advertisement.getAmountPerOneDisplaying() / advertisement.getDuration());
        validatedList.sort(comparator);
        ArrayList<Advertisement> advList = new ArrayList<>();
        long amount = 0;
        int totalDuration = 0;
        for (Advertisement advertisement :
                validatedList) {
            if (advertisement.getHits() <= 0 || timeUnits < advertisement.getDuration()) {
                continue;
            }
            advList.add(advertisement);
            amount += advertisement.getAmountPerOneDisplaying();
            totalDuration += advertisement.getDuration();
            timeUnits-=advertisement.getDuration();
        }

        StatisticManager.getInstance().register(new VideoSelectedEventDataRow(advList, amount, totalDuration));
        for (Advertisement advertisment :
                advList) {
            advertisment.revalidate();
            ConsoleHelper.writeMessage(advertisment.getName() + " is displaying... " + advertisment.getAmountPerOneDisplaying() + ", " + advertisment.getAmountPerOneDisplaying() * 1000 / advertisment.getDuration());
        }

    }
}


