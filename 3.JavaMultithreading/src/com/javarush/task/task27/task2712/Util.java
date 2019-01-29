package com.javarush.task.task27.task2712;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    private  static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
    public static String dateformat(Date date){
        return  dateFormat.format(date);
    }
}
