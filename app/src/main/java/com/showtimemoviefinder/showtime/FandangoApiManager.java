package com.showtimemoviefinder.showtime;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.io.*;
import java.net.URL;
import java.net.HttpURLConnection;

import android.os.AsyncTask;

public class FandangoApiManager extends AsyncTask<String, Integer, String> {

    public FandangoApiResultDelegate delegate = null;

    protected String sha256Encode(String StringToEncode) throws NoSuchAlgorithmException {

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] buffUtf8Msg = digest.digest(StringToEncode.getBytes());
        String result = String.format("%0" + (buffUtf8Msg.length*2) + "X", new BigInteger(1, buffUtf8Msg));

        return result;
    }

    protected String buildAuthorizationParameters(String apiKey, String sharedSecret) throws NoSuchAlgorithmException {

        long seconds =  System.currentTimeMillis() / 1000;
        String paramsToEncode = apiKey + sharedSecret + seconds;
        String encodedParams = sha256Encode(paramsToEncode);

        String result = String.format("api_key=%s&sig=%s", apiKey, encodedParams);

        return result;
    }

    protected String getResponse(String parameters) {

        String baseUri = "http://api.fandango.com";
        String apiVersion = "1";

//        // DONE! (Use your account-specific values here)
        String apiKey = "6m2xw628ffg3dnzya2sp4duy";
        String sharedSecret = "t3rQUXAkFD";
//
//        //String result = null;
        StringBuffer result = new StringBuffer();

        try {

            String authorizationParameters = buildAuthorizationParameters(apiKey, sharedSecret);
            String requestUri = String.format("%s/v%s/?%s&%s", baseUri, apiVersion, parameters, authorizationParameters);

            URL url = new URL(requestUri);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = url.openStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while((line = reader.readLine()) != null) {
                    result.append(line);
                }
            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
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
