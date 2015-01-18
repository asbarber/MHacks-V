package com.example.mray.mhacksvglass;

import android.app.Activity;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by mray on 17/01/15.
 */
public class DatabaseManager {

    private Firebase firebaseRef;
    private Activity activity;
    private int counter;

    public DatabaseManager(Activity activity) {
        counter = 0;
        this.activity = activity;
        firebaseRef = new Firebase("https://mhacksv.firebaseio.com/");
        firebaseRef.child("GlassInit").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (counter > 0) {
                    startGlass();
                    firebaseRef.child("GlassInit").setValue(false);
                    firebaseRef.child("GlassHandle").setValue(true);
                } else
                    counter++;
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) { }
        });
    }



    private void startGlass() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "What kind of transaction?\nSend Money (Venmo)\nNetwork (LinkedIn)\nFinancial (Cap. One)\nResume");

        activity.startActivityForResult(intent, 0);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "How much?");



        if (getIntent() != null && getIntent().getExtras() != null) {
            ArrayList<String> voiceResults = getIntent().getExtras()
                    .getStringArrayList(RecognizerIntent.EXTRA_RESULTS);

            Log.d("poop", voiceResults.get(0));

        }

    }

    private Intent getIntent() {
        return null;
    }
}



