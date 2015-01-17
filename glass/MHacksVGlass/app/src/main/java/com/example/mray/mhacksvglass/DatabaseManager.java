package com.example.mray.mhacksvglass;

import android.app.Activity;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by mray on 17/01/15.
 */
public class DatabaseManager {

    private Firebase firebaseRef;
    private Activity activity;

    public DatabaseManager(Activity activity) {
        this.activity = activity;
        firebaseRef = new Firebase("https://mhacksv.firebaseio.com/");
        firebaseRef.child("GlassInit").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                startGlass();
                firebaseRef.child("GlassInit").setValue(false);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) { }
        });
    }

    private void startGlass() {
    }

}
