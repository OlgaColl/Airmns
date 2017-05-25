package com.example.olgacoll.airmns;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



/**
 * Created by ericayala on 25/5/17.
 */

public class Activity7_MenuAvailability extends Activity {

    //Layout objects
    //Listener
    View.OnClickListener listener;
    //Date
    TextView tv_date;
    //Availability
    private static final int TIPO_DIALOGO = 0;
    private static DatePickerDialog.OnDateSetListener oyenteSelectorFecha;
    TextView tv_availability[];
    //Buttons
    Button b_add;




    // -- ON CREATE --
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout7_menu_availability);

        //Prepare views
        prepareViews();
        //Prepare reserve objects;
        prepareObjects();
        //Inicialize listener
        prepareListener();
        //On click listener
        addListener();
        //Date
        createDate();
    }




    // -- PREPARES --

    //-- Prepare views --
    private void prepareViews() {
        //Date
        //tv_date = (TextView) findViewById(R.id.textView_date_L7_professional_availability);
        //b_input_date = (Button) findViewById(R.id.button_date_L7);
        //Total pay
        b_add = (Button) findViewById(R.id.button_add_L7_professional_availability);
    }



    //-- Prepare objects (availability) --
    private void prepareObjects(){
        //Instance object
        //availability = new boolean[16];
        //Inicialice objects
        //for(int i = 0; i < availability.length; i++) availability[i] = false;
    }

    //-- Prepare listener --
    private  void prepareListener() {

        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    //DATE
                    //case R.id.button_add_L7_professional_availability: //Choose date
                        //Intent intent = new Intent(this, Activity7_ProfessionalAvailability.class);
                        //startActivity(intent);
                        //break;
                    //SELECT ALL
                    /*case R.id.button_select_all_L7:
                        changeAll(true);
                        break;
                    //SELECT NONE
                    case R.id.button_restart_L7:
                        changeAll(false);
                        break;
                    //OK
                    case R.id.button_ok_L7:
                        saveChanges();
                        break;
                    //DEFAULT
                    default:
                        break;*/
                }
            }
        };

    }

    //-- Add Listeners--
    private void addListener() {
        //Button date
        //Button Select all
        //b_select_all.setOnClickListener(listener);
        //Button Select none
        //b_restart.setOnClickListener(listener);
        //Button ok
        b_add.setOnClickListener(listener);
        //Availability
        //for(int i = 0; i<tv_availability.length; i++) tv_availability[i].setOnClickListener(listener_availability);
    }




    // -- DATE --
    private void createDate() {
        //Object Calendar
        /*calendar = Calendar.getInstance();
        //Add two days
        calendar.add(Calendar.HOUR, 48);
        //Calendar attributes
        anyo = calendar.get(Calendar.YEAR);
        mes = calendar.get(Calendar.MONTH);
        dia = calendar.get(Calendar.DAY_OF_MONTH);
        //Print date
        printDateTime();
        //Create selected date
        oyenteSelectorFecha = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                anyo = year;
                mes = month;
                dia = dayOfMonth;
                calendar.set(year,month,dayOfMonth);
                printDateTime();
            }
        };*/
    }


}
