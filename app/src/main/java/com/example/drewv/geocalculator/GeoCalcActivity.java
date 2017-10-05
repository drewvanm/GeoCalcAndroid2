package com.example.drewv.geocalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.drewv.geocalculator.R.id.distance;

public class GeoCalcActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_calc);
        TextView distance = (TextView) findViewById(R.id.distance);
        TextView bearing = (TextView) findViewById(R.id.bearing);
        EditText lat1 = (EditText) findViewById(R.id.lat1);
        EditText lat2 = (EditText) findViewById(R.id.lat2);
        EditText long1 = (EditText) findViewById(R.id.long1);
        EditText long2 = (EditText) findViewById(R.id.long2);

        Button calculate = (Button) findViewById(R.id.calculate);
        calculate.setOnClickListener(v -> {
            lat1.setText("9");
        });
        Button clear = (Button) findViewById(R.id.clear);
        clear.setOnClickListener(v -> {
            lat1.setText("");
            lat2.setText("");
            long1.setText("");
            long2.setText("");
            distance.setText("Distance: ");
            bearing.setText("Bearing: ");
        });
    }
}
