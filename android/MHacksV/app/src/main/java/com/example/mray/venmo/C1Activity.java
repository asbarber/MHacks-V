package com.example.mray.venmo;

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
public class C1Activity {

    public void transfer(double amount){
        String venmo_uri = "http://api.reimaginebanking.com/customers/54b604dfa520e02948a0f46f/accounts" +
                "?key=CUST8f8986b581bc84bb936916e0f7e8872f";

        Log.d("poop",venmo_uri);

        HttpClient httpclient = new DefaultHttpClient();

        HttpGet get = new HttpGet(venmo_uri);
        get.addHeader( "Content-Type" , "application/json" );
        try {
            HttpResponse response = httpclient.execute(get);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}