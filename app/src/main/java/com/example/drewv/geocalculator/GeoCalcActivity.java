package com.example.drewv.geocalculator;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;


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

        //Calculate Button
        Button calculate = (Button) findViewById(R.id.calculate);
        calculate.setOnClickListener(v -> {
            lat1.setText("9");

            // Makes the keyboard disapear when you click calculate
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

        });







        //Clear Button
        Button clear = (Button) findViewById(R.id.clear);
        clear.setOnClickListener(v -> {


            // Makes the keyboard dissapear when you click clear
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }




            lat1.setText("");
            lat2.setText("");
            long1.setText("");
            long2.setText("");
            distance.setText("Distance: ");
            bearing.setText("Bearing: ");
        });
    }
}
