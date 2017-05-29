package com.example.olgacoll.airmns;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by olgacoll on 13/5/17.
 */

public class Activity6B_DescriptionBooking extends Activity {

    //--Attributes--

    String date, time, observations, value, address, start_time, comments;
    double price;
    TextView textViewDate, textViewTime, textViewObservations, textViewValue, textViewPrice, textViewAddress, textViewStartTime, textViewComments;
    Bundle bundle;



    //--OnCreate--

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout6b_description_booking);
        //OnPrepare
        initComponents();
        initBundle();
        setDetailText();
    }



    //--OnPrepare--

    private void initComponents(){
        textViewDate = (TextView) findViewById(R.id.txt_date_descbooking);
        textViewTime = (TextView) findViewById(R.id.txt_time_descbooking);
        textViewObservations = (TextView) findViewById(R.id.txt_observations_descbooking);
        textViewValue = (TextView) findViewById(R.id.txt_rate_descbooking);
        textViewPrice = (TextView) findViewById(R.id.txt_price_descbooking);
        textViewAddress = (TextView) findViewById(R.id.txt_address_descbooking);
        textViewStartTime = (TextView)findViewById(R.id.txt_start_time_descbooking);
        textViewComments = (TextView) findViewById(R.id.txt_comments_descbooking);
    }

    private void initBundle() {
        bundle = this.getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getString("time") != null) {
                time = bundle.getString("time");
            }
            if (bundle.getString("observations") != null) {
                observations = bundle.getString("observations");
            }
            if (bundle.getString("comments") != null) {
                comments = bundle.getString("comments");
            }
            if (bundle.getString("value") != null) {
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
                value = bundle.getString("value");
            }

            price = bundle.getDouble("price");

        }
    }

    private void setDetailText(){
        textViewDate.setText("Date: " + date);
        textViewStartTime.setText("Start time: " + start_time + ":00h");
        textViewTime.setText("Total time: " + time + "h");
        textViewAddress.setText("Address: " + address);
        if(observations != null){
            textViewObservations.setText("Observations: " + observations);
        }else{
            textViewObservations.setText("Observations: Any");
        }
        if(value != null){
            textViewValue.setText("Qualification: " + value);
        }else{
            textViewValue.setText("Qualification: Pending rate");
        }
        if(comments != null){
            textViewComments.setText("Comments: " + comments);
        }else{
            textViewComments.setText("Comments: Pending comment");
        }
        textViewPrice.setText("Price: " + price + "€");
    }

}