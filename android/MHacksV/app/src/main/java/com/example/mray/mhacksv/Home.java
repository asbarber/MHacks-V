package com.example.mray.mhacksv;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.mray.venmo.C1Activity;
import com.example.mray.venmo.VenmoActivity;
import com.firebase.client.Firebase;
import com.thalmic.myo.Hub;

public class Home extends ActionBarActivity {
    private Hub hub;
    private MyoListener myoListener;

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

    public void sendVenmo(View view) {
        refresh task = new refresh();
        task.execute();
        //startActivityForResult(venmoIntent, 0);
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
    protected void onDestroy() {
        hub.removeListener(myoListener);
        super.onDestroy();
    }

    private class refresh extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void...params) {
            C1Activity c1 = new C1Activity();
            VenmoActivity vm = new VenmoActivity();
            vm.transfer(0.01);
            return null;
        }
        protected void onProgressUpdate(Void...progress) {

        }
        @Override
        protected void onPostExecute(Void result) {

        }
    }
}
