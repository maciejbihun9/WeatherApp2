package com.example.maciejbihun.weatherapp;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by MaciekBihun on 2016-03-19.
 */
public class DownloadWeatherService extends IntentService {

    public static final String LOCATION_LATITUDE = "latitude";
    public static final String LOCATION_LONGITUDE = "longitude";
    public DownloadWeatherService(){
        super("");
    }

    public DownloadWeatherService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        double latitude = (double)intent.getSerializableExtra(LOCATION_LATITUDE);
        double longitude = (double)intent.getSerializableExtra(LOCATION_LONGITUDE);
        ParseData mParseData = new ParseData(getApplicationContext(), latitude, longitude);
        mParseData.showResultsInLog();
    }

    public static void startDownload(Context mContext, double latitude, double longitude){
        Intent i = new Intent(mContext, DownloadWeatherService.class);
        i.putExtra(LOCATION_LATITUDE, latitude);
        i.putExtra(LOCATION_LONGITUDE, longitude);
        mContext.startService(i);
    }
}
