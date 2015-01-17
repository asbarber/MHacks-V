package com.example.mray.mhacksvglass.temp;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Sean on 1/17/15.
 */
public class linkedinapistuff {

    private JSONObject getResponse(String server) {
        String line = "";
        JSONObject json = null;
        try {
            URL url = new URL(server);
            BufferedReader br = new BufferedReader(new InputStreamReader((url.openStream())));
            String strTemp = "";
            while (null != (strTemp = br.readLine())) {
                line += strTemp;
            }
            if (line.isEmpty()) return null;
            Log.d("poop", line);
            json = new JSONObject(line);
        } catch (Exception e) {

        }
        return json;
    }

    public void getData() {
        // To get the data:
        JSONObject json = getResponse("http://api.linkedin.com/v1/people/~:(first-name,last-name,headline,picture-url)?format=json");
        String lastname = null;
        String firstname = null;
        String headline = null;
        String picture_url = null;
        Log.d("poop", json.toString());

        try {
            lastname = json.getString("last-name");
            firstname = json.getString("first-name");
            headline = json.getString("headline");
            picture_url = json.getString("picture-url");
            Log.d("poop", lastname);
        } catch (JSONException e) {

        }

    }
}
