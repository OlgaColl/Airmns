package com.example.olgacoll.airmns;

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
import com.example.olgacoll.airmns.remote.APIService;
import com.example.olgacoll.airmns.remote.APIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity2_SignupActivity extends AppCompatActivity {

    //--Attributes--

    private static final String TAG = "Activity2_SignupActivity";

    EditText editTextName, editTextLastname, editTextEmail, editTextPrefix, editTextMobile, editTextPassword, editTextPassword2;
    Button buttonSignup;
    APIService apiService;
    TextView textViewLogin;
    RadioButton radioButtonClient, radioButtonProfessional;
    View.OnClickListener listener;
    User user;
    String mail, password, type, name, lastname, prefix_phone, phone, reEnterPassword;



    //--OnCreate--

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout2_signup);
        //OnPrepare
        initComponents();
        onPrepareListener();
        //Listener
        addListener();
    }



    //--OnPrepare--

    public void initComponents(){
        apiService = APIUtils.getAPIService();
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
                        finish();
                        break;
                    case R.id.radio_client_L2_sign_up:
                        type = "client";
                        break;
                    case R.id.radio_professional_L2_sign_up:
                        type = "professional";
                        break;
                }
            }
        };
    }

    public void addListener(){
        buttonSignup.setOnClickListener(listener);
        textViewLogin.setOnClickListener(listener);
        radioButtonClient.setOnClickListener(listener);
        radioButtonProfessional.setOnClickListener(listener);
    }



    //--Methods--

    public void signup() {
        name = editTextName.getText().toString();
        lastname = editTextLastname.getText().toString();
        mail = editTextEmail.getText().toString();
        prefix_phone = editTextPrefix.getText().toString();
        phone = editTextMobile.getText().toString();
        password = editTextPassword.getText().toString();
        signUpUser();
    }

    public void signUpUser(){
        apiService.addUser(mail, password, type, name, lastname, prefix_phone, phone).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()) {
                    if(response.body().equals("1")) {
                        onSignupSuccess();
                    }else{
                        onSignupFailed();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t){
                showMessage("Can't access to server.");
            }
        });
    }

    public void onSignupSuccess() {
        //ShowMessage
        showMessage("Sign up success!");
        //Intent
        Intent intent = new Intent(getApplicationContext(), Activity1_LoginActivity.class);
        startActivity(intent);
        //Finish
        this.finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Sign up failed", Toast.LENGTH_LONG).show();
    }

    public boolean validate() {
        boolean valid = true;

        name = editTextName.getText().toString();
        lastname = editTextLastname.getText().toString();
        mail = editTextEmail.getText().toString();
        prefix_phone = editTextPrefix.getText().toString();
        phone = editTextMobile.getText().toString();
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

        if (prefix_phone.isEmpty()){
            editTextPrefix.setError("at least 2 numbers");
            valid = false;
        } else {
            editTextPrefix.setError(null);
        }

        if (phone.isEmpty() || phone.length()!=9) {
            editTextMobile.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            editTextMobile.setError(null);
        }

        if (mail.isEmpty() /*|| !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()*/) {
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



    //--ShowMessage--

    private void showMessage(String str){
        Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
    }

}