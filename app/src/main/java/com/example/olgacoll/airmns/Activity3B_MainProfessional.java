package com.example.olgacoll.airmns;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by alumne on 07/03/17.
 */

public class Activity3B_MainProfessional extends AppCompatActivity {

    //--Attributes--

    Bundle bundle;
    int id;
    //Objects
    FloatingActionButton fab;
    View.OnClickListener listener;
    ImageView imageViewManageReservation, imageViewBookingHistory;
    ImageView imageViewIntroduceAvailability, imageViewEditProfile;



    //--OnCreate--

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //OnCreate
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout3b_main_professional);
        //Init objects
        initObjects();
        prepareListener();
        initBundle();
        addListener();
    }



    //--OnPrepares--

    public void initObjects(){
        fab = (FloatingActionButton)findViewById(R.id.fab_L3B_main_professional);
        imageViewManageReservation = (ImageView)findViewById(R.id.imageViewManageReservation_L3B_main_professional);
        imageViewBookingHistory = (ImageView)findViewById(R.id.imageViewBookingHistory_L3B_main_professional);
        imageViewIntroduceAvailability = (ImageView)findViewById(R.id.imageViewIntroduceAvailability_L3B_main_professional);
        imageViewEditProfile = (ImageView)findViewById(R.id.imageViewEditProfile_L3B_main_professional);
    }

    public void prepareListener(){
        listener = new View.OnClickListener(){
            @Override
            public void onClick(View view){
                switch(view.getId()){
                    case R.id.fab_L3B_main_professional:
                        initInfo();
                        break;
                    case R.id.imageViewManageReservation_L3B_main_professional:
                        initManageBooking();
                        break;
                    case R.id.imageViewBookingHistory_L3B_main_professional:
                        initBookingHistory();
                        break;
                    case R.id.imageViewIntroduceAvailability_L3B_main_professional:
                        initIntroduceAvailability();
                        break;
                    case R.id.imageViewEditProfile_L3B_main_professional:
                        initEditProfile();
                        break;
                }
            }
        };
    }

    public void addListener(){
        fab.setOnClickListener(listener);
        imageViewManageReservation.setOnClickListener(listener);
        imageViewBookingHistory.setOnClickListener(listener);
        imageViewIntroduceAvailability.setOnClickListener(listener);
        imageViewEditProfile.setOnClickListener(listener);
    }



    //--Bundle--

    private void initBundle() {
        bundle = this.getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getString("id") != null) {
                id = Integer.parseInt(bundle.getString("id"));
            }
        }
    }



    //--Start Activities--

    public void initManageBooking(){
        //Booking
        bundle.putString("order", "more_now");
        //Intent
        Intent intent = new Intent(this, Activity6A_BookingHistory.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void initBookingHistory(){
        //Booking
        bundle.putString("order", "less_now");
        //Intent
        Intent intent = new Intent(this, Activity6A_BookingHistory.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void initIntroduceAvailability(){
        Intent intent = new Intent(this, Activity7A_MenuAvailability.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void initEditProfile(){
        Intent intent = new Intent(this, Activity4A_EditProfile.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void initInfo(){
        Intent intent = new Intent(this, Activity9_InfoActivity.class);
        startActivity(intent);
    }

}
