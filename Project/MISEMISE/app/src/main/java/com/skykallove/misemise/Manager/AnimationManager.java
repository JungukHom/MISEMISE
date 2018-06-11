package com.skykallove.misemise.Manager;

import android.util.Log;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by sky on 2018-03-27.
 */

public class AnimationManager {

    public static void setAnimation(Button button, int resID) {
        button.setAnimation(AnimationUtils.loadAnimation(button.getContext(), resID));
    }

    public static void setAnimation(EditText editText, int resID) {
        editText.setAnimation(AnimationUtils.loadAnimation(editText.getContext(), resID));
    }

    public static void setAnimation(TextView textView, int resID) {
        textView.setAnimation(AnimationUtils.loadAnimation(textView.getContext(), resID));
    }

    public static void setAnimation(ImageView imageView, int resID) {
        imageView.setAnimation(AnimationUtils.loadAnimation(imageView.getContext(), resID));
    }

}
