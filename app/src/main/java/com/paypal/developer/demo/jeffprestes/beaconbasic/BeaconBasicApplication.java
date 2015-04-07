package com.paypal.developer.demo.jeffprestes.beaconbasic;

import android.app.Application;

import com.paypal.developer.demo.jeffprestes.beaconbasic.util.SimpleMqttClient;

/**
 * Created by jprestes on 4/5/15.
 */
public class BeaconBasicApplication extends Application {

    private static BeaconBasicApplication app;
    private static SimpleMqttClient mqttClient;

    @Override
    public void onCreate()  {

        super.onCreate();

        app = this;
        mqttClient = new SimpleMqttClient(this);

    }


    public static SimpleMqttClient getMqttClient()  { return mqttClient; }

    public static BeaconBasicApplication getApp()  {
        return app;
    }

    @Override
    public void onTerminate()   {
        mqttClient.disconnect();
        super.onTerminate();
    }
}
