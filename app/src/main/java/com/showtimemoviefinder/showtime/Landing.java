package com.showtimemoviefinder.showtime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import java.util.Date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.android.volley.*;
import org.json.JSONArray;

import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;
import java.nio.charset.Charset;

import java.io.*;
import java.net.URL;
import java.net.HttpURLConnection;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import java.util.List;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Landing extends AppCompatActivity {

    public static RequestQueue requestQueue;
    JSONObject apiResult;
    public String urll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = Volley.newRequestQueue(this);
        setContentView(R.layout.activity_landing);
    }


    public void processZip(android.view.View view) {

        //Below is getting the zipcode from the user and then displaying it
        EditText inputZip = (EditText) findViewById(R.id.movieInput);
        String zipCode = inputZip.getText().toString();
        TextView output = (TextView) findViewById(R.id.output);


        //WOrking with the api
        String parameters = String.format(zipCode);
        getResponse(parameters);
    }

    protected void getResponse(String parameters) {
        try {
            String url;
            apiResult = new JSONObject();
            HttpURLConnection urlConnection = null;
            String baseUri = "http://data.tmsapi.com/v1.1/movies/showings?startDate=";
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String[] dateArr = dateFormat.format(date).split(" "); //2016/11/16 12:08:43

            String startDate = dateArr[0];

            String apiKey = "hxth5r3gjxaer3p9mcsmtezr";
            final StringBuffer result = new StringBuffer();
            try {
                url = String.format("%s%s&zip=%s&api_key=%s", baseUri, startDate, parameters, apiKey);
                urll = url;
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.GET,
                        url,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(final JSONObject response) {
                                apiResult = response;
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(final VolleyError error) {
                        System.out.print(error.toString());
                    }
                });
                requestQueue.add(jsonObjectRequest);
            } catch (Exception e) {
                e.printStackTrace();
            }
            gotResult();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        /** ////////////////////////////////////
         * ////////////////////////////////
         * Below is the stuff you need to deal with. Right now, all it does right is display the json result.
         * You can even run the app and see for yourself.
         * Can you look through the documentation on the api website and look into separating these parts out to display
         * what is listed on the google doc?
         * I figured that because we dont have a lot of time, we should focus on the basics.
         * ///////////////////////////////////////////////////
         * //////////////////////////////////////////////////////////
         *               ////////////////////////////////////////////////////////
         *                        //////////////////////////////////////////////////////////////////////
         */
        private void gotResult(){
            setContentView(R.layout.activity_theatre_results);
            TextView theatreList = (TextView) findViewById(R.id.theatreList);
            try {
                theatreList.setText(apiResult.toString());
            } catch (Exception e) {
                theatreList.setText("error");
            }

//        List<Movie> movies = new ArrayList<>();
//        for (int i = 0; i < result.length(); i++) {
//            movies.add(new Movie(result[i].get("title"), ))
//        }
//        ArrayAdapter<JSONObject> adapter = new ArrayAdapter<String>(this,
//                findViewById(R.id.theatreList), movies);
        }
    }

