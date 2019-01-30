package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementStorage {
    private final List<Advertisement> videos = new ArrayList();
    private static AdvertisementStorage ourInstance;

    public static synchronized AdvertisementStorage getInstance() {
        if (ourInstance == null) {
            ourInstance = new AdvertisementStorage();
        }
        return ourInstance;
    }

    private AdvertisementStorage() {
        Object someContent = new Object();
        add(new Advertisement(someContent, "first Video", 1523, 1, 3 * 60));
        add(new Advertisement(someContent, "Second video", 5, 0, 60));
        add(new Advertisement(someContent, "Третье видео", 99, 0, 3 * 60));
        add(new Advertisement(someContent, "а вот такое видео", 99, 10, 2 * 60));
        add(new Advertisement(someContent, "Новинка сезона", 2506, 3, 3 * 60));
       /* add(new Advertisement(someContent, "6", 2506, 3, 3 * 60));
        add(new Advertisement(someContent, "7", 400, 1, 3 * 60));
        add(new Advertisement(someContent, "8", 500, 1, 2 * 60));
        add(new Advertisement(someContent, "10", 400, 0, 3 * 60));
        add(new Advertisement(someContent, "11", 350, 100, 3 * 60)); // 3 min
        add(new Advertisement(someContent, "12", 1500, 10, 2 * 60)); //15 min
        add(new Advertisement(someContent, "13", 4600, 0, 10 * 60)); */

    }

    public List<Advertisement> list() {
        return videos;
    }

    public void add(Advertisement advertisement) {
        videos.add(advertisement);
    }
}
