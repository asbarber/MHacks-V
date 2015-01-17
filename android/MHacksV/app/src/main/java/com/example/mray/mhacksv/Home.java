package com.example.mray.mhacksv;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.thalmic.myo.Hub;

public class Home extends ActionBarActivity {
    private Hub hub;
    private MyoListener myoListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Intent venmoIntent = VenmoLibrary.openVenmoPayment("2265", "MHacks", "145434160922624933", "1.00", "MHacks", "pay");
       // startActivityForResult(venmoIntent, 0);// REQUEST_CODE_VENMO_APP_SWITCH);

        initHub();
        setupEventListener();
    }

    private void initHub() {
        TextView hub_status = (TextView)findViewById(R.id.hub_status);
        hub = Hub.getInstance();
        if (!hub.init(this)) {
            hub_status.setText("Hub could not be initialized");
            finish();
            return;
        }
        hub_status.setText("Hub initialized");
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

    private void setupEventListener() {
        myoListener = new MyoListener(this);
        hub.addListener(myoListener);

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
    protected void onStop() {
        hub.removeListener(myoListener);
        super.onStop();
    }
}
