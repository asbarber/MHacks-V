package com.example.mray.venmo;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by Aaron Barber on 17/01/15.
 */
public class Sample extends Activity {
    public void test(){
        Intent venmoIntent = VenmoLibrary.openVenmoPayment("2265", "MHacks", "145434160922624933", "1.00", "MHacks", "pay");
        startActivityForResult(venmoIntent, 0);// REQUEST_CODE_VENMO_APP_SWITCH);
    }
}
