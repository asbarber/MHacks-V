package com.example.mray.mhacksvglass.datasources;

import android.os.AsyncTask;

import com.example.mray.mhacksvglass.datasources.BankInfo;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Aaron Barber on 17/01/15.
 */
public class CapitalOneConnection extends AsyncTask<Void, Void, BankInfo> {

    private JSONArray makeJSON(HttpGet get){
        try {
            HttpClient client = new DefaultHttpClient();
            HttpResponse response = client.execute(get);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
            JSONTokener tokener = new JSONTokener(reader.readLine());
            return new JSONArray(tokener);
        } catch (IOException e) {
        } catch (JSONException e) {
        }

        return null;
    }

    public BankInfo gatherData(){
        BankInfo info = new BankInfo();

        String request;
        HttpGet get;
        JSONObject object;

        //Attempt to obtain balance
        try {
            request = "http://api.reimaginebanking.com/" +
                    "customers/54b604dfa520e02948a0f46f/accounts" +
                    "?key=CUST8f8986b581bc84bb936916e0f7e8872f";
            get = new HttpGet(request);
            get.addHeader( "Content-Type" , "application/json" );
            info.setBalance(makeJSON(get).getJSONObject(0).getDouble("balance"));
        } catch (JSONException e) {
        }

        //Attempt to obtain recent transition
        //...

        //Attempt to obtain upcoming bill
        try {
            request = "http://api.reimaginebanking.com:" +
                    "80/accounts/" +
                    "54b604e0a520e02948a0f7ea/bills" +
                    "?key=CUST8f8986b581bc84bb936916e0f7e8872f";

            get = new HttpGet(request);
            get.addHeader( "Content-Type" , "application/json" );
            info.setUpcomingPayment(makeJSON(get).getJSONObject(0).getString("upcoming payment date"));
        } catch (JSONException e) {
        }

        return info;
    }

    @Override
    protected BankInfo doInBackground(Void... params) {
        return gatherData();
    }
}