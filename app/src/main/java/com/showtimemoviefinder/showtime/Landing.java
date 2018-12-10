package com.showtimemoviefinder.showtime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONObject;
import org.w3c.dom.Text;

public class Landing extends AppCompatActivity implements FandangoApiResultDelegate{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
    }


    public void processZip(android.view.View view) {
        //Include the things you want to do with the zip
        //1. Get the text within the text box "zipcodeInput".
        //2. Send this number to the API to get a list of movie theaters
        //3. Display 5 of these movie theaters. Maybe this will be on a different layout.

        FandangoApiManager api = new FandangoApiManager();

        //Below is getting the zipcode from the user and then displaying it
        EditText inputZip = (EditText) findViewById(R.id.zipcodeInput);
        String zipCode = inputZip.getText().toString();
        TextView output = (TextView) findViewById(R.id.output);



        //WOrking with the api
        api.delegate = this;
        String parameters = String.format(zipCode);
        api.execute(parameters);
    }

    /** ////////////////////////////////////
     * ////////////////////////////////
     * Hi Oishee! Below is the stuff you need to deal with. Right now, all it does right is display the json result.
     * You can even run the app and see for yourself.
     * Can you look through the documentation on the api website and look into separating these parts out to display
     * what is listed on the google doc?
     * I figured that because we dont have a lot of time, we should focus on the basics.
     * ///////////////////////////////////////////////////
     * @param result the json returned by the api
     * //////////////////////////////////////////////////////////
     *               ////////////////////////////////////////////////////////
     *                        //////////////////////////////////////////////////////////////////////
     */
    public void gotResult(String result) {
        setContentView(R.layout.activity_theatre_results);
        TextView theatreList = (TextView) findViewById(R.id.theatreList);
        theatreList.setText(result);
    }



    private static final String APP_ID = "6m2xw628ffg3dnzya2sp4duy";
}
