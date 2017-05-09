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

public class Activity3A_MainUser extends AppCompatActivity{

    FloatingActionButton fab;
    View.OnClickListener listener;
    ImageView imageViewManageReservation, imageViewBookingHistory;
    ImageView imageViewRateReservation, imageViewEditProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout3a_main_user);

        fab = (FloatingActionButton)findViewById(R.id.fab);
        imageViewManageReservation = (ImageView)findViewById(R.id.imageViewManageReservation);
        imageViewBookingHistory = (ImageView)findViewById(R.id.imageViewBookingHistory);
        imageViewRateReservation = (ImageView)findViewById(R.id.imageViewRateReservation);
        imageViewEditProfile = (ImageView)findViewById(R.id.imageViewEditProfile);

        prepareListener();

        fab.setOnClickListener(listener);
        imageViewManageReservation.setOnClickListener(listener);
        imageViewBookingHistory.setOnClickListener(listener);
        imageViewRateReservation.setOnClickListener(listener);
        imageViewEditProfile.setOnClickListener(listener);
    }

    public void prepareListener(){
        listener = new View.OnClickListener(){
            @Override
            public void onClick(View view){
                switch(view.getId()){
                    case R.id.fab:
                        initFab();
                        //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        break;
                    case R.id.imageViewManageReservation:
                        initManageReservation();
                        break;
                    case R.id.imageViewBookingHistory:
                        initBookingHistory();
                        break;
                    case R.id.imageViewRateReservation:
                        initRateReservation();
                        break;
                    case R.id.imageViewEditProfile:
                        initEditProfile();
                        break;

                }
            }
        };
    }

    public void initFab(){
        Intent intent = new Intent(this, Activity9_InfoActivity.class);
        startActivity(intent);
    }

    public void initManageReservation(){
        Intent intent = new Intent(this, Activity5A_UserReserve.class);
        startActivity(intent);
    }

    public void initBookingHistory(){
        Intent intent = new Intent(this, Activity6A_BookingHistoryUser.class);
        startActivity(intent);
    }

    public void initRateReservation(){
        Intent intent = new Intent(this, Activity8A_RateReservations.class);
        startActivity(intent);
    }

    public void initEditProfile(){
        Intent intent = new Intent(this, Activity4A_EditProfileClient.class);
        startActivity(intent);
    }
}