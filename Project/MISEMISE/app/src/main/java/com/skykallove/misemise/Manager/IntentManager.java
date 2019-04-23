package com.skykallove.misemise.Manager;

import android.content.Context;
import android.content.Intent;

/**
 * Created by sky on 2018-03-27.
 */

public class IntentManager {
    public static void startActivity(Context context, Class forwardClass) {
        context.startActivity(new Intent(context, forwardClass));
    }

    public static void startService(Context context, Class service) {
        context.startService(new Intent(context, service));
    }
}
