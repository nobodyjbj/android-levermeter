package com.example.test.levelmetertest;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.FrameLayout;

public class MainActivity extends Activity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    private float xV = 0;
    private float yV = 0;

    FrameLayout frameLayout;
    MyView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = new MyView(this);
        frameLayout = (FrameLayout) findViewById(R.id.sensorLayout);
        frameLayout.addView(image);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();

        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Log.i("Sensor", "X axis = " + sensorEvent.values[0]);
        Log.i("Sensor", "Y axis = " + sensorEvent.values[1]);

        xV = sensorEvent.values[0];
        yV = sensorEvent.values[1];
        image.invalidate();

        if ( xV < 10 && xV > -10 ) {
            image.xV = xV;
        }

        if ( yV < 10 && yV > -10 ) {
            image.yV = yV;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}