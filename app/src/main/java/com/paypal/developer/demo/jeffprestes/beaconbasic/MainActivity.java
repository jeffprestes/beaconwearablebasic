package com.paypal.developer.demo.jeffprestes.beaconbasic;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    private Button btnMonitoring;
    private Button btnRanging;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnMonitoring = (Button) findViewById(R.id.btnMonitoring);
        btnRanging = (Button) findViewById(R.id.btnRanging);

        //Defining listeners
        btnMonitoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMonitoring(v);
            }
        });

        btnRanging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRanging(v);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    /**
     * This will open Beacon Monitoring Page
     * @param v
     */
    public void startMonitoring(View v)     {
        Intent intent = new Intent(this, Monitoring.class);
        startActivity(intent);
    }

    /**
     * This will open Beacon Ranging Page
     * @param v
     */
    public void startRanging(View v)    {

    }
}
