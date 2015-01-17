package com.example.mray.venmo;

import android.content.Intent;
import android.net.Uri;

import com.example.mray.mhacksv.MyoListener;

/**
 * Created by Aaron Barber on 17/01/15.
 */
public class VenmoActivity {

    public Intent transfer(String payer, String payee, double amount){
        String venmo_uri = "https://api.venmo.com/v1/payments" +
                "?access_token=" + payer +
                "&user_id=" + payee +
                "&amount=" + Double.toString(-1*Math.abs(amount)) +
                "&note=" + "MHacks";

        return new Intent(Intent.ACTION_VIEW, Uri.parse(venmo_uri));
    }
}
