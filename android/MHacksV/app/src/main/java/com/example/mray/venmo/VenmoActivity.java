package com.example.mray.venmo;

import android.content.Intent;
import android.net.Uri;

import com.example.mray.mhacksv.MyoListener;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.net.URL;

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


        HttpClient httpclient = new DefaultHttpClient();
        HttpPost post = new HttpPost(venmo_uri);
        try {
            HttpResponse response = httpclient.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}