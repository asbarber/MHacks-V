package com.example.mray.venmo;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * Created by Aaron Barber on 17/01/15.
 */
public class C1Activity extends AsyncTask<Void, Void, Void> {

    public void transfer(){
        String venmo_uri = "http://api.reimaginebanking.com/customers/54b604dfa520e02948a0f46f/accounts" +
                "?key=CUST8f8986b581bc84bb936916e0f7e8872f";

        HttpClient httpclient = new DefaultHttpClient();

        HttpGet get = new HttpGet(venmo_uri);
        get.addHeader( "Content-Type" , "application/json" );
        try {
            HttpResponse response = httpclient.execute(get);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected Void doInBackground(Void... params) {
        transfer();
        return null;
    }
}