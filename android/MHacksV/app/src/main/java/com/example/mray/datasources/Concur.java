package com.example.mray.datasources;

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

    public void sendToConcur() {
        String body = "{\n" +
                "\n" +
                "  \"AttendeeTypeID\": \"gWjcKjoNtNnAHBquHboU08B17u1BpiA\",\n" +
                "\n" +
                "  \"Company\": \"Mios\",\n" +
                "\n" +
                "  \"CurrencyCode\": \"USD\",\n" +
                "\n" +
                "  \"FirstName\": \"Sean\",\n" +
                "\n" +
                "  \"LastName\": \"Hacker\",\n" +
                "\n" +
                "  \"MiddleInitial\": \"J\",\n" +
                "\n" +
                "  \"Title\": \"Software Engineer\",\n" +
                "\n" +
                "  \"TotalAmountYTD\": \"200.00\"\n" +
                "\n" +
                "}";

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), body);

        final Request request = new Request.Builder()
                .header("Accept", "application/json")
                .header("Authorization", "OAuth " + "Posk7nuv/BYzDAMNlFJWHpIMzzk=")
                .header("Content-Type", "application/json")
                .header("User-Agent", "Concur-Android-Mios")
                .url("https://www.concursolutions.com/api/v3.0/expense/attendees")
                .post(requestBody)
                .build();

        OkHttpClient client = new OkHttpClient();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.d("CONCUR", "Concur fail: " + e.toString());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String resultBody = response.body().string();

                Log.d("CONCUR", "Concur success: " + resultBody);
            }
        });
    }
}
