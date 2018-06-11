package com.skykallove.misemise.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by sky on 2018-06-09.
 */

public class AlarmService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        checkAlarm();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void checkAlarm() {
        while (true) {
            try {
                sleep(60000);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            // 시간 비교
            // 시간이 같으면
            // 푸쉬알람 보냄
        }
    }

    private void sleep(int timeMillisecs) throws Exception {
        try {
            Thread.sleep(timeMillisecs);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
