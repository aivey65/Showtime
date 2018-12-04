package com.showtimemoviefinder.showtime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.TextView;

public class Landing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        //can this set content view be used to change the screen to other layouts?
    }


    public void processZip(android.view.View view) {
        //Include the things you want to do with the zip
        //1. Get the text within the text box "zipcodeInput".
        //2. Send this number to the API to get a list of movie theaters
        //3. Display 5 of these movie theaters. Maybe this will be on a different layout.
        EditText ziptext = (EditText) findViewById(R.id.zipcodeInput);
        TextView zipoutput = (TextView) findViewById(R.id.output);
        zipoutput.setText(ziptext.getText());
    }


    /**
     * below is an example of how the api can be used, but we would use the input rather than a
     * @param result
     */
//    FandangoApiManager api = new FandangoApiManager();
//    api.delegate = this; // Be sure to implement "FandangoApiResultDelegate" in your activity declaration
//
//    String zipCode = "90064";
//    String parameters = String.format("op=theatersbypostalcodesearch&postalcode=%s", zipCode);
//
//        api.execute(parameters);



    public void gotResult(String result) {

        // Process response data...
    }

}
