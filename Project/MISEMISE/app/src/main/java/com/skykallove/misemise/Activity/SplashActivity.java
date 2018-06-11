package com.skykallove.misemise.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.skykallove.misemise.Manager.IntentManager;
import com.skykallove.misemise.R;

/**
 * Created by sky on 2018-06-12.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ShowLoadingImageTask task = new ShowLoadingImageTask();
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private class ShowLoadingImageTask extends AsyncTask<String, String, String> {

        public ShowLoadingImageTask() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String value) {
            super.onPostExecute(value);
            // TODO: 2018-04-06 db connection check in server
            IntentManager.startActivity(getApplicationContext(), MainActivity.class);
            finish();
        }
    }
}
