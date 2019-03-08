package com.example.hex.sensorsanddatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnLight;
    private Button btnGyro;
    private Button btnDB;
    private Button btnShowDB;
    private Button btnOtherSensors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLight = findViewById(R.id.button1);
        btnGyro = findViewById(R.id.button2);
        btnDB = findViewById(R.id.button3);
        btnShowDB = findViewById(R.id.button4);
        btnOtherSensors = findViewById(R.id.button5);

        btnLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myActivityLight = new Intent(getBaseContext(),activity_Light.class);
                startActivity(myActivityLight);
            }
        });

        btnGyro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myActivityGyro = new Intent(getBaseContext(),activity_Gyro.class);
                startActivity(myActivityGyro);
            }
        });

        btnDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myActivityDB = new Intent(getBaseContext(),activity_DB.class);
                startActivity(myActivityDB);

            }
        });

        btnShowDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myActivityShowDB = new Intent(getBaseContext(),activity_ShowDB.class);
                startActivity(myActivityShowDB);

            }
        });

        btnOtherSensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
