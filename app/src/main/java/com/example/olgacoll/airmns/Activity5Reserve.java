package com.example.olgacoll.airmns;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


/**
 * Created by alumne on 10/03/17.
 */

public class Activity5Reserve extends Activity {

    // -- ATTRIBUTES --
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
        setContentView(R.layout.layout_5_reserve);

        //Prepare views
        prepareViews();
        //Prepare reserve objects;
        prepareObjects();
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
        tv_date = (TextView) findViewById(R.id.print_date);
        b_input_date = (Button) findViewById(R.id.button_date);
        //Time
        tv_time = (TextView) findViewById(R.id.print_time);
        b_input_time = (Button) findViewById(R.id.button_time);
        //Long time
        spinner_long_time = (Spinner) findViewById(R.id.spinner_long_time);
        //Address
        spinner_address = (Spinner) findViewById(R.id.spinner_address);
        //Observations
        input_observations = (EditText) findViewById(R.id.input_observations);
        //Button continue
        b_continue = (Button) findViewById(R.id.button_pay);
        //Total pay
        tv_total_pay = (TextView) findViewById(R.id.total_value_text);
    }

    // -- Prepare Reserve objects --
    private void prepareObjects() {
        precio_hora = 995;
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
                    case R.id.button_date: //Choose date
                        //Toast.makeText(getApplicationContext(), "INPUT DATE", Toast.LENGTH_SHORT).show();
                        mostrarCalendario(v);
                        //startDialogDate();
                        break;
                    //TIME
                    case R.id.button_time: //Choose date
                        //Toast.makeText(getApplicationContext(), "INPUT TIME", Toast.LENGTH_SHORT).show();
                        mostrarHora(v);
                        break;
                    //OBSERVATIONS
                    case R.id.input_observations: //Input observations

                        break;
                    //CONTINUE
                    case R.id.button_pay: //Continue
                        Toast.makeText(getApplicationContext(), "CONTINUE", Toast.LENGTH_SHORT).show();
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
                        //TODO - almacenar el valor de la tabla en una variable
                        // (para saber el numero de horas);
                        long_time = position+1;
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
                        //TODO - almacenar el valor de la tabla en una variable
                        // (para saber el numero de horas);
                        address = datos_address[position];
                    }

                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                };
    }

    //Control long time and address spinner
    private void controlSpinner() {
        //TIME
        datos_time = getResources().getStringArray(R.array.time_value);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.time_value, android.R.layout.simple_list_item_1);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner_long_time.setAdapter(adapter);
        spinner_long_time.setOnItemSelectedListener(listener_time_spinner);


        //Address
        datos_address = getResources().getStringArray(R.array.address_value);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 =
                ArrayAdapter.createFromResource(this, R.array.address_value, android.R.layout.simple_list_item_1);
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
        calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 48);
        anyo = calendar.get(Calendar.YEAR);
        mes = calendar.get(Calendar.MONTH);
        dia = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        printDateTime();
        oyenteSelectorFecha = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                anyo = year;
                mes = month;
                dia = dayOfMonth;
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
        tv_date.setText(dia + "/" + mes + "/" + anyo);
    }



    // -- TIME --
    private void mostrarHora(View v) {
        Calendar calendar_time = Calendar.getInstance();
        hour = calendar_time.get(Calendar.HOUR_OF_DAY);
        minute = calendar_time.get(Calendar.MINUTE);

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
        total_pay = long_time*precio_hora;

        tv_total_pay.setText(String.valueOf(total_pay)+"€");
    }


    //RESERVE
    public void reserveDone(){
        //TODO fer tots els objectes de Reserve i pujarlos a un bundle per
        //TODO fer pujar a una nova activity on mostri al layout les dades

        //Date

        //Long Time

        //Address

        //Observations
        observations = input_observations.getText().toString();

        //Total pay
    }

}
