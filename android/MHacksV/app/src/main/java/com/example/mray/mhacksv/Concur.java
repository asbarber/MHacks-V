package com.example.mray.mhacksv;

import android.util.Log;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Mine on 1/17/15.
 */
public class Concur {

    private String requestData = "<Attendee><AttendeeTypeID>gWjcKjoNtNnAHBquHboU08B17u1BpiA</AttendeeTypeID><Company>Mios</Company><CurrencyCode>USD</CurrencyCode><FirstName>Sean</FirstName><LastName>Hacker</LastName><MiddleInitial>J</MiddleInitial><Title>Software Engineer</Title></Attendee>";

    public void sendConcurRequest() {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/xml"), requestData);

        final Request request = new Request.Builder()
                .header("Accept", "application/xml")
//                .header("Content-Type", "application/json")
                .header("Authentication", "OAuth " + "Posk7nuv/BYzDAMNlFJWHpIMzzk=")
                .header("User-Agent", "Concur-Android")
                .url("https://www.concursolutions.com/api/v3.0/expense/attendees")
                .post(requestBody)
                .build();

            OkHttpClient client = new OkHttpClient();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    Log.d("FOO", "Concur request failed: " + e.toString());
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    String resultBody = response.body().string();

                    Log.d("FOO", "Concur result = " + resultBody);
                }
            });
    }

}
