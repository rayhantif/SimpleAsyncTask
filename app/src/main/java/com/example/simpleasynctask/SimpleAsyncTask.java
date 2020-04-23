package com.example.simpleasynctask;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Integer, String>{
    private WeakReference<TextView> mTextView;
    private WeakReference<ProgressBar> mProgressBar;
        SimpleAsyncTask(TextView tv, ProgressBar progress) {
        mTextView = new WeakReference<>(tv);
        mProgressBar = new WeakReference<>(progress);
        }

    @Override
    protected void onPreExecute() {
        mProgressBar.get().setVisibility(View.VISIBLE);
    }
    @Override
    protected String doInBackground(Void... voids) {
        Random r = new Random();
        int n = r.nextInt(10)+1;
        int sleepCount = n * 500;
        try{
            int totalCount = 0;
            int oneHundredth = sleepCount/100;
            while(totalCount < 100){
                Thread.sleep(oneHundredth);
                totalCount++;
                publishProgress(totalCount);

            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "Awake at last after sleeping for "+sleepCount+" milliseconds!";
    }
    @Override
    protected void onProgressUpdate(Integer... values) {

        mProgressBar.get().setProgress(values[0]);
    }



    @Override
    protected void onPostExecute(String s) {
        mTextView.get().setText(s);
        mProgressBar.get().setVisibility(View.INVISIBLE);
    }
}
