package com.example.olgacoll.airmns;

/**
 * Created by olgacoll on 14/3/17.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Activity4EditProfileProfessional extends AppCompatActivity {

    private static final String TAG = "Activity4EditProfile";

    EditText editTextName, editTextLastname, editTextEmail, editTextMobile, editTextPassword, editTextPassword2;
    Button buttonSaveChanges;
    TextView textViewLinkBack;
    View.OnClickListener listener;

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

        buttonSaveChanges = (Button)findViewById(R.id.btn_save_changes);

        prepareListener();

        buttonSaveChanges.setOnClickListener(listener);
    }

    public void prepareListener(){
        listener = new View.OnClickListener(){
            @Override
            public void onClick(View view){
                switch(view.getId()){
                    case R.id.btn_save_changes:
                        saveChanges();
                        break;
                }
            }
        };
    }

    public void saveChanges(){
        Log.d(TAG, "SaveChanges");
        Intent intent = new Intent(this, Activity3AMainUser.class);
        startActivity(intent);
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
