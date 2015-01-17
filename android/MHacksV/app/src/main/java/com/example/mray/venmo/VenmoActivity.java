package com.example.mray.venmo;

import android.content.Intent;
import android.net.Uri;

/**
 * Created by Aaron Barber on 17/01/15.
 */
public class VenmoActivity {

    public static String access_token = "";

    public Intent authenticate(){
        String venmo_uri = "https://api.venmo.com/v1/oauth/authorize?client_id=2265&scope=make_payments&redirect_uri=myo&response_type=code";
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
