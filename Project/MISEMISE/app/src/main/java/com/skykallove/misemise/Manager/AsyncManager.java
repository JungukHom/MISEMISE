package com.skykallove.misemise.Manager;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

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

    public void make(String url, ContentValues values) {
        AsyncRequestTask task = new AsyncRequestTask(this, url, values);
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskFinish(String result) {
        // TODO: 2018-05-29 onTaskFinish
        Log.i("testTest", result);
    }
}
