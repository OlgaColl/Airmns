package com.example.olgacoll.airmns;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.view.View.OnClickListener;

import com.example.olgacoll.airmns.model.Client;
import com.example.olgacoll.airmns.model.User;

import java.util.Calendar;


/**
 * Created by alumne on 10/03/17.
 */

public class Activity5A_UserReserve extends Activity {

    Bundle bundle;
    User user;
    String mail, password, type, name, lastname, prefix_phone, phone;
    int id;

    //Listener
    OnClickListener listener;
    AdapterView.OnItemSelectedListener listener_time_spinner;
    AdapterView.OnItemSelectedListener listener_address_spinner;

    //Layout objects
    //Date
    TextView tv_date;
    Button b_input_date;
    private static final int TIPO_DIALOGO = 0;
    private static DatePickerDialog.OnDateSetListener oyenteSelectorFecha;
    //Time
    TextView tv_time;
    Button b_input_time;
    //Long time
    Spinner spinner_long_time;
    String datos_time[];
    //Address
    Spinner spinner_address;
    String datos_address[];
    //Observations
    EditText input_observations;
    //Button continue
    Button b_continue;
    //Total Pay
    TextView tv_total_pay;

    //Class objects
    Calendar calendar;
    int anyo;
    int mes;
    int dia;
    int hour;
    int minute;

    //Reserve
    int long_time;
    String address;
    String observations;
    double total_pay;
    float precio_hora;

