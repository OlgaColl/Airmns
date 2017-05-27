package com.example.olgacoll.airmns;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olgacoll.airmns.model.Availability;
import com.example.olgacoll.airmns.remote.APIService;
import com.example.olgacoll.airmns.remote.APIUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by ericayala on 25/5/17.
 */

public class Activity7_MenuAvailability extends Activity {

    //objects
    private static final String TAG = "Activity7_MenuAvailability";
    APIService apiService;
    //Availability
    private static final int TIPO_DIALOGO = 0;
    private static DatePickerDialog.OnDateSetListener oyenteSelectorFecha;
    //Attributes
    int id;
    int indexAvailability;
    String dateAvailability[];
    List<Availability> dataObjectAvailability;
    //Bundle
    Bundle bundle;
    //Listener
    View.OnClickListener listener;
    AdapterView.OnItemSelectedListener listenerSpinner;
    //Date
    TextView tv_date;
    //Spinner
    Spinner spinnerAvailability;
    //Buttons
    Button b_add;
    Button b_modify;
    Button b_remove;




    // -- ON CREATE --
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout7_menu_availability);

        //Prepare views
        prepareViews();
        //Prepare reserve objects;
        prepareObjects();
        //Spinner
        controlSpinner();
        //Inicialize listener
        prepareListener();
        //On click listener
        addListener();
    }



    // -- PREPARES --

    //-- Prepare views --
    private void prepareViews() {
        //Add, modify & delete
        b_add = (Button) findViewById(R.id.button_add_L7_professional_availability);
        b_modify = (Button) findViewById(R.id.button_modify_L7_professional_availability);
        b_remove = (Button) findViewById(R.id.button_remove_L7_professional_availability);
        //Bundle
        bundle = this.getIntent().getExtras();
        //Get user id
        if (bundle != null) {
            if (bundle.getString("id") != null) {
                id = Integer.parseInt(bundle.getString("id"));
            }
        } else id = -1;
        spinnerAvailability = (Spinner) findViewById(R.id.spinner_L7_professional_availability);
    }

    //-- Prepare objects (availability) --
    private void prepareObjects(){
        apiService = APIUtils.getAPIService();
    }

    //-- Control Spinner --
    private void controlSpinner() {
        System.out.println("ID USER en control sppiner " + id);
        apiService.listAvailability(id).enqueue(new Callback<List<Availability>>() {
            @Override
            public void onResponse(Call<List<Availability>> call, Response<List<Availability>> response) {
                //System.out.println(response.body().get(1).toString());
                System.out.println("Response code: " + response.code());

                dateAvailability = new String[response.body().size()];
                dataObjectAvailability = new ArrayList<Availability>();

                //fillAddressSpinner
                for(int i = 0; i < response.body().size(); i++){
                    dateAvailability[i] = response.body().get(i).toString();
                    dataObjectAvailability.add(response.body().get(i));
                }

                for(int i = 0; i < dataObjectAvailability.size(); i++){
                    System.out.println(dataObjectAvailability.get(i).toString());
                }

                ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_style, dateAvailability);
                spinnerAvailability.setAdapter(adaptador);
                prepareItemListener();
                spinnerAvailability.setOnItemSelectedListener(listenerSpinner);
            }

            @Override
            public void onFailure(Call<List<Availability>> call, Throwable t) {
                showMessage("Can't access to server.");
                System.out.println("---------");
                System.out.println(t.getCause());
                System.out.println(t.getLocalizedMessage());
                System.out.println(t.getMessage());
                System.out.println(t.getStackTrace());
                System.out.println(t.getSuppressed());
            }
        });

    }

    //-- Prepare listener --
    private  void prepareListener() {

        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    //Add
                    case R.id.button_add_L7_professional_availability: //Choose date
                        initIntroduceAvailability();
                        break;
                    //Modify
                    case R.id.button_modify_L7_professional_availability:
                        initModifyAvailability();
                        break;
                    //Remove
                    case R.id.button_remove_L7_professional_availability:
                        //changeAll(false);
                        break;
                    //OK
                    /*case R.id.button_ok_L7:
                        saveChanges();
                        break;*/
                    //DEFAULT
                    default:
                        break;
                }
            }
        };

    }

    public void prepareItemListener() {
        listenerSpinner = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(
                    AdapterView<?> parent,
                    View view,
                    int position,
                    long id) {

                indexAvailability = position;
                //editTextAddress.setText(dateAvailability[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }

    //-- Add Listeners--
    private void addListener() {
        //Buttons
        b_add.setOnClickListener(listener);
        b_modify.setOnClickListener(listener);
        b_remove.setOnClickListener(listener);
    }



    //--Methods--

    public void initIntroduceAvailability(){
        //User id
        bundle.putString("id", Integer.toString(id));
        //Type
        bundle.putString("type", "add");
        //Intent
        Intent intent = new Intent(this, Activity7_ProfessionalAvailability.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void initModifyAvailability(){
        //User id
        bundle.putString("id", Integer.toString(id));
        //Type
        bundle.putString("type", "modify");
        //Date
        String to_date = new SimpleDateFormat("yyyy-MM-dd").format(dataObjectAvailability.get(indexAvailability).getDate());
        bundle.putString("date", to_date);
        //Start time
        bundle.putString("start_time", String.valueOf( dataObjectAvailability.get(indexAvailability).getStart_time() ));
        //End time
        bundle.putString("end_time", String.valueOf( dataObjectAvailability.get(indexAvailability).getEnd_time() ));
        //Intent
        Intent intent = new Intent(this, Activity7_ProfessionalAvailability.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void showMessage(String str){
        Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
    }


}
