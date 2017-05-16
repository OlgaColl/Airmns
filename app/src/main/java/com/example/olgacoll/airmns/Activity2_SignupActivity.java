package com.example.olgacoll.airmns;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olgacoll.airmns.model.User;

public class Activity2_SignupActivity extends AppCompatActivity {

    private static final String TAG = "Activity2_SignupActivity";

    EditText editTextName, editTextLastname, editTextEmail, editTextPrefix, editTextMobile, editTextPassword, editTextPassword2;
    Button buttonSignup;
    TextView textViewLogin;
    RadioButton radioButtonClient, radioButtonProfessional;
    View.OnClickListener listener;
    User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout2_signup);

        initComponents();
        onPrepareListener();

        buttonSignup.setOnClickListener(listener);
        textViewLogin.setOnClickListener(listener);
        radioButtonClient.setOnClickListener(listener);
        radioButtonProfessional.setOnClickListener(listener);
    }

    public void initComponents(){
        editTextName = (EditText)findViewById(R.id.input_name);
        editTextLastname = (EditText)findViewById(R.id.input_lastname);
        editTextEmail = (EditText)findViewById(R.id.input_email_L1_login);
        editTextMobile = (EditText)findViewById(R.id.input_mobile);
        editTextPrefix = (EditText)findViewById(R.id.input_prefix);
        editTextPassword = (EditText)findViewById(R.id.input_password_L1_login);
        editTextPassword2 = (EditText)findViewById(R.id.input_reEnterPassword);
        buttonSignup = (Button)findViewById(R.id.btn_signup_L2_signup);
        textViewLogin = (TextView)findViewById(R.id.link_login_L2_sign_up);
        radioButtonClient = (RadioButton)findViewById(R.id.radio_client_L2_sign_up);
        radioButtonProfessional = (RadioButton)findViewById(R.id.radio_professional_L2_sign_up);
        user = new User();
    }

    public void onPrepareListener(){
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.btn_signup_L2_signup:
                        signup();
                        break;
                    case R.id.link_login_L2_sign_up:
                        Intent intent = new Intent(getApplicationContext(), Activity1_LoginActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.radio_client_L2_sign_up:
                        break;
                    case R.id.radio_professional_L2_sign_up:
                        break;
                }
            }
        };
    }

    public void signup() {
        //Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
        }else{
            onSignupSuccess();
        }

        /*buttonSignup.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Activity2_SignupActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

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
                }, 3000);*/
    }


    public void onSignupSuccess() {
        buttonSignup.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Sign up failed", Toast.LENGTH_LONG).show();

        buttonSignup.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = editTextName.getText().toString();
        String lastname = editTextLastname.getText().toString();
        String email = editTextEmail.getText().toString();
        String prefix = editTextPrefix.getText().toString();
        String mobile = editTextMobile.getText().toString();
        String password = editTextPassword.getText().toString();
        String reEnterPassword = editTextPassword2.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            editTextName.setError("at least 3 characters");
            valid = false;
        } else {
            editTextName.setError(null);
        }

        if (lastname.isEmpty() || lastname.length() < 3) {
            editTextLastname.setError("at least 3 characters");
            valid = false;
        } else {
            editTextLastname.setError(null);
        }

        if (prefix.isEmpty()){
            editTextPrefix.setError("at least 2 numbers");
            valid = false;
        } else {
            editTextPrefix.setError(null);
        }

        if (mobile.isEmpty() || mobile.length()!=10) {
            editTextMobile.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            editTextMobile.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("enter a valid email address");
            valid = false;
        } else {
            editTextEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            editTextPassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            editTextPassword.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            editTextPassword2.setError("Password Do not match");
            valid = false;
        } else {
            editTextPassword2.setError(null);
        }

        if(!password.equals(reEnterPassword)){
            editTextPassword.setError("Password not match.");
            editTextPassword.setError("Password not match.");
            valid = false;
        }else{
            editTextPassword.setError("Password not match.");
        }

        return valid;
    }

    /*public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_client:
                if (checked)
                    //TODO
                    break;
            case R.id.radio_professional:
                if (checked)
                    //TODO
                    break;
        }
    }*/
}
