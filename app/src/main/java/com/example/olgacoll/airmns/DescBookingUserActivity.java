package com.example.olgacoll.airmns;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by olgacoll on 13/5/17.
 */

public class DescBookingUserActivity extends Activity {

    String date, time, observations, value;
    double price;
    TextView textView, textViewDate, textViewTime, textViewObservations, textViewValue, textViewPrice;
    Bundle bundle;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout6_desc_booking_user);

        initComponents();
        initBundle();
        setDetailText();
        textView.setText(text);
    }

    private void initComponents(){
        textView = (TextView) findViewById(R.id.textView);
        textViewDate = (TextView) findViewById(R.id.txt_date_descbooking);
        textViewTime = (TextView) findViewById(R.id.txt_time_descbooking);
        textViewObservations = (TextView) findViewById(R.id.txt_observations_descbooking);
        textViewValue = (TextView) findViewById(R.id.txt_price_descbooking);
        textViewPrice = (TextView) findViewById(R.id.txt_price_descbooking);
    }

    private void initBundle() {
        bundle = this.getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getString("time") != null) {
                time = bundle.getString("time");
            }
            if (bundle.getString("observations") != null) {
                observations = bundle.getString("observations");
            }if (bundle.getString("value") != null) {
                value = bundle.getString("value");
            }

            price = bundle.getDouble("price");

        }
    }

    private void setDetailText(){
        textViewDate.setText("Date: " + date);
        textViewTime.setText("Time: ");
        textViewObservations.setText("Observations: " + observations);
        textViewValue.setText("Value: " + value);
        textViewPrice.setText("Price: " + price + "€");
    }
}