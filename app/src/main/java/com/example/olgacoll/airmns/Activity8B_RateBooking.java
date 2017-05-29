package com.example.olgacoll.airmns;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olgacoll.airmns.remote.APIService;
import com.example.olgacoll.airmns.remote.APIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ericayala on 2/5/17.
 */

public class Activity8B_RateBooking extends Activity {


    // -- ATTRIBUTES --
    //Valoration
    int valoration, id_reserve;
    //ApiService
    APIService apiService;
    String name, lastname, mail, prefix, phone, observations, date, start_time, duration, address;
    //Listener
    View.OnClickListener listener;
    //User & Booking
    TextView tv_name, tv_surname, tv_email, tv_phone, tv_date, tv_time, tv_address;
    //Observations
    EditText et_observations;
    //ImageView Valoration
    ImageView iv_star[];
    //Button
    Button b_done;
    Bundle bundle;



    // -- ON CREATE --
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout8b_rate_booking);

        //Prepare views
        prepareViews();
        //Inicialize bundles
        initBundle();
        setText();
        //Inicialize listener
        prepareListener();
        //On click listener
        addListener();
    }



    // -- PREPARES --

    //Prepare views
    private void prepareViews() {
        apiService = APIUtils.getAPIService();
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
        et_observations = (EditText)findViewById(R.id.input_observations_L8_valore_reserve_false);
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

    private void initBundle(){
        bundle = this.getIntent().getExtras();
        if (bundle != null) {

            id_reserve = bundle.getInt("id_reserve");

            if (bundle.getString("name") != null) {
                name = bundle.getString("name");
            }
            if (bundle.getString("lastname") != null) {
                lastname = bundle.getString("lastname");
            }
            if (bundle.getString("mail") != null) {
                mail = bundle.getString("mail");
            }
            if (bundle.getString("prefix") != null) {
                prefix = bundle.getString("prefix");
            }
            if (bundle.getString("phone") != null) {
                phone = bundle.getString("phone");
            }
            if (bundle.getString("date") != null) {
                date = bundle.getString("date");
            }
            if (bundle.getString("start_time") != null) {
                start_time = bundle.getString("start_time");
            }
            if (bundle.getString("duration") != null) {
                duration = bundle.getString("duration");
            }
            if (bundle.getString("address") != null) {
                address = bundle.getString("address");
            }
        }


    }

    private void setText(){
        tv_name.setText("Name: " + name);
        tv_surname.setText("Lastname: " + lastname);
        tv_email.setText("Mail: " + mail);
        tv_phone.setText("Phone: "  + prefix + " " + phone);
        tv_date.setText("Date: " + date);
        tv_time.setText("Start time: " + start_time + ":00h, duration: " + duration + "h");
        tv_address.setText("Address: " + address);
    }

    //Prepare listener
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
                        checkValoration();
                        break;
                    //DEFAULT
                    default:
                        break;
                }
            }
        };


    }



    //--Methods--

    //Print stairs
    private void printStars() {
        Drawable star, star_border;

        star = getResources().getDrawable(R.drawable.ic_action_star);
        star_border = getResources().getDrawable(R.drawable.ic_action_star_border);

        ColorFilter filterBlack = new LightingColorFilter(Color.BLACK, Color.BLACK);
        ColorFilter filterWhite = new LightingColorFilter(Color.WHITE, Color.WHITE);

        for(int i = 0; i<iv_star.length; i++) {
            if (i<=valoration){
                iv_star[i].setImageResource(R.drawable.ic_action_star_yellow);
                star.setColorFilter(filterBlack);
            } else{
                iv_star[i].setImageResource(R.drawable.ic_action_star_border);
            }
        }
    }

    //Add Listeners
    private void addListener() {
        //Availability
        for(int i = 0; i<iv_star.length; i++) iv_star[i].setOnClickListener(listener);
        //Button date
        b_done.setOnClickListener(listener);
    }

    private void checkValoration(){
        if(valoration <= 0){
            showMessage("Value your reserve");
        }else{
            observations = et_observations.getText().toString();
            apiService.rateReserve(id_reserve, valoration, observations).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    initMain();
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    showMessage("Error sending valoration. Try again");
                }
            });
        }
    }

    private void initMain(){
        this.finish();
    }



    //--ShowMessage--

    private void showMessage(String str) {
        Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
    }



}

