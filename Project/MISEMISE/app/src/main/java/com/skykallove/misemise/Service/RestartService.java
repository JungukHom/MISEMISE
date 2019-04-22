package com.skykallove.misemise.Service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by developer0223 on 2019-04-11.
 */

public class RestartService extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        /*
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Intent _intent = new Intent(context, AlarmService.class);
            context.startService(_intent);
        }
        */

        /**
         * 서비스 죽일때 알람으로 다시 서비스 등록
         */
        if (intent.getAction().equals("ACTION.RESTART.AlarmService")) {
            Intent i = new Intent(context, AlarmService.class);
            context.startService(i);
        }

        /**
         * 폰 재시작 할때 서비스 등록
         */
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Intent i = new Intent(context, AlarmService.class);
            context.startService(i);
        }
    }
}
