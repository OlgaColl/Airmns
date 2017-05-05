package com.example.olgacoll.airmns;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ericayala on 2/5/17.
 */

public class Activity8B_ValueReserve extends Activity {


    // -- ATTRIBUTES --
    //Valoration
    int valoration;
    //Listener
    View.OnClickListener listener;
    //User
    TextView tv_name;
    TextView tv_surname;
    TextView tv_email;
    TextView tv_phone;
    //Reserve
    TextView tv_date;
    TextView tv_time;
    TextView tv_address;
    TextView tv_observations;
    //ImageView Valoration
    ImageView iv_star[];
    //Button
    Button b_done;




    // -- ON CREATE --
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout8b_value_reserve);

        //Prepare views
        prepareViews();
        //Inicialize listener
        prepareListener();
        //On click listener
        addListener();
    }



    // -- PREPARES --

    //-- Prepare views --
    private void prepareViews() {
        //Valoration
        valoration = -1;
        //User
        tv_name = (TextView) findViewById(R.id.user_name_L8_value_reserve);
        tv_surname = (TextView) findViewById(R.id.user_surname_L8_value_reserve);
        tv_email = (TextView) findViewById(R.id.user_email_L8_value_reserve);
        tv_phone = (TextView) findViewById(R.id.user_phone_L8_value_reserve);
        //User
        tv_date = (TextView) findViewById(R.id.print_date_L8_resume_reserve);
        tv_time = (TextView) findViewById(R.id.time_L8_resume_reserve);
        tv_address = (TextView) findViewById(R.id.address_L8_resume_reserve);
        tv_observations = (TextView) findViewById(R.id.observations_L8_resume_reserve);
        //Image valoration
        iv_star = new ImageView[5];
        iv_star[0] = (ImageView) findViewById(R.id.star_0_L8);
        iv_star[1] = (ImageView) findViewById(R.id.star_1_L8);
        iv_star[2] = (ImageView) findViewById(R.id.star_2_L8);
        iv_star[3] = (ImageView) findViewById(R.id.star_3_L8);
        iv_star[4] = (ImageView) findViewById(R.id.star_4_L8);
        //Button Done
        b_done = (Button) findViewById(R.id.button_done_L8_resume_reserve);
    }

    //-- Prepare listener --
    private  void prepareListener() {

        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    //Star0
                    case R.id.star_0_L8:
                        if (valoration == 0) valoration = -1;
                        else valoration = 0;
                        printStars();
                        break;
                    //Star1
                    case R.id.star_1_L8:
                        if (valoration == 1) valoration = 0;
                        else valoration = 1;
                        printStars();
                        break;
                    //Star2
                    case R.id.star_2_L8:
                        if (valoration == 2) valoration = 1;
                        else valoration = 2;
                        printStars();
                        break;
                    //Star3
                    case R.id.star_3_L8:
                        if (valoration == 3) valoration = 2;
                        else valoration = 3;
                        printStars();
                        break;
                    //Star4
                    case R.id.star_4_L8:
                        if (valoration == 4) valoration = 3;
                        else valoration = 4;
                        printStars();
                        break;
                    //OK
                    case R.id.button_done_L8_resume_reserve:

                        break;
                    //DEFAULT
                    default:
                        break;
                }
            }
        };


    }

    //-- Print stairs --
    private void printStars() {
        for(int i = 0; i<iv_star.length; i++) {
            if (i<=valoration) iv_star[i].setImageResource(R.drawable.star_8_valore_reserve);
            else iv_star[i].setImageResource(R.drawable.star_8_valore_reserve_false);
        }
    }

    //-- Add Listeners--
    private void addListener() {
        //Button date
        b_done.setOnClickListener(listener);
        //Availability
        for(int i = 0; i<iv_star.length; i++) iv_star[i].setOnClickListener(listener);
    }

}
