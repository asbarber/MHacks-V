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

    public Intent work(){
        String venmo_uri = "https://api.venmo.com/v1/oauth/authorize?client_id=HqgVfAsFVZQwYwnjMaHSDrXBs3gm5sPB&scope=make_payments%20access_profile";

        return new Intent(Intent.ACTION_VIEW, Uri.parse(venmo_uri));
    }
}
