package com.example.android.myapplication;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;

/**
 * Created by sadat on 2/26/18.
 */
public class FetchMoviesTask extends AsyncTask<URL, Void, String> {

    /*Context context;
    private ProgressDialog dialog = new ProgressDialog(context);*/

    @Override
    protected String doInBackground(URL... urls) {
        String jsonResponse = null;
        URL inputurl = urls[0];
        try {
            jsonResponse = NetworkUtils.getResponseFromHttpUrl(inputurl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }

}