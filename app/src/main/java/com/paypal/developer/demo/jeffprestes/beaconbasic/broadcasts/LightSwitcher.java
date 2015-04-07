package com.paypal.developer.demo.jeffprestes.beaconbasic.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;

import com.paypal.developer.demo.jeffprestes.beaconbasic.BeaconBasicApplication;
import com.paypal.developer.demo.jeffprestes.beaconbasic.notifiers.PopupAlerts;
import com.paypal.developer.demo.jeffprestes.beaconbasic.util.SimpleMqttClient;

import org.eclipse.paho.client.mqttv3.MqttClient;

public class LightSwitcher extends BroadcastReceiver {
    public LightSwitcher() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManagerCompat.from(context).cancel(PopupAlerts.NOTIFICATION_ID);

        if (intent != null && intent.hasExtra("message"))   {

            SimpleMqttClient mqttClient = BeaconBasicApplication.getMqttClient();
            String msg = intent.getStringExtra("message");
            mqttClient.publish(msg);
            return;

        }

    }
}
