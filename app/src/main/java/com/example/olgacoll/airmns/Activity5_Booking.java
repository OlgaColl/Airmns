package com.example.olgacoll.airmns;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
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

import com.example.olgacoll.airmns.model.Address;
import com.example.olgacoll.airmns.model.Info;
import com.example.olgacoll.airmns.remote.APIService;
import com.example.olgacoll.airmns.remote.APIUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by alumne on 10/03/17.
 */

public class Activity5_Booking extends Activity {

    //--Attributtes--

    //ApiService
    APIService apiService;
    private static final String TAG = "Activity5_Booking";
    //Bundle
    Bundle bundle;// = new Bundle();
    //Objects
    int id, id_professional;
    boolean correct_data = false;

    //Listener
    OnClickListener listener;
    AdapterView.OnItemSelectedListener listener_time_spinner, listener_address_spinner;

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
    String datos_address[],dateAddress[];
    List<Address> dataObjectAddress;
    int indexAddress = -1;
    //Observations
    EditText input_observations;
    //Button continue
    Button b_find, b_continue;
    //Total Pay
    TextView tv_total_pay;

    //Class objects
    Calendar calendar;
    int anyo, mes, dia, hour, minute;

    //Reserve
    int long_time;
    String address, observations;
    double total_pay;
    float precio_hora, iva;



