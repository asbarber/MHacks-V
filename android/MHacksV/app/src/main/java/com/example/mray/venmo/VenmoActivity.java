package com.example.mray.venmo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Aaron Barber on 17/01/15.
 */
public class VenmoActivity {

    public static String access_token = "";

    public Intent authenticate(){
        String venmo_uri = "https://api.venmo.com/v1/oauth/authorize?client_id=2265&scope=make_payments&redirect_uri=myo";
        return new Intent(Intent.ACTION_VIEW, Uri.parse(venmo_uri));
    }

    public Intent transfer(String user_id, double amount){
        String venmo_uri = "https://api.venmo.com/v1/payments" +
                "?access_token=" + access_token +
                "&user_id=" + user_id +
                "&amount=" + Double.toString(amount);

        return new Intent(Intent.ACTION_VIEW, Uri.parse(venmo_uri));
    }
}
