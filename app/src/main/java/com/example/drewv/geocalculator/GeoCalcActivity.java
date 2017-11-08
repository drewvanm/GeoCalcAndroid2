package com.example.drewv.geocalculator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.location.Location;
import android.widget.Toast;


import com.example.drewv.geocalculator.dummy.HistoryContent;

import org.joda.time.DateTime;

import static android.R.attr.data;
import static com.example.drewv.geocalculator.R.id.calculate;
import static com.example.drewv.geocalculator.R.id.distance;
import static com.example.drewv.geocalculator.R.id.lat1;

public class GeoCalcActivity extends AppCompatActivity {
    private String curDistUnit = "Kilometer";
    private String curBearUnit = "Degrees";
    public static final int SETTINGS_SELECTION = 1;
    public static final int HISTORY_RESULT = 2;
    private EditText lat1 = null;
    private EditText long1 = null;
    private EditText lat2 = null;
    private EditText long2 = null;
    private TextView distance = null;
    private TextView bearing = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_calc);

        distance = (TextView) findViewById(R.id.distance);
        bearing = (TextView) findViewById(R.id.bearing);
        lat1 = (EditText) findViewById(R.id.lat1);
        lat2 = (EditText) findViewById(R.id.lat2);
        long1 = (EditText) findViewById(R.id.long1);
        long2 = (EditText) findViewById(R.id.long2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Calculate Button
        Button calculate = (Button) findViewById(R.id.calculate);
        calculate.setOnClickListener(v -> {
            updateScreen();
        });




        //Clear Button
        Button clear = (Button) findViewById(R.id.clear);
        clear.setOnClickListener(v -> {


            // Makes the keyboard dissapear when you click clear
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
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

    private void updateScreen(){
        if (lat1.getText().toString().isEmpty() ||
                lat2.getText().toString().isEmpty() ||
                long1.getText().toString().isEmpty() ||
                long2.getText().toString().isEmpty())
        {
            return;
        }
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
        double dist = loc1.distanceTo(loc2);
        double bear = loc1.bearingTo(loc2);
        //check which unit
        dist = dist / 1000;

        if (!curDistUnit.equals("Kilometers")) {
            dist = dist * .621371;
        }

        if (!curBearUnit.equals("Degrees")) {
            bear =  bear * 17.777778;
        }

        double roundedDistance = (double) Math.round(dist * 100) / 100;
        double roundedBearing = (double) Math.round(bear * 100) / 100;

        distance.setText("Distance: " + Double.toString(roundedDistance) + " " + curDistUnit);
        bearing.setText("Bearing: " + Double.toString(roundedBearing) + " " + curBearUnit);


        // Makes the keyboard disapear when you click calculate
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        HistoryContent.HistoryItem item = new HistoryContent.HistoryItem(Double.toString(lat1d),
                Double.toString(long1d),Double.toString(lat2d), Double.toString(long2d), DateTime.now());
        HistoryContent.addItem(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Intent intent = new Intent(GeoCalcActivity.this, SettingsActivity.class);
            intent.putExtra("dist", curDistUnit);
            intent.putExtra("bear", curBearUnit);
            startActivityForResult(intent, SETTINGS_SELECTION);
            return true;
        }else if (item.getItemId() == R.id.action_history){
            Intent intent = new Intent(GeoCalcActivity.this, HistoryActivity.class);
            startActivityForResult(intent, HISTORY_RESULT);
            return true;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == SETTINGS_SELECTION){
            curDistUnit = data.getStringExtra("distUnit");
            curBearUnit = data.getStringExtra("bearUnit");
            Button calc = (Button) findViewById(R.id.calculate);
            calc.performClick();
        }else if (resultCode == HISTORY_RESULT) {
            String[] vals = data.getStringArrayExtra("item");
            this.lat1.setText(vals[0]);
            this.long1.setText(vals[1]);
            this.lat2.setText(vals[2]);
            this.long2.setText(vals[3]);
            this.updateScreen();  // code that updates the calcs.
        }
    }
}