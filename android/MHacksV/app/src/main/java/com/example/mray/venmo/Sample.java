package com.example.mray.venmo;

/**
 * Created by Aaron Barber on 17/01/15.
 */
public class Sample {
    public void test(){
        Intent venmoIntent = VenmoLibrary.openVenmoPayment("2265", "MHacks", "145434160922624933", 1.00, "MHacks", "pay");
        startActivityForResult(venmoIntent, REQUEST_CODE_VENMO_APP_SWITCH);
    }
}
