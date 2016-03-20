package com.example.maciejbihun.weatherapp;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by MaciekBihun on 2016-03-19.
 */
public class DownloadWeatherService extends IntentService {

    public DownloadWeatherService(){
        super("");
    }

    private ParseData mParseData;
    public DownloadWeatherService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("działa serwis?", "tak");
        mParseData = new ParseData(getApplicationContext());
        mParseData.showResultsInLog();
    }
}
