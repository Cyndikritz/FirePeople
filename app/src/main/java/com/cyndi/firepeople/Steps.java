package com.cyndi.firepeople;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Steps extends AppCompatActivity implements SensorEventListener{

    private int stepCounter = 0;
    private int counterSteps = 0;
    private int stepDetector = 0;
    TextView lblSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        lblSteps = findViewById(R.id.lblSteps);
    }

    public void onStart() {
        super.onStart();

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor= sensorManager .getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        sensorManager.registerListener(this, sensor, sensorManager.SENSOR_DELAY_NORMAL);

        lblSteps.setText(stepCounter+"");

        UpdateUI();
    }

    public void UpdateUI(){

        lblSteps.setText(stepCounter+"");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
            case Sensor.TYPE_STEP_DETECTOR:
                stepDetector++;
                break;
            case Sensor.TYPE_STEP_COUNTER:
                //Since it will return the total number since we registered we need to subtract the initial amount
                //for the current steps since we opened app
                if (counterSteps < 1) {
                    // initial value
                    counterSteps = (int)event.values [0];
                }

                // Calculate steps taken based on first counter value received.
                stepCounter = (int)event.values [0] - counterSteps;
                UpdateUI();
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
