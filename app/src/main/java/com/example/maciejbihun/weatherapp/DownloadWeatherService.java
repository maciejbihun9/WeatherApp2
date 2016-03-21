package com.example.maciejbihun.weatherapp;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by MaciekBihun on 2016-03-19.
 */
public class DownloadWeatherService extends IntentService {

    public DownloadWeatherService(){
        super("");
    }

    public DownloadWeatherService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        double latitude = intent.getDoubleExtra(UserLocation.LOCATION_LATITUDE, 12.0);
        double longitude = intent.getDoubleExtra(UserLocation.LOCATION_LONGITUDE, 60);
        ParseData mParseData = new ParseData(getApplicationContext(), latitude, longitude);
        mParseData.showResultsInLog();
    }

    /*public static Intent startDownload(double latitude, double longitude){
        Intent i = new Intent();
        i.putExtra(LOCATION_LATITUDE, latitude);
        i.putExtra(LOCATION_LONGITUDE, longitude);
        return i;
    }*/
}