    // -- ON CREATE --
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //On Create
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout5_booking);
        //Prepare views
        prepareViews();
        //Prepare reserve objects;
        prepareObjects();
        //Inicialize listener
        prepareListener();
        //Control Time and Address Spinner
        controlSpinner();
        //Find info
        findInfo();
        //On click listener
        addListener();
        //Date
        createDate();
    }



    // -- PREPARES --

    //-- Prepare views--
    private void prepareViews() {
        //--VIEWS--
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
        //Button Find Professional availability
        b_find = (Button) findViewById(R.id.button_find_5A_reserve);
        //Button continue
        b_continue = (Button) findViewById(R.id.button_pay_5A_reserve);
        //Total pay
        tv_total_pay = (TextView) findViewById(R.id.total_value_text_5A_reserve);
        //--BUNDLE--
        //Bundle
        bundle = this.getIntent().getExtras();
        //Get user id
        if (bundle != null) {
            if (bundle.getString("id") != null) {
                id = Integer.parseInt(bundle.getString("id"));
            } else id = -1;
        }
    }

    // -- Prepare Reserve objects --
    private void prepareObjects() {
        //ApiService
        apiService = APIUtils.getAPIService();
        //Attributes
        precio_hora = (float) 9.95;
        precio_hora = 21;
        long_time = 1;
        address = "";
        observations = "";
        //Price
        calcularPrecio();
    }

    //-- Control Spinner --
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

        //--ADDRESS--
        apiService.listAllAddress(id).enqueue(new Callback<List<Address>>() {
            @Override
            public void onResponse(Call<List<Address>> call, Response<List<Address>> response) {
                //Objects
                dateAddress = new String[response.body().size()];
                dataObjectAddress = new ArrayList<Address>();

                //fillAddressSpinner
                for(int i = 0; i < response.body().size(); i++){
                    dateAddress[i] = response.body().get(i).toString();
                    dataObjectAddress.add(response.body().get(i));
                }

                //ArrayAdapter and Spinner
                ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_style, dateAddress);
                spinner_address.setAdapter(adaptador);
                prepareItemListener();
                spinner_address.setOnItemSelectedListener(listener_address_spinner);
            }

            @Override
            public void onFailure(Call<List<Address>> call, Throwable t) {
                showMessage("Can't access to server.");
            }
        });

    }

    public void prepareItemListener() {
        listener_address_spinner = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                indexAddress = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //DoNothing
            }
        };
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
                    //FIND PROVESSIONAL AVAILABILITY
                    case R.id.button_find_5A_reserve: //Input observations
                        findIdProfessional();
                        break;
                    //CONTINUE
                    case R.id.button_pay_5A_reserve: //Continue
                        //Toast.makeText(getApplicationContext(), "CONTINUE", Toast.LENGTH_SHORT).show();
                        bookingDone();
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
                        //Correct_data = false;
                        correct_data = false;
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

    private void findInfo(){
        apiService.selectInfo().enqueue(new Callback<Info>() {
            @Override
            public void onResponse(Call<Info> call, Response<Info> response) {
                if(response.isSuccessful()){
                    precio_hora = (float) response.body().getPrice_hour() ;
                    iva = (float) response.body().getIva();
                }
            }

            @Override
            public void onFailure(Call<Info> call, Throwable t) {
                showMessage("Can't acces to server. You will get the default values");
            }
        });
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
        b_find.setOnClickListener(listener);
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
        correct_data = false;
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
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                minute = selectedMinute;
                hour = selectedHour;
                correct_data = false;
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
        tv_total_pay.setText(" " + String.valueOf(total_pay)+"€");
    }

    // -- BOOKING --
    public void bookingDone(){
        //If input data is correct
        if (correctData()) {
            if(id > 0) {
                //If professional is founded
                if(correct_data) {
                    //If find professional
                    if (id_professional > 0) {
                        //Dialog confirm payment
                        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
                        //Set text
                        alertbox.setMessage("Are you sure?");
                        //Add Ok option
                        alertbox.setPositiveButton("Continue to payment", new DialogInterface.OnClickListener() {
                            //To do whe press Ok
                            public void onClick(DialogInterface arg0, int arg1) {
                                //Insert row in bd
                                addBooking();
                            }
                        });
                        //Add Cancel option
                        alertbox.setNegativeButton("No, I want to change something", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                //System.exit(0);
                            }
                        });
                        //Show
                        alertbox.show();
                    }
                    //Notify professional not found
                    else showAlert("There is'nt professional availability in this dates.");
                //Notify professional not found
                } else showAlert("You must find professional availability.");
            } else {
                //Notify id not found
                showMessage("Can't find your user identifier in BD. Is possible can't acces to server.");
            }
        }
    }

    //Comprove if correct input date
    private boolean correctData(){
        //--CALENDAR--
        //Current date more 2 days (date correct input availability)
        Calendar calendario_actual = Calendar.getInstance();
        calendario_actual.add(Calendar.HOUR, 24);
        //--COMPROVE--
        //If calendar more or igual than current date return false
        if(calendar.before(calendario_actual) || minute != 0) {
            //Show message
            showAlert("Input date must be 2 days greater than current date and minutes must be 0.");
            return false;
        }
        //If 7:00 is more than start time return false
        else if(hour < 7) {
            //Show message and Show
            showAlert("Start time must be more than 7:00.");
            return false;
        //If end time is more than 23:00 return false
        } else if((hour+long_time) > 23) {
            //Show message
            showAlert("End time less than 23:00.");
            return false;
        //Else return true
        } else return true;
    }



    // -- METHODS --

    private void findIdProfessional(){
        //Instance date in correct format
        String date = String.valueOf(anyo) + "-" + String.valueOf(mes+1) + "-" + String.valueOf(dia);
        //ApiService
        apiService.findProfessionalForBooking(date, hour, long_time).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                id_professional = Integer.parseInt(response.body());
                if(id_professional < 0) showMessage("Professional availability not found.");
                else if(id_professional > 0) {
                    showMessage("Professional availability founded!");
                    correct_data = true;
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                showMessage("Can't access to server.");
                id_professional = -1;
            }
        });
    }

    private void addBooking(){
        //Id address
        int id_address = dataObjectAddress.get(indexAddress).getId_address();
        //Date
        String date = String.valueOf(anyo) + "-" + String.valueOf(mes+1) + "-" + String.valueOf(dia);
        //Observations
        observations = input_observations.getText().toString();
        //Query
        apiService.addBooking(id, id_professional, id_address, date, hour, long_time, total_pay, observations, iva).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body().equals("0")) {
                    showMessage("Can't do booking. Please, check your data or do it later.");
                }
                else if(response.body().equals("1")) {
                    //Show message
                    showMessage("Payment successful!");
                    //Close activity
                    finishActivity();
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                showMessage("Can't access to server.");
            }
        });

    }



    // -- SHOW ALERT OR MESSAGE --

    private void showAlert(String str){
        //Declare alert
        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
        //Set text
        alertbox.setMessage(str);
        //Add option
        alertbox.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            //To do whe press Ok
            public void onClick(DialogInterface arg0, int arg1) {
                //OK;
            }
        });
        //Show
        alertbox.show();
    }

    private void showMessage(String str){
        Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
    }



    // -- Finish Activity --

    private void finishActivity(){
        //Finish
        this.finish();
    }

}
