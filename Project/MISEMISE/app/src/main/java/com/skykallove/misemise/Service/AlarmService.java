package com.skykallove.misemise.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.skykallove.misemise.Data.Url;
import com.skykallove.misemise.Manager.AirGradeManager;
import com.skykallove.misemise.Manager.AsyncManager;
import com.skykallove.misemise.Manager.CityLocationManager;
import com.skykallove.misemise.Manager.JSONManager;
import com.skykallove.misemise.Manager.URLParameterManager;
import com.skykallove.misemise.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by sky on 2018-06-09.
 */

public class AlarmService extends Service {

    private static final String ALARM_PREF_NAME = "alarmPrefName";
    private static final String ALARM_CITY_NAME = "alarmCityName";
    private static final String  ALARM_CITY_NAME_STRING = "alarmCityNameString";

    public static List<String> alarmTime = new ArrayList<>();

    public List<String> getAlarmTime() {
        return getMyAlarmTime();
    }

    public void addAlarmTime(String time) {
        alarmTime.add(time);
        saveMyAlarmTime(alarmTime);
    }

    public String getCityInfo() {
        SharedPreferences prefs = getSharedPreferences(ALARM_CITY_NAME_STRING, MODE_PRIVATE);
        return prefs.getString(ALARM_CITY_NAME_STRING, "강남구");
    }

    public void saveMyAlarmTime(List<String> info) {
        SharedPreferences pref = getSharedPreferences(ALARM_PREF_NAME, MODE_PRIVATE);
        Set<String> values = new HashSet<>();
        for (int i = 0; i < info.size(); i++) {
            values.add(info.get(i));
        }

        SharedPreferences.Editor editor = pref.edit();
        editor.putStringSet("alarm", values);
        editor.commit();
    }

    public List<String> getMyAlarmTime() {
        SharedPreferences pref = getSharedPreferences(ALARM_PREF_NAME, MODE_PRIVATE);
        Set<String> _result = pref.getStringSet("alarm", new HashSet<String>());

        List<String> result = new ArrayList<>();
        for (String str : _result) {
            result.add(str);
        }
        return result;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("test", "onBind");
        return null;
    }

    @Override
    public void onCreate() {
        Log.i("test", "onCreate");
        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("test", "onStartCommand");
        // startForeground(1,new Notification());

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Notification notification;

        notification = new Notification.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setContentTitle("")
                .setContentText("")
                .build();

        notificationManager.notify(startId, notification);
        notificationManager.cancel(startId);

        CheckTimeTask task = new CheckTimeTask();
        task.execute();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i("test", "onDestroy");
        super.onDestroy();
    }

    private class CheckTimeTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            while (true) {
                try {

                    SimpleDateFormat sdfNow = new SimpleDateFormat("HH:mm"); // "yyyy/MM/dd HH:mm:ss"
                    String time = sdfNow.format(new Date(System.currentTimeMillis()));

                    List<String> alarmTimes = getAlarmTime();
                    for (int i = 0; i < alarmTimes.size(); i++) {
                        if (time.equals(alarmTimes.get(i))) {
                            publishProgress();
                        }
                    }
                    sleep(60000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            showNotificationBar();
        }
    }

    private void showNotificationBar() {
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Notification notification;

        String city = getCityInfo();
        Map<String, String> parsedData = getParsedData(city);
        String titleQualityString = parsedData.get("IDEX_NM");
        String _titleQualityInt = parsedData.get("IDEX_MVL");
        int titleQualityInt = Integer.parseInt(_titleQualityInt);
        notification = new Notification.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.misemise_logo)
                .setContentTitle(city + "의 기상 상황은 " + titleQualityString + "입니다.")
                .setContentText(AirGradeManager.getGradeMessageWithGrade(AirGradeManager.getGradeWithWholeValue(titleQualityInt)))
                .build();

        notificationManager.notify(0, notification);
    }

    private void sleep(int timeMilliseconds) throws InterruptedException {
        try {
            Thread.sleep(timeMilliseconds);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<String, String> getParsedData(String gu) {

        AsyncManager manager = AsyncManager.getInstance();
        String nm = CityLocationManager.getNMbyCityName(gu);
        String a = manager.make(Url.REAL_TIME_CITY_AIR, URLParameterManager.getRequestString(nm, gu));

        Map<String, String> parsedData = JSONManager.parse(a);

        return parsedData;
    }
}
