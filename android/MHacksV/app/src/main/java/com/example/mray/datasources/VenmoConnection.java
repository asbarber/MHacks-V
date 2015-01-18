package com.example.mray.datasources;

import android.os.AsyncTask;

import com.example.mray.mhacksv.HackerCenter;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * Created by Aaron Barber on 17/01/15.
 */
public class VenmoConnection extends AsyncTask<String, Void, Void> {

    public void makeTransaction(String amount){
        String request = "https://api.venmo.com/v1/payments" +
                "?access_token=" + HackerCenter.payer_access_token +
                "&user_id=" + HackerCenter.receiver_id +
                "&amount=" + amount +
                "&note=" + "MHacks";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(request);
        try {
            HttpResponse response = client.execute(post);
        } catch (IOException e) {
        }

    }

    @Override
    protected Void doInBackground(String... params) {
        if (params != null && params.length > 0) {
            makeTransaction(params[0]);
        }
        return null;
    }
}