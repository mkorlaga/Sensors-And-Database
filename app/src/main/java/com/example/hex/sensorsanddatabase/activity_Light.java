package com.example.hex.sensorsanddatabase;

import android.content.res.Configuration;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class activity_Light extends AppCompatActivity implements SensorEventListener {

    private TextView txtValue;
    private View viewBackground;
    private Button btnStart;
    private Button btnStop;
    private SensorManager sensorManager;
    private Sensor lightSensor;

    private boolean flag;
    private float lightSensorValue;
    private float maxSensorRange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__light);

        txtValue = findViewById(R.id.textView);
        viewBackground = findViewById(R.id.backgroundView);
        btnStart = findViewById(R.id.button1);
        btnStop = findViewById(R.id.button2);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        if (lightSensor == null) {
            Toast.makeText(this, "Device has no light sensor!", Toast.LENGTH_SHORT).show();
            finish();
        }
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_FASTEST);
        maxSensorRange = lightSensor.getMaximumRange();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = true;
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = false;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
                lightSensorValue = event.values[0];

                txtValue.setText("Measured value is \n" + lightSensorValue + " lx");
                int newValue = (int) (255f * lightSensorValue / maxSensorRange);

                viewBackground.setBackgroundColor(Color.rgb(newValue, newValue, newValue));
                if (newValue > 120 && newValue < 136) newValue = 0;
                txtValue.setTextColor(Color.rgb(255 - newValue, 255 - newValue, 255 - newValue));
            }
        }
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE && flag == true){
            if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
                lightSensorValue = event.values[0];

                txtValue.setText("Measured value is \n" + lightSensorValue + " lx");
                int newValue = (int) (255f * lightSensorValue / maxSensorRange);

                viewBackground.setBackgroundColor(Color.rgb(newValue, newValue, newValue));
                if (newValue > 120 && newValue < 136) newValue = 0;
                txtValue.setTextColor(Color.rgb(255 - newValue, 255 - newValue, 255 - newValue));
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
