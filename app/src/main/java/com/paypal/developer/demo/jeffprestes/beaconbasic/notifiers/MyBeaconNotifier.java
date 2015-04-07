package com.paypal.developer.demo.jeffprestes.beaconbasic.notifiers;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;

import com.paypal.developer.demo.jeffprestes.beaconbasic.BeaconBasicApplication;
import com.paypal.developer.demo.jeffprestes.beaconbasic.R;

import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.Region;

/**
 * Created by jprestes on 3/16/15.
 */
public class MyBeaconNotifier implements MonitorNotifier {

    protected static final String TAG = "MyBeaconNotifier";
    private TextView statusView;
    private Activity clientActivity;
    private PopupAlerts popupAlerts;

    public MyBeaconNotifier(TextView v, Activity a)   {
        statusView = v;
        clientActivity = a;
        popupAlerts = new PopupAlerts();
    }

    @Override
    public void didEnterRegion(Region region) {
        Log.i(TAG, "I just saw an beacon for the first time!");

        clientActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                statusView.setTextAppearance(clientActivity, R.style.BeaconStatusBoxClose);
                statusView.setBackgroundColor(Color.GREEN);
                statusView.setText("Beacon found");
                Log.i("StatusBeacon", "Beacon found");
                popupAlerts.notifyArrivedAtBeaconRegion();
            }
        });

    }

    @Override
    public void didExitRegion(Region region) {
        Log.i(TAG, "I no longer see an beacon");
        clientActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                statusView.setTextAppearance(clientActivity, R.style.BeaconStatusBoxClose);
                statusView.setBackgroundColor(Color.LTGRAY);
                statusView.setText("No Beacon found");
                Log.i("StatusBeacon", "No Beacon found");
            }
        });
    }

    @Override
    public void didDetermineStateForRegion(int state, Region region) {
        Log.i(TAG, "I have just switched from seeing/not seeing beacons: " + state);
    }
}
