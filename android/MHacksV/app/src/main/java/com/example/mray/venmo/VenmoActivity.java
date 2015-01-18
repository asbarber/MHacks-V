package com.example.mray.venmo;

import android.os.AsyncTask;
import android.util.Log;

import com.example.mray.mhacksv.MyoListener;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * Created by Aaron Barber on 17/01/15.
 */
public class VenmoActivity extends AsyncTask<String, Void, Void> {

    public void transfer(String amount){
        String venmo_uri = "https://api.venmo.com/v1/payments" +
                "?access_token=" + MyoListener.payer_access_token +
                "&user_id=" + MyoListener.receiver_id +
                "&amount=" + amount +
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

    @Override
    protected Void doInBackground(String... params) {
        transfer(params[0]);
        return null;
    }
}