package com.example.mray.mhacksvglass.datasources;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * Created by Aaron Barber on 17/01/15.
 */
public class VenmoConnection extends AsyncTask<String, Void, Void> {

    public static final String payer_id = "1599880231982727210";   //Michael/Sean
    public static final String receiver_id = "1599750746931200618";   //Aaron/Katelyn

    public static final String payer_access_token = "apurVUx4wRTdBWz9HJ9sS4uTnpKHSVQH"; //Michael/Sean
    public static final String receiver_access_token = "8V3FreePyReTgcpuPB4x4KNPcGJ63qZd";   //Aaron/Katelyn

    public void makeTransaction(String amount){
        String request = "https://api.venmo.com/v1/payments" +
                "?access_token=" + payer_access_token +
                "&user_id=" + receiver_id +
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