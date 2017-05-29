package com.example.olgacoll.airmns;

/**
 * Created by olgacoll on 14/3/17.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olgacoll.airmns.remote.APIService;
import com.example.olgacoll.airmns.remote.APIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity4B_EditAddress extends AppCompatActivity{

    //--Attributes--

    private static final String TAG = "Activity4B_EditAddress";
    APIService apiService;
    Bundle bundle;
    int id_user, id_address;
    String controlAddress, street, number, stair, floor, door, postal_code, city;
    EditText editTextStreet, editTextNumber, editTextStair, editTextFloor, editTextDoor, editTextPostalCode, editTextCity;
    Button buttonSaveChanges;
    TextView textViewLinkBack;
    View.OnClickListener listener;



    //--OnCreate--

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout4b_edit_address);
        //OnPrepare
        initComponents();
        initBundle();
        prepareListener();
        //Listener
        buttonSaveChanges.setOnClickListener(listener);
        textViewLinkBack.setOnClickListener(listener);
    }



    //--OnPrepare--

    private void initComponents(){
        apiService = APIUtils.getAPIService();
        editTextStreet = (EditText) findViewById(R.id.change_street_L4_edit_address);
        editTextNumber = (EditText) findViewById(R.id.change_number_L4_edit_address);
        editTextStair = (EditText) findViewById(R.id.change_stair_L4_edit_address);
        editTextFloor = (EditText) findViewById(R.id.change_floor_L4_edit_address);
        editTextDoor = (EditText) findViewById(R.id.change_door_L4_edit_address);
        editTextPostalCode = (EditText) findViewById(R.id.change_postal_code_L4_edit_address);
        editTextCity = (EditText) findViewById(R.id.change_city_L4_edit_address);
        buttonSaveChanges = (Button) findViewById(R.id.btn_saveChanges_L4_edit_address);
        textViewLinkBack = (TextView)findViewById(R.id.link_back_L4_edit_address);
    }

    private void initBundle(){
        bundle = this.getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getString("id") != null) {
                id_user = Integer.parseInt(bundle.getString("id"));
            }
            if (bundle.getString("controlAddress") != null) {
                controlAddress = bundle.getString("controlAddress");
                changeButtonName(controlAddress);
            }
            if (bundle.getString("controlIdAddress") != null) {
                id_address = bundle.getInt("controlIdAddress");
            }
        }
    }

    private void changeButtonName(String controlAddress){
        if(controlAddress.equals("addAddress")){
            buttonSaveChanges.setText("Add address");
        }else{
            buttonSaveChanges.setText("Modify Address");
        }
    }

    private void prepareListener(){
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.link_back_L4_edit_address:
                        initBack();
                        break;
                    case R.id.btn_saveChanges_L4_edit_address:
                        saveChanges();
                        break;
                }
            }
        };
    }



    //--Methods--

    private void initBack(){
        Intent intent = new Intent(this, Activity4A_EditProfile.class);
        startActivity(intent);
    }

    private void saveChanges(){
        if(!validate()){
            editAddressFailed(controlAddress);
        }else{
            editAddressSuccess(controlAddress);
        }
    }

    private void editAddressFailed(String controlAddress){
        if(controlAddress.equals("addAddress")){
            showMessage("Error adding a new address");
        }else{
            showMessage("Error modifying an address");
        }
    }

    private void editAddressSuccess(String controlAddress){
        if(controlAddress.equals("addAddress")){
            addAddress();
        }else{
            modifyAddress();
        }
    }

    private void addAddress(){
        apiService.addAddress(id_user, street, number, floor, stair, door, city, postal_code).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                showMessage("Added succesfull!");
                finishActivity();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                showMessage("Can't access to server.");
            }
        });
    }

    private void modifyAddress(){
        apiService.modifyAddress(id_address, street, number, floor, stair, door, city, postal_code).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                showMessage("Succesfull modify");
                finishActivity();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                showMessage("Can't access to server.");
            }
        });
    }



    //--Validate--

    public boolean validate() {
        boolean valid = true;

        street = editTextStreet.getText().toString();
        number = editTextNumber.getText().toString();
        stair = editTextStair.getText().toString();
        floor = editTextFloor.getText().toString();
        door = editTextDoor.getText().toString();
        postal_code = editTextPostalCode.getText().toString();
        city = editTextCity.getText().toString();


        if (street.isEmpty() || street.length() < 2) {
            editTextStreet.setError("at least 2 characters");
            valid = false;
        } else {
            editTextStreet.setError(null);
        }

        if(number.isEmpty() || number.length() < 1){
            editTextNumber.setError("at least 1 character");
            valid = false;
        } else{
            editTextNumber.setError(null);
        }

        //Stair can be null
        if(stair.isEmpty()) {
            stair = "";
        }

        //Floor can be null too
        if(floor.isEmpty()) {
            floor = "";
        }

        if(door.isEmpty() || door.length() < 1){
            editTextDoor.setError("at least 1 character");
            valid = false;
        } else{
            editTextDoor.setError(null);
        }

        if(postal_code.isEmpty() || postal_code.length() < 4){
            editTextPostalCode.setError("at least 4 characters");
            valid = false;
        } else{
            editTextPostalCode.setError(null);
        }

        if (city.isEmpty() || city.length() < 3) {
            editTextCity.setError("at least 3 characters");
            valid = false;
        } else {
            editTextCity.setError(null);
        }

        return valid;
    }



    //--ShowMessage--

    private void showMessage(String str){
        Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
    }



    //--Finish Activity--

    private void finishActivity(){
        //Finish
        this.finish();
    }

}
