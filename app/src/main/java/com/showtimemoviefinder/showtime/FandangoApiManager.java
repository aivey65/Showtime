package com.showtimemoviefinder.showtime;

import java.util.Date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.io.*;
import java.net.URL;
import java.net.HttpURLConnection;

import android.os.AsyncTask;

public class FandangoApiManager extends AsyncTask<String, Integer, String> {

    public FandangoApiResultDelegate delegate = null;

    protected String getResponse(String parameters) {

        String baseUri = "http://data.tmsapi.com/v1.1/movies/showings?startDate=";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String[] dateArr = dateFormat.format(date).split(" "); //2016/11/16 12:08:43

        String startDate =dateArr[0];

        String apiKey = "hxth5r3gjxaer3p9mcsmtezr";
        StringBuffer result = new StringBuffer();
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(String.format("%s%s&zip=%s&api_key=%s", baseUri, startDate, parameters, apiKey));
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection = (HttpURLConnection) url
                    .openConnection();

            InputStream in = urlConnection.getInputStream();

            InputStreamReader isw = new InputStreamReader(in);

            int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                data = isw.read();
                result.append(current);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return result.toString();
    }

    @Override
    protected String doInBackground(String... arg0) {
        return getResponse(arg0[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        if (delegate != null) {
            delegate.gotResult(result);
        } else {
            System.out.println("Got a result, but sadly no one to give it to");
        }
    }


}
