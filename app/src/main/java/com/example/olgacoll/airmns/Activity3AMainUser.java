package com.example.olgacoll.airmns;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by alumne on 07/03/17.
 */

public class Activity3AMainUser extends AppCompatActivity {

    View.OnClickListener listener;
    ImageView imageViewManageReservation, imageViewBookingHistory;
    ImageView imageViewRateReservation, imageViewEditProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout3a_main_user);

        imageViewManageReservation = (ImageView)findViewById(R.id.imageViewManageReservation);
        imageViewBookingHistory = (ImageView)findViewById(R.id.imageViewBookingHistory);
        imageViewRateReservation = (ImageView)findViewById(R.id.imageViewRateReservation);
        imageViewEditProfile = (ImageView)findViewById(R.id.imageViewEditProfile);

        prepareListener();

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

    public void initManageReservation(){
        Intent intent = new Intent(this, Activity5Reserve.class);
        startActivity(intent);
    }

    public void initBookingHistory(){
        Intent intent = new Intent(this, BookingHistoryActivity.class);
        startActivity(intent);
    }

    public void initRateReservation(){
        Intent intent = new Intent(this, RateReservationActivity.class);
        startActivity(intent);
    }

    public void initEditProfile(){
        Intent intent = new Intent(this, Activity4EditProfile.class);
        startActivity(intent);
    }
}
