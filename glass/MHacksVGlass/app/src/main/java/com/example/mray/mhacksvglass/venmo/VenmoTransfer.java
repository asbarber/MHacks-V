package com.example.mray.mhacksvglass.venmo;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Aaron Barber on 17/01/15.
 */
public class VenmoTransfer {

    /**
     * Starts the intent for a Venmo transfer.
     *
     * @param c context to start the activity
     * @param user_id phone number / email / id of the other end of the transaction
     * @param amount value for the transaction
     * @param pay true if the caller is paying money, false if the caller is receiving money
     */
    public void startTransfer(Context c, String user_id, double amount, boolean pay){
        if (!VenmoLibrary.isVenmoInstalled(c)){
            return;
        }

        String payType = pay ? "pay" : "charge";
        Intent intent = VenmoLibrary.openVenmoPayment("2265", "MHacks", user_id, Double.toString(amount), "MHacks", payType);
        c.startActivity(intent);
    }
}
