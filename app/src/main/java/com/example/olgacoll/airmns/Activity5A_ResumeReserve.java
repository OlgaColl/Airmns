package com.example.olgacoll.airmns;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ericayala on 1/5/17.
 */

public class Activity5A_ResumeReserve extends Activity {

    // -- ATTRIBUTES --

    //CLASS
    //Bundle
    Bundle mbundle;
    //Listener
    View.OnClickListener listener;
    //Reserve
    //Date
    Date date_reserve;
    //Time
    int hour;
    //Long Time
    int long_time;
    //Address
    String address;
    //Observations
    String observations;
    //Total pay
    Double total_pay;

    //LAYOUT
    TextView tv_date;
    TextView tv_long_time;
    TextView tv_address;
    TextView tv_observations;
    TextView tv_total_price;
    Button b_pay;




    // -- OnCreate --

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout5a_resume_reserve);

        //Get bundle attributes
        mbundle = this.getIntent().getExtras();

        //Prepare views
        prepareViews();
        //Prepare reserve objects;
        prepareBundle();
        //Inicialize listener
        prepareListener();
        //On click listener
        addListener();
    }




    // -- PREPARES --

    //-- Prepare views--
    private void prepareViews() {
        //Date
        tv_date = (TextView) findViewById(R.id.print_date_L5_resume_reserve);
        //Long time
        tv_long_time = (TextView) findViewById(R.id.time_L5_resume_reserve);
        //Address
        tv_address = (TextView) findViewById(R.id.address_L5_resume_reserve);
        //Observations
        tv_observations = (TextView) findViewById(R.id.observations_L5_resume_reserve);
        //Total pay
        tv_total_price = (TextView) findViewById(R.id.total_value_text_L5_resume_reserve);
        //Button Pay
        b_pay = (Button) findViewById(R.id.button_pay_L5_resume_reserve);
    }

    // -- Prepare Reserve objects --
    private void prepareBundle() {
        //GET VALUE
        //Date
        String date = mbundle.getString("date_reserve"); //Get string value
        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy"); //Create format to parse date
        try {
            date_reserve = formatoDeFecha.parse(date); //Parse string date to date
        } catch (ParseException ex) {}
        //Time
        hour = mbundle.getInt("time_reserve");
        //Long Time
        long_time = mbundle.getInt("long_time_reserve");
        //Date range
        String date_range = createDateRange();
        //Address
        address = mbundle.getString("address_reserve");
        //Observations
        observations = mbundle.getString("observations_reserve");
        observations = createObservations(); //Parse observations
        //Total pay
        total_pay = mbundle.getDouble("total_pay_reserve");

        //PRINT
        tv_date.setText(date);
        tv_long_time.setText(date_range);
        tv_address.setText(address);
        tv_observations.setText(observations);
        tv_total_price.setText(String.valueOf(total_pay)+"€");
    }

    // -- Prepare Listener --
    private void prepareListener() {

        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    //PAY
                    case R.id.button_pay_5A_reserve: //Continue
                        reserveDone();
                        break;
                    //DEFAULT
                    default:
                        break;
                }
            }
        };
    }

    //-- Add Listeners--
    private void addListener() {
        //Button pay
        b_pay.setOnClickListener(listener);;
    }




    // -- Other Methods --

    //To parse correct format for user view a range date
    private String createDateRange() {
        int time_finish = hour +long_time;
        if (time_finish > 23) time_finish = time_finish - 23;
        return hour + ":00 - " + time_finish + ":00";
    }

    //To parse correct format observations
    private String createObservations() {
        if (observations.trim().equals("")) return "(no observations)";
        else return observations;
    }

    //When press button user pay reserve
    private void reserveDone(){

    }
}
