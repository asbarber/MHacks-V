package com.example.mray.mhacksv;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.thalmic.myo.AbstractDeviceListener;
import com.thalmic.myo.Myo;
import com.thalmic.myo.Pose;

/**
 * Created by mray on 17/01/15.
 */
public class MyoListener extends AbstractDeviceListener {
    private Activity activity;
    private TextView connection_status;
    //private final String name = "Katelyn";
    private final String name = "Sean";
    //private final String mac = "DF:34:F2:52:47:20"; //Katelyn
    private final String mac = "E6:35:8E:89:45:58"; //Sean
    private Firebase myFirebaseRef;

    private boolean katelynActive;
    private boolean seanActive;

    private void detHandshake() {
        if (seanActive && katelynActive) {
            handshakeOccured();
            myFirebaseRef.child("Katelyn").child("Handshake").setValue(false);
            myFirebaseRef.child("Sean").child("Handshake").setValue(false);
        }
    }

    public MyoListener(Activity activity) {
        this.activity = activity;
        this.connection_status = (TextView) activity.findViewById(R.id.connection_status);
        katelynActive = false;
        seanActive = false;
        myFirebaseRef = new Firebase("https://mhacksv.firebaseio.com/");
        myFirebaseRef.child("Katelyn").child("Handshake").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                katelynActive = (boolean)dataSnapshot.getValue();
                detHandshake();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) { }
        });
        myFirebaseRef.child("Sean").child("Handshake").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                seanActive = (boolean)dataSnapshot.getValue();
                detHandshake();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) { }
        });
    }

    private void handshakeOccured() {
        Log.d("poop","HANDSHAKE!!");
    }

    @Override
    public void onConnect(Myo myo, long timestamp) {
        connection_status.setText("Connected to: " + myo.getName() + " at " + timestamp);
        myo.vibrate(Myo.VibrationType.SHORT);
    }

    @Override
    public void onDisconnect(Myo myo, long timestamp) {
        connection_status.setText("Disconnected from: " + myo.getName() + " at " + timestamp);
    }

    @Override
    public void onPose(Myo myo, long timestamp, Pose pose) {
        if (pose.equals(Pose.FIST)) {
            myo.vibrate(Myo.VibrationType.MEDIUM);

            Thread thread = new Thread(){
                @Override
                public void run() {
                    myFirebaseRef.child(name).child("Mac").setValue(mac);
                    myFirebaseRef.child(name).child("Handshake").setValue(true);
                    myFirebaseRef.child(name).child("Venmo ID").setValue("0x000000");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) { }
                    myFirebaseRef.child(name).child("Handshake").setValue(false);
                }
            };
            thread.start();
        }
    }
}
