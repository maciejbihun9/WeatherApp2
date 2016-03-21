package com.example.maciejbihun.weatherapp;

import android.Manifest;
import android.app.IntentService;
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
    public static final String LOCATION_LATITUDE = "latitude";
    public static final String LOCATION_LONGITUDE = "longitude";
    public static final String MyPREFERENCES = "preferences";

    public static void getLocationWeather(Context mContext){

    }

    public UserLocation(Context mContext){
      this.mContext = mContext;
      LocationManager lm = (LocationManager) mContext.getSystemService(mContext.LOCATION_SERVICE);
      if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
          return;
      }
      lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME,
              LOCATION_REFRESH_DISTANCE, mLocationListener);
    }

    public double getUserLocation(){
        return
    }

    //do something on user location change
    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            //put user location to service
            Intent i = new Intent(mContext.getApplicationContext(), DownloadWeatherService.class);
            i.putExtra(LOCATION_LATITUDE, location.getLatitude());
            i.putExtra(LOCATION_LONGITUDE, location.getLongitude());
            mContext.startService(i);
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
