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

import com.example.olgacoll.airmns.model.Client;
import com.example.olgacoll.airmns.model.Professional;
import com.example.olgacoll.airmns.model.User;

import java.util.List;

public class Activity2_SignupActivity extends AppCompatActivity {

    private static final String TAG = "Activity2_SignupActivity";

    EditText editTextName, editTextLastname, editTextEmail, editTextPrefix, editTextMobile, editTextPassword, editTextPassword2;
    Button buttonSignup;
    TextView textViewLogin;
    RadioButton radioButtonClient, radioButtonProfessional;
    View.OnClickListener listener;
    User user;
    String email, password, name, lastname, prefix, mobile, reEnterPassword, type;

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
        type = "client"; //inicialitzem amb tipus d'usuari client
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
                        break;
                    case R.id.radio_client_L2_sign_up:
                        type = "client";
                        System.out.println(type);
                        break;
                    case R.id.radio_professional_L2_sign_up:
                        type = "professional";
                        System.out.println(type);
                        break;
                }
            }
        };
    }

    public void signup() {
        if (!validate()) {
            onSignupFailed();
        }else{
            onSignupSuccess();
        }
    }
    public void onSignupSuccess() {
        setData();
        setResult(RESULT_OK, null);
        Toast.makeText(getBaseContext(), "Sign up succes!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), Activity1_LoginActivity.class);
        startActivity(intent);
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Sign up failed", Toast.LENGTH_LONG).show();
        buttonSignup.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        name = editTextName.getText().toString();
        lastname = editTextLastname.getText().toString();
        email = editTextEmail.getText().toString();
        prefix = editTextPrefix.getText().toString();
        mobile = editTextMobile.getText().toString();
        password = editTextPassword.getText().toString();
        reEnterPassword = editTextPassword2.getText().toString();

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

        if (mobile.isEmpty() || mobile.length()!=9) {
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

        if (password.isEmpty() || password.length() < 4 || password.length() > 15) {
            editTextPassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            editTextPassword.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 15 || !(reEnterPassword.equals(password))) {
            editTextPassword2.setError("Password do not match");
            valid = false;
        } else{
            editTextPassword2.setError(null);
        }

        return valid;
    }

    public void setData(){

        System.out.println(email);
        System.out.println(password);
        System.out.println(name);
        System.out.println(lastname);
        if(type.equals("client")){
            user = new Client(email, password, name, lastname, "+" + prefix, mobile);
        }else{
            user = new Professional(email, password, name, lastname, "+" + prefix, mobile);
        }

        user.toString();
    }
}