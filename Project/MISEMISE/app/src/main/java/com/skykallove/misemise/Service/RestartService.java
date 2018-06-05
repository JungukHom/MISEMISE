package com.skykallove.misemise.Service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by sky on 2018-06-05.
 */

public class RestartService extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Intent _intent = new Intent(context, AlarmService.class);
            context.startService(_intent);
        }
    }
}
