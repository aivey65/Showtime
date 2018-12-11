package com.showtimemoviefinder.showtime;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

public class Landing extends AppCompatActivity {

    private static final String TAG = "Showtime:Main";
    private static RequestQueue requestQueue;
    private String json;

    /**
     * Run when our activity comes into view.
     *
     * @param savedInstanceState state that was saved by the activity last time it was paused
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up a queue for our Volley requests
        requestQueue = Volley.newRequestQueue(this);

        // Load the main layout for our activity
        setContentView(R.layout.activity_main);

        // Attach the handler to our UI button
        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final EditText edit = findViewById(R.id.MovieInput);
                String search = edit.getText().toString();
                search = search.replace(" ", "%20");
                startAPICall(search);
                TextView answer = findViewById(R.id.JsonResult);
                answer.setText(getDetails(json));
            }
        });
    }

    /**
     * Make an API call.
     * @param search fmdik
     */
    void startAPICall(String search) throws JSONException {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://api.themoviedb.org/3/search/movie?api_key="
                            + "4faf2feec28e411ec393552c6b2f4df0" + "&language=en-US&query=" + search + "&page=1&include_adult=false",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            try {
                                Log.d(TAG, response.toString(2));
                                json = response.toString();
                            } catch (JSONException ignored) {
                                throw ignored;
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.e(TAG, error.toString());
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getDetails(final String json) {
        if (json == null) {
            return "";
        }
        JsonParser parser = new JsonParser();
        JsonObject result = parser.parse(json).getAsJsonObject();
        if (result == null) {
            return "";
        }
        int total = result.get("total_results").getAsInt();
        if (total == 0) {
            return "Invalid movie choice";
        }
        JsonArray results = result.get("results").getAsJsonArray();
        JsonObject details = results.get(0).getAsJsonObject();
        String vote = details.get("vote_average").getAsString();
        String title = details.get("title").getAsString();
        String overview = details.get("overview").getAsString();
        String rdate = details.get("release_date").getAsString();

        return "Title" + title + "\n" + "Overview" + overview + "\n" + "Rating" + vote + "\n" + "Release Date" + rdate;
    }
}