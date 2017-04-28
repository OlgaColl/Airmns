package com.example.olgacoll.airmns;

/**
 * Created by olgacoll on 14/3/17.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Activity4EditProfileClient extends AppCompatActivity {

    private static final String TAG = "Activity4EditProfileClient";

    EditText editTextName, editTextLastname, editTextEmail, editTextMobile, editTextPassword, editTextPassword2;
    Button buttonSaveChanges;
    Button buttonEditAddress;
    Spinner spinnerAddress;
    String dataAddress[];
    Bundle bundle;

    View.OnClickListener listener;
    AdapterView.OnItemSelectedListener listenerSpinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout4a_editprofileclient);

        editTextName = (EditText)findViewById(R.id.input_name);
        editTextLastname = (EditText)findViewById(R.id.input_lastname);
        editTextEmail = (EditText)findViewById(R.id.input_email);
        editTextMobile = (EditText)findViewById(R.id.input_mobile);
        editTextPassword = (EditText)findViewById(R.id.input_password);
        editTextPassword2 = (EditText)findViewById(R.id.input_reEnterPassword);
        buttonEditAddress = (Button)findViewById(R.id.btn_edit_address);
        buttonSaveChanges = (Button)findViewById(R.id.btn_save_changes);
        spinnerAddress = (Spinner)findViewById(R.id.spinner_address);

        bundle = new Bundle();

        prepareListener();
        controlSpinner();
        //loadDataSpinner();
        buttonEditAddress.setOnClickListener(listener);
        buttonSaveChanges.setOnClickListener(listener);
    }

    public void loadDataSpinner(){
        for(int i = 1; i < 21; i++){
            dataAddress[i] = "Address " + i ;
        }
    }

    public void prepareListener(){
        listener = new View.OnClickListener(){
            @Override
            public void onClick(View view){
                switch(view.getId()){
                    case R.id.btn_save_changes:
                        saveChanges();
                        break;
                    case R.id.btn_edit_address:
                        editAddress(); //TODO Bundle!!
                        break;
                }
            }
        };
    }

    public void saveChanges(){
        //Log.d(TAG, "SaveChanges");
        Intent intent = new Intent(this, Activity3AMainUser.class);
        startActivity(intent);
    }

    public void editAddress(){
        /*Intent intent = new Intent(this, EditAddressActivity.class);
        startActivity(intent);*/

    }

    //Control address Spinner
    private void controlSpinner() {
        //Address
        dataAddress = getResources().getStringArray(R.array.address_value);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.address_value, android.R.layout.simple_list_item_1);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerAddress.setAdapter(adapter1);
        spinnerAddress.setOnItemSelectedListener(listenerSpinner);
    }


    /*public void saveChanges() {
        Log.d(TAG, "SaveChanges");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _saveChanges.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Activity4EditProfile.this, R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String name = _nameText.getText().toString();
        String lastname = _lastnameText.getText().toString();
        //TODO Spinner
        //String address = _addressText.getText().toString();
        String mobile = _mobileText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        _saveChanges.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _saveChanges.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String lastname = _lastnameText.getText().toString();
        //String address = _addressText.getText().toString();
        String email = _emailText.getText().toString();
        String mobile = _mobileText.getText().toString();
        String password = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if(lastname.isEmpty() || lastname.length() < 3){
            _lastnameText.setError("at least 3 characters");
            valid = false;
        } else{
            _lastnameText.setError(null);
        }

        /*if (address.isEmpty()) {
            _addressText.setError("Enter Valid Address");
            valid = false;
        } else {
            _addressText.setError(null);
        }

        if (mobile.isEmpty() || mobile.length()!=10) {
            _mobileText.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            _mobileText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            _reEnterPasswordText.setError("Password Do not match");
            valid = false;
        } else {
            _reEnterPasswordText.setError(null);
        }

        return valid;
    }*/
}
