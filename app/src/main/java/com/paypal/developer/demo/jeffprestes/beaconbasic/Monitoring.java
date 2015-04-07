package com.paypal.developer.demo.jeffprestes.beaconbasic;

import android.os.RemoteException;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.paypal.developer.demo.jeffprestes.beaconbasic.notifiers.MyBeaconNotifier;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Region;


public class Monitoring extends ActionBarActivity implements BeaconConsumer {

    protected static final String TAG = "MonitoringActivity";
    private BeaconManager beaconManager;
    private TextView statusView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring);
        beaconManager = BeaconManager.getInstanceForApplication(BeaconBasicApplication.getApp());

        if (beaconManager.getBeaconParsers() != null && beaconManager.getBeaconParsers().size()==1) {
            BeaconParser bp = new BeaconParser();
            //see: https://altbeacon.github.io/android-beacon-library/javadoc/org/altbeacon/beacon/BeaconParser.html#setBeaconLayout(java.lang.String)
            bp.setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24");

            beaconManager.getBeaconParsers().add(bp);
        }

        beaconManager.bind(this);
        beaconManager.setBackgroundScanPeriod(5000l);

        statusView = (TextView) findViewById(R.id.beaconStatus);

    }

    protected void onDestroy()  {
        super.onDestroy();
        beaconManager.unbind(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_monitoring, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBeaconServiceConnect() {

        beaconManager.setMonitorNotifier(new MyBeaconNotifier(statusView,this));
        Log.i(TAG, "Monitor Notifier was started");

        try {
            beaconManager.startMonitoringBeaconsInRegion(new Region("BeaconBasicMonitor", null, null, null));
            Log.i(TAG, "Monitoring Beacons has getting started");
        } catch (RemoteException e) {
            Log.e(TAG,"Error: " + e.getLocalizedMessage(), e);
        }
    }
}
