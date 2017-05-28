package com.example.olgacoll.airmns;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by olgacoll on 13/5/17.
 */

public class DescBookingUserActivity extends Activity {

    String date, time, observations, value, address, start_time;
    double price;
    TextView textViewDate, textViewTime, textViewObservations, textViewValue, textViewPrice, textViewAddress, textViewStartTime;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout6_desc_booking_user);

        initComponents();
        initBundle();
        setDetailText();
    }

    private void initComponents(){
        textViewDate = (TextView) findViewById(R.id.txt_date_descbooking);
        textViewTime = (TextView) findViewById(R.id.txt_time_descbooking);
        textViewObservations = (TextView) findViewById(R.id.txt_observations_descbooking);
        textViewValue = (TextView) findViewById(R.id.txt_rate_descbooking);
        textViewPrice = (TextView) findViewById(R.id.txt_price_descbooking);
        textViewAddress = (TextView) findViewById(R.id.txt_address_descbooking);
        textViewStartTime = (TextView)findViewById(R.id.txt_start_time_descbooking);
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
            if (bundle.getString("date") != null) {
                date = bundle.getString("date");
            }

            if (bundle.getString("addressSelected") != null) {
                address = bundle.getString("addressSelected");
            }

            if (bundle.getString("start_time") != null) {
                start_time = bundle.getString("start_time");
            }

            if (bundle.getString("value") != null) {
                value = bundle.getString("start_time");
            }

            price = bundle.getDouble("price");

        }
    }

    private void setDetailText(){
        System.out.println(address);
        textViewDate.setText("Date: " + date);
        textViewStartTime.setText("Start time: " + start_time + ":00h");
        textViewTime.setText("Total time: " + time + "h");
        textViewAddress.setText("Address: " + address);
        if(observations != null){
            textViewObservations.setText("Observations: " + observations);
        }else{
            textViewObservations.setText("Observations: any");
        }
        if(value != null){
            textViewValue.setText("Qualification: " + value);
        }else{
            textViewValue.setText("Qualification: Pending rate");
        }

        textViewPrice.setText("Price: " + price + "€");
    }
}