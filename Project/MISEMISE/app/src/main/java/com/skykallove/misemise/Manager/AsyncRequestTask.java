package com.skykallove.misemise.Manager;

import android.content.ContentValues;
import android.os.AsyncTask;

/**
 * Created by sky on 2018-05-29.
 */

public class AsyncRequestTask extends AsyncTask<String, String, String> {

    public interface AsyncResponse {
        void onTaskFinish(String result);
    }

    public AsyncResponse delegate = null;

    private String url;
    private ContentValues values;

    public AsyncRequestTask(String url, ContentValues values, AsyncResponse delegate) {
        this.url = url;
        this.values = values;
        this.delegate = delegate;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        delegate.onTaskFinish(result);
    }
}