    // -- ON CREATE --
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout5a_reserve);

        //Prepare views
        prepareViews();
        //Prepare reserve objects;
        prepareObjects();

        initBundle();
        //Inicialize listener
        prepareListener();
        //Control Time and Address Spinner
        controlSpinner();
        //On click listener
        addListener();
        //Date
        createDate();
    }




    // -- PREPARES --

    //-- Prepare views--
    private void prepareViews() {
        //Date
        tv_date = (TextView) findViewById(R.id.print_date_5A_reserve);
        b_input_date = (Button) findViewById(R.id.button_date_5A_reserve);
        //Time
        tv_time = (TextView) findViewById(R.id.print_time_5A_reserve);
        b_input_time = (Button) findViewById(R.id.button_time_5A_reserve);
        //Long time
        spinner_long_time = (Spinner) findViewById(R.id.spinner_long_time_5A_reserve);
        //Address
        spinner_address = (Spinner) findViewById(R.id.spinner_address_5A_reserve);
        //Observations
        input_observations = (EditText) findViewById(R.id.input_observations_5A_reserve);
        //Button continue
        b_continue = (Button) findViewById(R.id.button_pay_5A_reserve);
        //Total pay
        tv_total_pay = (TextView) findViewById(R.id.total_value_text_5A_reserve);
    }

    private void initBundle() {
        //int id, String mail, password, type, name, lastname, prefix_phone, phone;
        bundle = this.getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getString("id") != null) {
                id = Integer.parseInt(bundle.getString("id"));
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

    // -- Prepare Reserve objects --
    private void prepareObjects() {
        precio_hora = (float) 9.95;
        long_time = 1;
        address = "";
        observations = "";
        calcularPrecio();
    }

    // -- Prepare Listener --
    private void prepareListener() {

        listener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    //DATE
                    case R.id.button_date_5A_reserve: //Choose date
                        mostrarCalendario(v);
                        break;
                    //TIME
                    case R.id.button_time_5A_reserve: //Choose date
                        mostrarHora(v);
                        break;
                    //OBSERVATIONS
                    case R.id.input_observations_5A_reserve: //Input observations

                        break;
                    //CONTINUE
                    case R.id.button_pay_5A_reserve: //Continue
                        //Toast.makeText(getApplicationContext(), "CONTINUE", Toast.LENGTH_SHORT).show();
                        reserveDone();
                        break;
                    //DEFAULT
                    default:
                        break;
                }
            }
        };

        listener_time_spinner =
                new AdapterView.OnItemSelectedListener(){
                    public void onItemSelected( AdapterView<?> parent,
                                                View view,
                                                int position,
                                                long id) {
                        Toast.makeText(getApplicationContext(), "("+ datos_time[position]+")", Toast.LENGTH_SHORT).show();
                        //Increment 1 valor to index for to now how many hours
                        long_time = position+1;
                        //Calcule total price
                        calcularPrecio();
                    }

                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                };

        listener_address_spinner =
                new AdapterView.OnItemSelectedListener(){
                    public void onItemSelected( AdapterView<?> parent,
                                                View view,
                                                int position,
                                                long id) {
                        Toast.makeText(getApplicationContext(), "("+ datos_address[position]+")", Toast.LENGTH_SHORT).show();
                        //Get address to spinner
                        address = datos_address[position];
                    }

                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                };
    }

    //Control long time and address spinner
    private void controlSpinner() {
        //TIME
        datos_time = getResources().getStringArray(R.array.txt_time_value_L5A_reserve);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.txt_time_value_L5A_reserve, android.R.layout.simple_list_item_1);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner_long_time.setAdapter(adapter);
        spinner_long_time.setOnItemSelectedListener(listener_time_spinner);


        //Address
        datos_address = getResources().getStringArray(R.array.txt_address_value_5A_reserve);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 =
                ArrayAdapter.createFromResource(this, R.array.txt_address_value_5A_reserve, android.R.layout.simple_list_item_1);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner_address.setAdapter(adapter1);
        spinner_address.setOnItemSelectedListener(listener_address_spinner);
    }

    //-- Add Listeners--
    private void addListener() {
        //Button date
        b_input_date.setOnClickListener(listener);
        //startDialogDate();
        //Button time
        b_input_time.setOnClickListener(listener);
        //Input observations
        input_observations.setOnClickListener(listener);
        //Button continue
        b_continue.setOnClickListener(listener);
    }




    // -- DATE --

    private void createDate() {
        //Instance date
        calendar = Calendar.getInstance();
        //Add two days
        calendar.add(Calendar.HOUR, 48);

        //Instance date variables with real data
        minute = calendar.get(Calendar.MINUTE);
        //If minute is more than 30 increment hour
        if(minute >= 30) {
            int many_minutes = 60-minute;
            calendar.add(Calendar.MINUTE,many_minutes);
        }
        //Minute always must be 00:00
        minute = 0;
        anyo = calendar.get(Calendar.YEAR);
        mes = calendar.get(Calendar.MONTH);
        dia = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);

        //Print date
        printDateTime();

        //Instance poput for input date
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


    // -- DIALOG TO INPUT DATE CLASS --
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
            case 0:
                return new DatePickerDialog(this, oyenteSelectorFecha, anyo, mes, dia);
        }
        return null;
    }

    public void mostrarCalendario(View control){
        showDialog(TIPO_DIALOGO);
    }

    //Print date in TextView
    private void printDateTime() {
        //Time
        if (minute < 10) tv_time.setText( hour + ":0" + minute);
        else tv_time.setText( hour + ":" + minute);
        //Date
        tv_date.setText(dia + "/" + (mes+1) + "/" + anyo);

    }




    // -- TIME --
    private void mostrarHora(View v) {
        Calendar calendar_time = Calendar.getInstance();
        //hour = calendar_time.get(Calendar.HOUR_OF_DAY);
        //minute = calendar_time.get(Calendar.MINUTE);

        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                minute = selectedMinute;
                hour = selectedHour;
                printDateTime();
            }
        }, hour, minute, true);
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }




    // -- PAY --
    private void calcularPrecio() {
        //Calcule total pay
        total_pay = long_time*precio_hora;
        //Rounded to 2 decimals
        total_pay = Math.round(total_pay*100.0)/100.0;
        //Print total price
        tv_total_pay.setText(String.valueOf(total_pay)+"€");
    }




    // -- RESERVE --
    public void reserveDone(){
        //If input data is correct
        if (correctData()) {
            //Date
            String date_reserve = dia + "/" + (mes+1) + "/" + anyo;
            bundle.putString("date_reserve", date_reserve);
            //Time
            bundle.putInt("time_reserve", hour);
            //Long Time
            bundle.putInt("long_time_reserve", long_time);
            //Address
            bundle.putString("address_reserve", address);
            //Observations
            observations = input_observations.getText().toString();
            bundle.putString("observations_reserve", observations);
            //Total pay
            bundle.putDouble("total_pay_reserve", total_pay);

            //Start ResumeReserve activity
            Intent intent = new Intent(this, Activity5A_ResumeReserve.class);
            // set Bundle to intent
            intent.putExtras(bundle);
            startActivity(intent);

            //Else print message
        } else {
            AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
            //Show message
            alertbox.setMessage("Input date must be 2 days greater than current date and minutes must be 0.");
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
        if(calendar.before(calendario_actual) || minute != 0) return false;
        else return true;
    }


}
