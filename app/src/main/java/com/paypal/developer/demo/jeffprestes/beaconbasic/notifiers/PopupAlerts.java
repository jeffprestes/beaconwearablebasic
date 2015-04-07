package com.paypal.developer.demo.jeffprestes.beaconbasic.notifiers;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.paypal.developer.demo.jeffprestes.beaconbasic.BeaconBasicApplication;
import com.paypal.developer.demo.jeffprestes.beaconbasic.Monitoring;
import com.paypal.developer.demo.jeffprestes.beaconbasic.R;
import com.paypal.developer.demo.jeffprestes.beaconbasic.broadcasts.LightSwitcher;

/**
 * Sends pop up notifications to watch and handheld
 * Created by jprestes on 4/6/15.
 */
public class PopupAlerts    {

    static final String LIGHT_ON = "lighton";
    static final String LIGHT_OFF = "lightoff";
    public static final int NOTIFICATION_ID = 123456;

    public PopupAlerts()    {

    }

    public void notifyArrivedAtBeaconRegion()    {

        Context ctx = BeaconBasicApplication.getApp().getApplicationContext();

        Intent actionIntent = new Intent(ctx, LightSwitcher.class);
        actionIntent.putExtra("message", this.LIGHT_ON);

        PendingIntent actionPendingIntent = PendingIntent.getBroadcast( ctx,
                                                                        01,
                                                                        actionIntent,
                                                                        PendingIntent.FLAG_CANCEL_CURRENT );

        NotificationCompat.Builder notifBuilder = new NotificationCompat.Builder(ctx);

        notifBuilder.setSmallIcon(R.drawable.ic_launcher);
        notifBuilder.setContentIntent(actionPendingIntent);
        notifBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        notifBuilder.setCategory(NotificationCompat.CATEGORY_TRANSPORT);
        notifBuilder.addAction(R.drawable.ic_launcher_light_on, "Turn light On", actionPendingIntent);

        String tmp = ctx.getString(R.string.notificationArrivedAtBeaconRegionTitle);
        notifBuilder.setContentTitle(tmp);

        tmp = ctx.getString(R.string.notificationArrivedAtBeaconRegionText);
        notifBuilder.setContentText(tmp);


        NotificationManagerCompat notifManager = NotificationManagerCompat.from(ctx);

        notifManager.notify(this.NOTIFICATION_ID, notifBuilder.build());
    }

}
