package com.example.maciejbihun.weatherapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

/**
 * Created by MaciekBihun on 2016-03-19.
 */
public class UserLocation {

    private Context mContext;
    private static final int LOCATION_REFRESH_TIME = 10;
    private static final int LOCATION_REFRESH_DISTANCE = 10;

    //stałe do określenia danych w SharedPreferences.
    public static final String LOCATION_LATITUDE = "latitude";
    public static final String LOCATION_LONGITUDE = "longitude";
    public static final String MyPREFERENCES = "preferences";

    private SharedPreferences prefs;

    //inicjalizacja zmiennych przy każdym wywołaniu obiektu
    public UserLocation(Context mContext) {
        this.mContext = mContext;
        prefs = mContext.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        LocationManager lm = (LocationManager) mContext.getSystemService(mContext.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME,
                LOCATION_REFRESH_DISTANCE, mLocationListener);
    }

    //save user last location to sharedPreferences.
    private void saveUserLocation(double latitude, double longitude){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putFloat(LOCATION_LATITUDE, (float) latitude);
        editor.putFloat(LOCATION_LONGITUDE, (float) longitude);
        editor.commit();
    }

    //do something on user location change
    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            saveUserLocation(location.getLatitude(), location.getLongitude());
            Intent i = new Intent(mContext, DownloadWeatherService.class);
            mContext.startService(i);
            Log.i("Latitude", location.getLatitude() + "");
            Log.i("Longitude", location.getLongitude() + "");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
}
