package com.example.hex.sensorsanddatabase;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class activity_ShowDB extends AppCompatActivity {

    private TextView txtShowDB;

    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__showdb);

        myDB = new DatabaseHelper(this);
        txtShowDB = findViewById(R.id.textView9);

        Cursor result = myDB.getAllDate();
        if(result.getCount() == 0){
            Toast.makeText(activity_ShowDB.this,"No data to show",Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while(result.moveToNext()){
            buffer.append("Id :"+ result.getString(0)+"\n");
            buffer.append("Angle 1:"+ result.getString(1)+"\n");
            buffer.append("Angle 2:"+ result.getString(2)+"\n");
            buffer.append("Angle 3:"+ result.getString(3)+"\n");
            buffer.append("Light:"+ result.getString(4)+"\n\n");
        }
        txtShowDB.setText(buffer.toString());
    }
}
