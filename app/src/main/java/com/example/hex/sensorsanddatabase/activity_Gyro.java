package com.example.hex.sensorsanddatabase;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class activity_Gyro extends AppCompatActivity implements SensorEventListener {

    private TextView txtXLabel;
    private TextView txtYLabel;
    private TextView txtZLabel;
    private TextView txtXValue;
    private TextView txtYValue;
    private TextView txtZValue;

    private SensorManager mSensorManager;


    // Gravity rotational data
    private float gravity[];
    // Magnetic rotational data
    private float magnetic[]; //for magnetic rotational data
    private float accels[] = new float[3];
    private float mags[] = new float[3];
    private float[] values = new float[3];

    // azimuth, pitch and roll
    private float azimuth;
    private float pitch;
    private float roll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__gyro);
        txtXLabel = findViewById(R.id.textView3);
        txtYLabel = findViewById(R.id.textView4);
        txtZLabel = findViewById(R.id.textView2);
        txtXValue = findViewById(R.id.textView6);
        txtYValue = findViewById(R.id.textView7);
        txtZValue = findViewById(R.id.textView5);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }




    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)accels = event.values.clone();
        else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) mags = event.values.clone();

        if (mags != null && accels != null) {
            gravity = new float[9];
            magnetic = new float[9];
            SensorManager.getRotationMatrix(gravity, magnetic, accels, mags); // przeksztalceeni macierzy rotacji na orthonormalne wspolrzedne powszednei uzywane
            float[] outGravity = new float[9];
            SensorManager.remapCoordinateSystem(gravity, SensorManager.AXIS_X, SensorManager.AXIS_Z, outGravity); // przeksztalcenie maceirzy obrotu do innego ukladu wspolrzednych
            SensorManager.getOrientation(outGravity, values); // wylicza nasze katy w radianach

            azimuth = values[0] * 57.2957795f;
            pitch = values[1] * 57.2957795f;
            roll = values[2] * 57.2957795f;
            mags = null;
            accels = null;

            txtXValue.setText(Integer.toString((int)pitch));
            txtYValue.setText(Integer.toString((int)azimuth));
            txtZValue.setText(Integer.toString((int)roll));
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        Sensor accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) mSensorManager.registerListener(this, accelerometer,SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_FASTEST);

        Sensor magneticField = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (magneticField != null) mSensorManager.registerListener(this, magneticField,SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
