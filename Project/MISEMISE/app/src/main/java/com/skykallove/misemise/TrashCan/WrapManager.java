package com.skykallove.misemise.TrashCan;

import android.content.ContentValues;

/**
 * Created by sky on 2018-05-29.
 */

public class WrapManager {

    private static String KEY = "5650416567736b793532486c4c7a71";
    private static String TYPE = "xml";
    private static String SERVICE = "RealtimeCityAir";
    private static String START_INDEX = "1";
    private static String END_INDEX = "10";

    public static ContentValues getRequestString(String msrrgn, String msrste) {

        ContentValues values = new ContentValues();

        values.put("msrrgn", msrrgn);
        values.put("msrste", msrste);

        return  values;
    }

}
