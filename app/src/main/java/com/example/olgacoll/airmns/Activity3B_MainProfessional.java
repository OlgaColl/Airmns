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
    FloatingActionButton fab;
    View.OnClickListener listener;
    ImageView imageViewManageReservation, imageViewBookingHistory;
    ImageView imageViewIntroduceAvailability, imageViewEditProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout3b_main_autonomous);

        fab = (FloatingActionButton)findViewById(R.id.fab_L3B_main_professional);
        imageViewManageReservation = (ImageView)findViewById(R.id.imageViewManageReservation_L3B_main_professional);
        imageViewBookingHistory = (ImageView)findViewById(R.id.imageViewBookingHistory_L3B_main_professional);
        imageViewIntroduceAvailability = (ImageView)findViewById(R.id.imageViewIntroduceAvailability_L3B_main_professional);
        imageViewEditProfile = (ImageView)findViewById(R.id.imageViewEditProfile_L3B_main_professional);

        prepareListener();

        fab.setOnClickListener(listener);
        imageViewManageReservation.setOnClickListener(listener);
        imageViewBookingHistory.setOnClickListener(listener);
        imageViewIntroduceAvailability.setOnClickListener(listener);
        imageViewEditProfile.setOnClickListener(listener);
    }

    public void prepareListener(){
        listener = new View.OnClickListener(){
            @Override
            public void onClick(View view){
                switch(view.getId()){
                    case R.id.fab_L3B_main_professional:
                        initFab();
                        break;
                    case R.id.imageViewManageReservation_L3B_main_professional:
                        initManageReservation();
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

    public void initFab(){
        Intent intent = new Intent(this, Activity9_InfoActivity.class);
        startActivity(intent);
    }

    public void initManageReservation(){
        Intent intent = new Intent(this, Activity5B_ProfessionalReserve.class);
        startActivity(intent);
    }

    public void initBookingHistory(){
        Intent intent = new Intent(this, Activity6B_BookingHistoryProfessional.class);
        startActivity(intent);
    }

    public void initIntroduceAvailability(){
        Intent intent = new Intent(this, Activity7_MenuAvailability.class);
        startActivity(intent);
    }

    public void initEditProfile(){
        Intent intent = new Intent(this, Activity4B_EditProfileProfessional.class);
        startActivity(intent);
    }
}
