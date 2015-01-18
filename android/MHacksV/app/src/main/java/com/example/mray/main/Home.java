package com.example.mray.main;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.mray.mhacksv.R;
import com.firebase.client.Firebase;
import com.thalmic.myo.Hub;

public class Home extends ActionBarActivity {
    private Hub hub;
    private HackerCenter hackerCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Firebase.setAndroidContext(this);
        initHub();
        setupEventListener();

    }

    private void initHub() {
        hub = Hub.getInstance();
        if (!hub.init(this)) {
            finish();
            return;
        }
        hub.setLockingPolicy(Hub.LockingPolicy.NONE);
        hub.setMyoAttachAllowance(2);
        return;
    }

    public void connect1(View view) {
        for (int i=0; i<hub.getConnectedDevices().size(); i++) {
            hub.detach(hub.getConnectedDevices().get(i).getMacAddress());
        }
        hub.attachByMacAddress("DF:34:F2:52:47:20");
    }

    public void connect2(View view) {
        for (int i=0; i<hub.getConnectedDevices().size(); i++) {
            hub.detach(hub.getConnectedDevices().get(i).getMacAddress());
        }
        hub.attachByMacAddress("E6:35:8E:89:45:58");
    }

    public void glass(View view) {
        hackerCenter.triggerGlass();
    }

    private void setupEventListener() {
        hackerCenter = new HackerCenter(this);
        hub.addListener(hackerCenter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        hub.removeListener(hackerCenter);
        super.onDestroy();
    }
}
