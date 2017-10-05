package com.example.drewv.geocalculator;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.location.Location;
import android.widget.Toast;


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

            double lat1d = Double.parseDouble(lat1.getText().toString());
            double lat2d = Double.parseDouble(lat2.getText().toString());
            double long1d = Double.parseDouble(long1.getText().toString());
            double long2d = Double.parseDouble(long2.getText().toString());

            //Distance from location 1 to location 2
            Location loc1 = new Location("");
            loc1.setLatitude(lat1d);
            loc1.setLongitude(long1d);

            Location loc2 = new Location("");
            loc2.setLatitude(lat2d);
            loc2.setLongitude(long2d);

            


            // Prints out the distance in Kilometers
            float distanceInMeters = loc1.distanceTo(loc2);
            float bearingInDegrees = loc1.bearingTo(loc2);
            //check which unit
            distanceInMeters = distanceInMeters / 1000;
            double roundedDistance = (double) Math.round(distanceInMeters * 100) / 100;
            double roundedBearing = (double) Math.round(bearingInDegrees * 100) / 100;

            distance.setText("Distance: " + Double.toString(roundedDistance));
            bearing.setText("Bearing: " + Double.toString(roundedBearing));



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
