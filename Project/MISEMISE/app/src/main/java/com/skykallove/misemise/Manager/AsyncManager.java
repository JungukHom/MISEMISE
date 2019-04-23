package com.skykallove.misemise.Manager;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

/**
 * Created by sky on 2018-05-29.
 */

public class AsyncManager {

    private static AsyncManager instance = null;

    private AsyncManager() {
    }

    public static AsyncManager getInstance() {
        return (instance == null ? instance = new AsyncManager() : instance);
    }

    public String make(String url, ContentValues values) {
        String result = "";
        AsyncRequestTask task = new AsyncRequestTask(url, values);

        try {
            result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            return result;
        }
    }
}