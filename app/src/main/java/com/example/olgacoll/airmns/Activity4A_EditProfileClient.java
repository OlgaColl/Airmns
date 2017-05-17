package com.example.olgacoll.airmns;

/**
 * Created by olgacoll on 14/3/17.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Activity4A_EditProfileClient extends AppCompatActivity {

    private static final String TAG = "Activity4A_EditProfileClient";

    EditText editTextName, editTextLastname, editTextMobile, editTextPassword, editTextPassword2;
    //EditText editTextAddress;
    Button buttonAddAddress, buttonRemoveAddress, buttonSaveChanges;
    Spinner spinnerAddress;
    String dataAddress[];
    int indexAddress;
    Bundle bundle;

    View.OnClickListener listener;
    AdapterView.OnItemSelectedListener listenerSpinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout4_edit_profile);

        editTextName = (EditText) findViewById(R.id.input_name);
        editTextLastname = (EditText) findViewById(R.id.input_lastname);
        editTextMobile = (EditText) findViewById(R.id.input_mobile);
        editTextPassword = (EditText) findViewById(R.id.input_password_L1_login);
        editTextPassword2 = (EditText) findViewById(R.id.input_reEnterPassword);
        buttonAddAddress = (Button) findViewById(R.id.button_add_L4_edit_profile);
        buttonRemoveAddress = (Button) findViewById(R.id.button_remove_L4_edit_profile);
        buttonSaveChanges = (Button) findViewById(R.id.btn_save_changes);
        spinnerAddress = (Spinner) findViewById(R.id.spinner_address_5A_reserve);
        indexAddress = 0; //assignamos un indice por defecto
        bundle = new Bundle();
        prepareListener();
        controlSpinner();
        buttonAddAddress.setOnClickListener(listener);
        buttonRemoveAddress.setOnClickListener(listener);
        buttonSaveChanges.setOnClickListener(listener);
    }

    public void prepareListener() {
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.button_add_L4_edit_profile:
                        addAddress();
                        break;
                    case R.id.button_remove_L4_edit_profile:
                        removeAddress();
                        break;
                    case R.id.btn_save_changes:
                        saveChanges();
                        break;
                }
            }
        };
    }

    public void addAddress() {
        Log.d("Add address", "Add address");
        Intent intent = new Intent(this, Activity4_EditAddressActivity.class);
        startActivity(intent);
    }

    private void removeAddress() {
        Log.d("Remove address", "Remove address");
        showRemoveAlert();
    }

    private void showRemoveAlert() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Remove address");
        builder1.setMessage("Are you sure to delete the address " + dataAddress[indexAddress].toString() + "?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //dialog.cancel();
                        //Add toast to confirm remove item
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public void saveChanges() {
        //Log.d(TAG, "SaveChanges");
        Intent intent = new Intent(this, Activity3A_MainUser.class);
        startActivity(intent);
    }

    //Control address Spinner
    private void controlSpinner() {
        //Address
        dataAddress = getResources().getStringArray(R.array.txt_address_value_5A_reserve);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.txt_address_value_5A_reserve, android.R.layout.simple_list_item_1);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerAddress.setAdapter(adapter1);
        prepareItemListener();
        spinnerAddress.setOnItemSelectedListener(listenerSpinner);
    }

    public void prepareItemListener() {
        listenerSpinner = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(
                    AdapterView<?> parent,
                    View view,
                    int position,
                    long id) {

                indexAddress = position;
                //editTextAddress.setText(dataAddress[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
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
