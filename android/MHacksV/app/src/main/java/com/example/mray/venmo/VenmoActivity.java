package com.example.mray.venmo;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * Created by Aaron Barber on 17/01/15.
 */
public class VenmoActivity {

    public void transfer(String payer, String payee, double amount){
        String venmo_uri = "https://api.venmo.com/v1/payments" +
                "?access_token=" + payer +
                "&user_id=" + payee +
                "&amount=" + Double.toString(-1*Math.abs(amount)) +
                "&note=" + "MHacks";

        Log.d("poop",venmo_uri);

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost post = new HttpPost(venmo_uri);
        try {
            HttpResponse response = httpclient.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}