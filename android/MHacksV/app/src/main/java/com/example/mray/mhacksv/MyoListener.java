package com.example.mray.mhacksv;

import android.app.Activity;
import android.widget.TextView;

import com.thalmic.myo.AbstractDeviceListener;
import com.thalmic.myo.Myo;
import com.thalmic.myo.Pose;

/**
 * Created by mray on 17/01/15.
 */
public class MyoListener extends AbstractDeviceListener {
    private Activity activity;
    private TextView connection_status;

    public MyoListener(Activity activity) {
        this.activity = activity;
        this.connection_status = (TextView) activity.findViewById(R.id.connection_status);
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
        }
    }
}
