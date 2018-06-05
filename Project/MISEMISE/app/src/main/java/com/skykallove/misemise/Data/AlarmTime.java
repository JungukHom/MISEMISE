package com.skykallove.misemise.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sky on 2018-06-05.
 */

public class AlarmTime {

    private AlarmTime() {
    }

    public static List<String> alarmTime = new ArrayList<>();

    public static List<String> getAlarmTime() {
        return alarmTime;
    }

    public static void setAlarmTime(String time) {
        alarmTime.add(time);
    }

    public static void removeElement(String time) {
        alarmTime.remove(time);
    }

    public static void clear() {
        alarmTime.clear();
    }
}
