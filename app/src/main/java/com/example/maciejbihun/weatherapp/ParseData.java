package com.example.maciejbihun.weatherapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by MaciekBihun on 2016-03-19.
 */
public class ParseData {

    private static final String URL = "https://api.forecast.io/forecast/";
    private static final String API_KEY = "f069b3cc99e5c80041dd1f326e7a4237";
    private static final String LONGITUDE = "16.92464907349081";
    private static final String LATITUDE = "50.9652106914855";
    private static final String TAG = "ParseData";

    private SharedPreferences prefs;

    private Context mContext;
    public ParseData(Context mContext){
        this.mContext = mContext;
        prefs = mContext.getSharedPreferences(UserLocation.MyPREFERENCES, Context.MODE_PRIVATE);
    }

    //wczytywanie danych z url do tablicy bajtów.
    private byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        //open connection
        HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            //loads a portion of data from input to output
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            Log.i("getByteArray", out + " ");
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }

    }

    //zamienia bajty na stringa
    private String getUrl(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    /*public ArrayList<GalleryItem> downloadWeather(String url) {
        ArrayList<GalleryItem> items = new ArrayList<GalleryItem>();
        try {
            //tworzy stringa z otrzymanych danych
            String xmlString = getUrl(url);
            Log.i(TAG, "Received xml: " + xmlString);
            //parse received bytes to String. Parser takes it and parse it from xml to model object.
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(xmlString));
            parseItems(items, parser);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);
        } catch (XmlPullParserException xppe) {
            Log.e(TAG, "Failed to parse items", xppe);
        }
        return items;
    }*/

    private double getUserLatitude(){
        return prefs.getFloat(UserLocation.LOCATION_LATITUDE, 0);
    }

    private double getUserLongitude(){
        return prefs.getFloat(UserLocation.LOCATION_LONGITUDE, 0);
    }

    private String buildUrl(){
        return URL+API_KEY+"/"+getUserLatitude()+","+getUserLongitude();
        //return URL+API_KEY+"/"+LATITUDE+","+LONGITUDE;
    }

    public void showResultsInLog(){
        String xmlString = null;
        try {
            xmlString = getUrl(buildUrl());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "Received xml: " + xmlString);
    }

    /*public ArrayList<GalleryItem> fetchItems() {
// Move code here from above
        String url = Uri.parse(URL).buildUpon()
                .appendQueryParameter("api_key", API_KEY)
                .appendQueryParameter("method", METHOD_GET_RECENT)
                .appendQueryParameter(PARAM_EXTRAS, EXTRA_SMALL_URL)
                .build().toString();
        return downloadWeather(url);
    }*/

}
