package com.skykallove.misemise.Manager;

import android.app.Application;

import com.tsengvn.typekit.Typekit;

/**
 * Created by sky on 2018-05-21.
 */

public class FontManager extends Application {

    private String nanumPen = "NanumBarunGothic.ttf";

    @Override
    public void onCreate() {
        super.onCreate();
        Typekit.getInstance().addNormal(Typekit.createFromAsset(this, "fonts/" + nanumPen));
    }

}
