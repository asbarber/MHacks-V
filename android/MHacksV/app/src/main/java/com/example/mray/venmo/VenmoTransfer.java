package com.example.mray.venmo;

import android.content.Intent;

/**
 * Created by Aaron Barber on 17/01/15.
 */
public class VenmoTransfer {

    /**
     * Creates an intent to start a venmo transfer.
     * @param user_id phone number / email / id of the other end of the transaction
     * @param amount value for the transaction
     * @param pay true if the caller is paying money, false if the caller is receiving money
     * @return Intent to call to start the transfer, NULL if no venmo app or unable to process
     */
    public Intent createIntent(String user_id, double amount, boolean pay){
        String payType = pay ? "pay" : "charge";
        return VenmoLibrary.openVenmoPayment("2265", "MHacks", user_id, Double.toString(amount), "MHacks", payType);
        //startActivityForResult(venmoIntent, 0);// REQUEST_CODE_VENMO_APP_SWITCH);
    }
}
