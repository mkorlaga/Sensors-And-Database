package com.example.hex.sensorsanddatabase;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class activity_DB extends AppCompatActivity implements SensorEventListener {

    DatabaseHelper myDB;


    private Button btnInsert;
    private Button btnUpdate;
    private Button btnDelete;

    private Button btnAngle_1;
    private Button btnAngle_2;
    private Button btnAngle_3;

    private EditText txtID;
    private TextView txtLastEdit;
    private TextView txtAngle_1;
    private TextView txtAngle_2;
    private TextView txtAngle_3;

    private SensorManager sensorManager;
    private Sensor sensorLight;
    private Sensor sensorAccelerometer;
    private Sensor sensorMagnetic;

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

    private float[] angleArray = new float[3];
    private float[] lightArray = new float[3];

    private float angleValue;
    private float lightValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__db);
        myDB = new DatabaseHelper(this);
        btnInsert = findViewById(R.id.button1);
        btnDelete = findViewById(R.id.button2);
        btnUpdate = findViewById(R.id.button3);
        txtID = findViewById(R.id.textView8);
        btnAngle_1 = findViewById(R.id.button10);
        btnAngle_2 = findViewById(R.id.button11);
        btnAngle_3 = findViewById(R.id.button12);
        txtAngle_1 = findViewById(R.id.textView10);
        txtAngle_2 = findViewById(R.id.textView11);
        txtAngle_3 = findViewById(R.id.textView12);
        txtLastEdit = findViewById(R.id.textView9);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorMagnetic = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (sensorLight == null) {
            Toast.makeText(this, "Device has no light sensor!", Toast.LENGTH_SHORT).show();
            finish();
        }
        if (sensorAccelerometer == null) {
            Toast.makeText(this, "Device has no accelerometer!", Toast.LENGTH_SHORT).show();
            finish();
        }
        if (sensorMagnetic == null) {
            Toast.makeText(this, "Device has no magnetic sensor!", Toast.LENGTH_SHORT).show();
            finish();
        }
        sensorManager.registerListener(this, sensorLight, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorAccelerometer, SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(this, sensorMagnetic, SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_FASTEST);

        btnAngle_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                angleArray[0] = angleValue;
                lightArray[0] = lightValue;
                angleArray[0] = Math.round(angleArray[0]);
                txtAngle_1.setText(Integer.toString((int)angleArray[0]));
            }
        });

        btnAngle_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                angleArray[1] = angleValue;
                lightArray[1] = lightValue;
                angleArray[1] = Math.round(angleArray[1]);
                txtAngle_2.setText(Integer.toString((int)angleArray[1]));

            }
        });
        btnAngle_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                angleArray[2] = angleValue;
                lightArray[2] = lightValue;
                angleArray[2] = Math.round(angleArray[2]);
                txtAngle_3.setText(Integer.toString((int)angleArray[2]));
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float tmpLight_float = lightArray[0] + lightArray[1] + lightArray[2];
                tmpLight_float = tmpLight_float/3;
                tmpLight_float = Math.round(tmpLight_float);
                int LL = (int) tmpLight_float;

                float z = angleArray[0];
                z = Math.round(z);
                int zz = (int)z;

                float x = angleArray[1];
                x = Math.round(x);
                int xx = (int)x;

                float y = angleArray[2];
                y = Math.round(y);
                int yy = (int)y;

                String tmpLight_string = Integer.toString(LL);
                String tmpAngle_1 = Integer.toString(zz);
                String tmpAngle_2 = Integer.toString(xx);
                String tmpAngle_3 = Integer.toString(yy);

                boolean isInserted = myDB.insertData(tmpAngle_1,tmpAngle_2,tmpAngle_3,tmpLight_string);
                if (isInserted == true){
                    Toast.makeText(activity_DB.this,"Data inserted",Toast.LENGTH_SHORT).show();
                    txtLastEdit.setText("Last data: Angle 1: "+tmpAngle_1+" Angle 2: "+tmpAngle_2+" Angle 3: "+tmpAngle_3+" Light: "+tmpLight_string);
                }
                else Toast.makeText(activity_DB.this,"Data not inserted",Toast.LENGTH_SHORT).show();

            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float tmpLight_float = lightArray[0] + lightArray[1] + lightArray[2];
                tmpLight_float = tmpLight_float/3;
                tmpLight_float = Math.round(tmpLight_float);
                int LL = (int) tmpLight_float;

                float z = angleArray[0];
                z = Math.round(z);
                int zz = (int)z;

                float x = angleArray[1];
                x = Math.round(x);
                int xx = (int)x;

                float y = angleArray[2];
                y = Math.round(y);
                int yy = (int)y;

                String tmpLight_string = Integer.toString(LL);
                String tmpAngle_1 = Integer.toString(zz);
                String tmpAngle_2 = Integer.toString(xx);
                String tmpAngle_3 = Integer.toString(yy);

                boolean isUpdate = myDB.updateData(txtID.getText().toString(),tmpAngle_1,tmpAngle_2,tmpAngle_3,tmpLight_string);

                if (isUpdate == true){
                    Toast.makeText(activity_DB.this,"Data updated",Toast.LENGTH_SHORT).show();
                    txtLastEdit.setText("Last data: Angle 1: "+tmpAngle_1+" Angle 2: "+tmpAngle_2+" Angle 3: "+tmpAngle_3+" Light: "+tmpLight_string);

                }
                else Toast.makeText(activity_DB.this,"Data not updated",Toast.LENGTH_SHORT).show();


            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer deletedRows = myDB.deleteData(txtID.getText().toString());
                if(deletedRows > 0){Toast.makeText(activity_DB.this,"Data deleted, ID = "+txtID.getText().toString(),Toast.LENGTH_SHORT).show();}
                else Toast.makeText(activity_DB.this,"Data not deleted",Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorLight, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorAccelerometer, SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(this, sensorMagnetic, SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) accels = event.values.clone();
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

            angleValue = azimuth;

        }

        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            lightValue = event.values[0];
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
