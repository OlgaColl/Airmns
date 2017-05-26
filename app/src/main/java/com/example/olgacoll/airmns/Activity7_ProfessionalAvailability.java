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
    int start_time;
    int end_time;


    //Layout objects
    //Listener
    View.OnClickListener listener;
    View.OnClickListener listener_availability;
    //Date
    TextView tv_date;
    Button b_input_date;
    //Date
    private static final int TIPO_DIALOGO = 0;
    private static DatePickerDialog.OnDateSetListener oyenteSelectorFecha;
    //Start and end Time
    EditText et_start_time;
    EditText et_end_time;
    //Button Ok
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
        et_start_time = (EditText) findViewById(R.id.id_start_time_L7_professional_availability);
        et_end_time = (EditText) findViewById(R.id.id_end_time_L7_professional_availability);
        b_ok = (Button) findViewById(R.id.button_ok_L7);
    }

    //-- Prepare objects (availability) --
    private void prepareObjects(){
        start_time = 7;
        end_time = 23;
        et_start_time.setText(String.valueOf(start_time));
        et_end_time.setText(String.valueOf(end_time));
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
                //availability[hour]= !availability[hour];
                //Update AvailabilityViews
                //updateAvailabilitViews();
                //Toast.makeText(getApplicationContext(), String.valueOf(hour), Toast.LENGTH_SHORT).show();

            }
        };
    }

    //-- Add Listeners--
    private void addListener() {
        //Button date
        b_input_date.setOnClickListener(listener);
        //Button ok
        b_ok.setOnClickListener(listener);
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
            //Else print message
        } else {
            /*AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
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
            alertbox.show();*/
        }

    }

    //Comprove if correct input date
    private boolean correctData(){
        //Declare alert
        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
        //Add option
        alertbox.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            //To do whe press Ok
            public void onClick(DialogInterface arg0, int arg1) {
                //mensaje("Pulsado el botón SI");
            }
        });

        //-DATE--
        //Current date more 2 days (date correct input availability)
        Calendar calendario_actual = Calendar.getInstance();
        calendario_actual.add(Calendar.HOUR, 24);
        //-HOURS-
        start_time = Integer.parseInt( et_start_time.getText().toString() );
        end_time = Integer.parseInt( et_end_time.getText().toString() );
        //If calendar more or igual than current date return false
        if(calendar.before(calendario_actual)) {
            //Set text
            alertbox.setMessage("Input date must be 2 days greater than current date. ");
            //Show
            alertbox.show();
            //return
            return false;
        }
        //-TIME-
        /*//If 7 is more than start time return false
        if(start_time < 7 & start_time > 22) return false;
        //If end time is more than 23 return false
        if(end_time > 23 & end_time < 8) return false;
        //If start time is more than start time return false
        if(end_time < start_time) return false;*/


        //I-TIME-
        if(start_time < 7 || start_time > 22 || end_time > 23 || end_time < 8 || end_time < start_time || end_time == start_time) {
            //Set text
            alertbox.setMessage("Input bad time.");
            //Show
            alertbox.show();
            //Return
            return false;
        }
        //Else
        else return true;

    }

}