package com.skykallove.misemise.Manager;

import android.content.ContentValues;
import android.os.AsyncTask;

/**
 * Created by sky on 2018-05-29.
 */

public class AsyncManager implements AsyncRequestTask.AsyncResponse{

    private static AsyncManager instance = null;

    private AsyncManager() {
    }

    public static AsyncManager getInstance() {
        return (instance == null ? instance = new AsyncManager() : instance);
    }

    public String make(String url, ContentValues values) {
        String result = "";
        AsyncRequestTask task = new AsyncRequestTask("", new ContentValues(), this);
        return  result;
    }

    @Override
    public void onTaskFinish(String result) {
        // TODO: 2018-05-29 onTaskFinish
    }
}
