package com.example.olgacoll.airmns;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.olgacoll.airmns.model.Client;
import com.example.olgacoll.airmns.model.User;

/**
 * Created by alumne on 07/03/17.
 */

public class Activity3A_MainUser extends AppCompatActivity{

    Bundle bundle;
    User user;
    String mail, password, type, name, lastname, prefix_phone, phone;
    int id;

    FloatingActionButton fab;
    View.OnClickListener listener;
    ImageView imageViewManageReservation, imageViewBookingHistory;
    ImageView imageViewRateReservation, imageViewEditProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout3a_main_user);

        fab = (FloatingActionButton)findViewById(R.id.fab_L3A_main_user);
        imageViewManageReservation = (ImageView)findViewById(R.id.imageViewManageReservation_L3A_main_user);
        imageViewBookingHistory = (ImageView)findViewById(R.id.imageViewBookingHistory_L3A_main_user);
        imageViewRateReservation = (ImageView)findViewById(R.id.imageViewRateReservation_L3A_main_user);
        imageViewEditProfile = (ImageView)findViewById(R.id.imageViewEditProfile_L3A_main_user);

        prepareListener();
        initBundle();
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
                    case R.id.fab_L3A_main_user:
                        initFab();
                        //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        break;
                    case R.id.imageViewManageReservation_L3A_main_user:
                        initManageReservation();
                        break;
                    case R.id.imageViewBookingHistory_L3A_main_user:
                        initBookingHistory();
                        break;
                    case R.id.imageViewRateReservation_L3A_main_user:
                        initRateReservation();
                        break;
                    case R.id.imageViewEditProfile_L3A_main_user:
                        initEditProfile();
                        break;
                }
            }
        };
    }

    private void initBundle() {
        bundle = this.getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getString("id") != null) {
                id = Integer.parseInt(bundle.getString("id"));
                System.out.println("ID EN BUNDLE " + id);
            }
            if (bundle.getString("mail") != null) {
                mail = bundle.getString("mail");
            }
            if (bundle.getString("password") != null) {
                password = bundle.getString("password");
            }
            if (bundle.getString("type") != null) {
                type = bundle.getString("type");
            }
            if (bundle.getString("name") != null) {
                name = bundle.getString("name");
            }
            if (bundle.getString("lastname") != null) {
                lastname = bundle.getString("lastname");
            }
            if (bundle.getString("prefix_phone") != null) {
                prefix_phone = bundle.getString("prefix_phone");
            }
            if (bundle.getString("phone") != null) {
                phone = bundle.getString("phone");
            }
        }

        user = new Client(id, mail, password, type, name, lastname, prefix_phone, phone);
        System.out.println(user.toString());
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
        //Intent intent = new Intent(this, Activity8A_RateReservations.class);
        Intent intent = new Intent(this, Activity8B_ValueReserve.class);
        startActivity(intent);
    }

    public void initEditProfile(){
        Intent intent = new Intent(this, Activity4A_EditProfileClient.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
