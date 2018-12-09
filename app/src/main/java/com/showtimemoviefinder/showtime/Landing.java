package com.showtimemoviefinder.showtime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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
        String parameters = String.format("op=theatersbypostalcodesearch&postalcode=%s", zipCode);
        output.setText(api.getResponse(parameters));
        //gotResult();
    }


    public void gotResult(String result) {
        TextView output = (TextView) findViewById(R.id.output);
        output.setText(result);
    }



    private static final String APP_ID = "6m2xw628ffg3dnzya2sp4duy";
}
