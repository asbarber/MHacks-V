package com.example.mray.mhacksv;

import android.app.Activity;
import android.widget.TextView;

import com.example.mray.datasources.VenmoConnection;
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
public class HackerCenter extends AbstractDeviceListener {
    private Activity activity;
    private TextView connection_status;
    private Firebase myFirebaseRef;

    //private final String name = "Katelyn";            //Katelyn
    private final String name = "Sean";                 //Sean

    //private final String mac = "DF:34:F2:52:47:20";   //Katelyn
    private final String mac = "E6:35:8E:89:45:58";     //Sean

    public static final String payer_id = "1599880231982727210";   //Michael/Sean
    public static final String receiver_id = "1599750746931200618";   //Aaron/Katelyn

    public static final String payer_access_token = "apurVUx4wRTdBWz9HJ9sS4uTnpKHSVQH"; //Michael/Sean
    public static final String receiver_access_token = "8V3FreePyReTgcpuPB4x4KNPcGJ63qZd";   //Aaron/Katelyn

    private boolean katelynActive;
    private boolean seanActive;

    private void detHandshake() {
        if (seanActive && katelynActive) {
            myFirebaseRef.child("GlassInit").setValue(true);
            myFirebaseRef.child("Katelyn").child("Handshake").setValue(false);
            myFirebaseRef.child("Sean").child("Handshake").setValue(false);
        }
    }

    public HackerCenter(Activity activity) {
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
        myFirebaseRef.child("GlassDone").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                myFirebaseRef.child("GlassDone").setValue(false);
                if (myFirebaseRef.child(name).child("Method").equals("menu_payment")) {
                    String payment = null;
                    //payment = myFirebaseRef.child(name).child("Payment");
                    VenmoConnection va = new VenmoConnection();
                    va.execute(payment);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) { }
        });
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
