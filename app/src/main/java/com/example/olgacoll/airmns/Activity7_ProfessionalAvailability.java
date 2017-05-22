package com.example.olgacoll.airmns;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by ericayala on 25/4/17.
 */

public class Activity7_ProfessionalAvailability extends Activity {

    // -- ATTRIBUTES --
    //Class objects
    //Time
    Calendar calendar;
    int anyo;
    int mes;
    int dia;
    //Availability
    boolean availability[];


    //Layout objects
    //Listener
    View.OnClickListener listener;
    View.OnClickListener listener_availability;
    //Date
    TextView tv_date;
    Button b_input_date;
    //Availability
    private static final int TIPO_DIALOGO = 0;
    private static DatePickerDialog.OnDateSetListener oyenteSelectorFecha;
    TextView tv_availability[];
    //Buttons
    Button b_select_all;
    Button b_restart;
    Button b_ok;




    // -- ON CREATE --
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout7_professional_availability);

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
        tv_date = (TextView) findViewById(R.id.textView_date_L7_professional_availability);
        b_input_date = (Button) findViewById(R.id.button_date_L7);
        //Availability
        tv_availability = new TextView[16];
        inicialiceAvailability();
        //Button Select all
        b_select_all = (Button) findViewById(R.id.button_select_all_L7);
        //Button Select none
        b_restart = (Button) findViewById(R.id.button_restart_L7);
        //Total pay
        b_ok = (Button) findViewById(R.id.button_ok_L7);
    }

    //-- Inicialice Availability textViews--
    private void inicialiceAvailability() {
        tv_availability[0] = (TextView) findViewById(R.id.imagenLogo01);
        tv_availability[1] = (TextView) findViewById(R.id.imagenLogo02);
        tv_availability[2] = (TextView) findViewById(R.id.imagenLogo03);
        tv_availability[3] = (TextView) findViewById(R.id.imagenLogo04);
        tv_availability[4] = (TextView) findViewById(R.id.imagenLogo05);
        tv_availability[5] = (TextView) findViewById(R.id.imagenLogo06);
        tv_availability[6] = (TextView) findViewById(R.id.imagenLogo07);
        tv_availability[7] = (TextView) findViewById(R.id.imagenLogo08);
        tv_availability[8] = (TextView) findViewById(R.id.imagenLogo09);
        tv_availability[9] = (TextView) findViewById(R.id.imagenLogo10);
        tv_availability[10] = (TextView) findViewById(R.id.imagenLogo11);
        tv_availability[11] = (TextView) findViewById(R.id.imagenLogo12);
        tv_availability[12] = (TextView) findViewById(R.id.imagenLogo13);
        tv_availability[13] = (TextView) findViewById(R.id.imagenLogo14);
        tv_availability[14] = (TextView) findViewById(R.id.imagenLogo15);
        tv_availability[15] = (TextView) findViewById(R.id.imagenLogo16);
    }

    //-- Prepare objects (availability) --
    private void prepareObjects(){
        //Instance object
        availability = new boolean[16];
        //Inicialice objects
        for(int i = 0; i < availability.length; i++) availability[i] = false;
    }

    //-- Prepare listener --
    private  void prepareListener() {

        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    //DATE
                    case R.id.button_date_L7: //Choose date
                        mostrarCalendario(v);
                        break;
                    //SELECT ALL
                    case R.id.button_select_all_L7:
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
                        break;
                }
            }
        };

        listener_availability = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Id view
                int nombre = v.getId();
                //Search TextView with their id
                TextView tv_nombre = (TextView) findViewById(nombre);
                //Int hour
                int hour = Integer.parseInt( tv_nombre.getText().toString().substring(0,2) ) -7;
                //Change boalean to hour to true/false
                availability[hour]= !availability[hour];
                //Update AvailabilityViews
                updateAvailabilitViews();
                //Toast.makeText(getApplicationContext(), String.valueOf(hour), Toast.LENGTH_SHORT).show();

            }
        };
    }

    //-- Add Listeners--
    private void addListener() {
        //Button date
        b_input_date.setOnClickListener(listener);
        //Button Select all
        b_select_all.setOnClickListener(listener);
        //Button Select none
        b_restart.setOnClickListener(listener);
        //Button ok
        b_ok.setOnClickListener(listener);
        //Availability
        for(int i = 0; i<tv_availability.length; i++) tv_availability[i].setOnClickListener(listener_availability);
    }




    // -- DATE --
    private void createDate() {
        //Object Calendar
        calendar = Calendar.getInstance();
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
        };
    }

    //-- DIALOG TO INPUT DATE CLASS --
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
            case 0:
                return new DatePickerDialog(this, oyenteSelectorFecha, anyo, mes, dia);
        }
        return null;
    }

    //Dialog to show Calendar
    public void mostrarCalendario(View control){
        showDialog(TIPO_DIALOGO);
    }

    //Print date in TextView
    private void printDateTime() {
        //Date
        tv_date.setText(dia + "/" + (mes+1) + "/" + anyo);
    }



    // -- AVAILABILITY --

    private void updateAvailabilitViews() {
        for(int i = 0; i<availability.length; i++){
            if (availability[i]){
                tv_availability[i].setBackgroundColor(Color.WHITE);
                tv_availability[i].setTextColor(Color.parseColor("#1b4f72"));
            } else {
                //tv_availability[i].setBackgroundColor(Color.TRANSPARENT);
                tv_availability[i].setBackgroundResource(R.drawable.button7_image_hour);
                tv_availability[i].setTextColor(Color.WHITE);
            }

        }
    }



    // -- CHANGE ALL BOOLEANS --

    private void changeAll(boolean bl){
        //Hours
        for(int i = 0; i < availability.length; i++) availability[i] = bl;
        updateAvailabilitViews();
    }



    // -- SAVE CHANGES --

    private void saveChanges() {
        //If input data is correct
        if (correctData()) {
            //Add 1 day
            calendar.add(Calendar.HOUR, 24);
            anyo = calendar.get(Calendar.YEAR);
            mes = calendar.get(Calendar.MONTH);
            dia = calendar.get(Calendar.DAY_OF_MONTH);
            printDateTime();
            //Reestart availability
            changeAll(false);
            //Else print message
        } else {
            AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
            //Show message
            alertbox.setMessage("Input date must be 2 days greater than current date. ");
            //Add option
            alertbox.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                //To do whe press Ok
                public void onClick(DialogInterface arg0, int arg1) {
                    //mensaje("Pulsado el botón SI");
                }
            });

            //Show
            alertbox.show();
        }

    }

    //Comprove if correct input date
    private boolean correctData(){
        //Current date more 2 days (date correct input availability)
        Calendar calendario_actual = Calendar.getInstance();
        calendario_actual.add(Calendar.HOUR, 24);
        //If calendar more or igual than current date return false
        if(calendar.before(calendario_actual)) return false;
        else return true;

    }

}