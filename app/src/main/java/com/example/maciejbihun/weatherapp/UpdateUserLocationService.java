package com.example.maciejbihun.weatherapp;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by MaciekBihun on 2016-03-19.
 */
public class UpdateUserLocationService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public UpdateUserLocationService(String name) {
        super(name);
    }

    //empty constructor for
    public UpdateUserLocationService(){
        super("");

    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
