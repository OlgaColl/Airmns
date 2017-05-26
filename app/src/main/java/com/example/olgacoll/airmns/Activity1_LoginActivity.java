package com.example.olgacoll.airmns;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olgacoll.airmns.model.User;
import com.example.olgacoll.airmns.remote.APIService;
import com.example.olgacoll.airmns.remote.APIUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//Removes Bind
public class Activity1_LoginActivity extends AppCompatActivity{

    // -- Attributtes --
    Bundle bundle;
    private static final String TAG = "Activity1_LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private APIService apiService;
    String mailuser, passworduser;
    User user;
    EditText editTextEmail, editTextPassword;
    Button buttonLogin;
    TextView textViewSignUpLink;
    View.OnClickListener listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout1_login);

        initComponents();
        prepareListener();
        textViewSignUpLink.setOnClickListener(listener);
        buttonLogin.setOnClickListener(listener);
    }

    public void initComponents(){
        user = new User();
        editTextEmail = (EditText)findViewById(R.id.input_email_L1_login);
        editTextPassword = (EditText)findViewById(R.id.input_password_L1_login);
        buttonLogin = (Button)findViewById(R.id.btn_login_L1_login);
        textViewSignUpLink = (TextView)findViewById(R.id.link_signup_L1_login);
        apiService = APIUtils.getAPIService();
        bundle = this.getIntent().getExtras();
        if(bundle == null) bundle = new Bundle();
    }

    //eric es profesional 0 (no activitat), olga es client, comprovar amb pepe
    public void prepareListener() {
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_login_L1_login:
                        login();
                        break;
                    case R.id.link_signup_L1_login:
                        Intent intent = new Intent(getApplicationContext(), Activity2_SignupActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        };
    }

    private void login(){
        if (!validate()) {
            onSignupFailed();
        }else{
            checkLogin();
            //onSignupSuccess();
        }
    }

    public void onSignupSuccess() {
        setResult(RESULT_OK, null);
        Toast.makeText(getBaseContext(), "Login succes!", Toast.LENGTH_SHORT).show();

    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
    }

    public boolean validate(){

        boolean valid = true;
        mailuser = editTextEmail.getText().toString();
        passworduser = editTextPassword.getText().toString();

        if(mailuser.isEmpty()){
            editTextEmail.setError("Field can't be empty");
            valid = false;
        }else{
            editTextEmail.setError(null, getDrawable(R.drawable.ic_succes_ok));
        }

        if(passworduser.isEmpty()){
            editTextPassword.setError("Field can't be empty");
            valid = false;
        }else{
            editTextPassword.setError(null);
        }

        return valid;
    }

    private void checkLogin(){
        apiService.login(mailuser).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                System.out.println(response.body());
                if(response.isSuccessful()) {
                    System.out.println("Status code " + response.code());
                    System.out.println(response.body().getType());

                    int id = response.body().getId();
                    String mail = response.body().getMail();
                    String password = response.body().getPassword();
                    String type = response.body().getType();
                    String name= response.body().getName();
                    String lastname = response.body().getLastname();
                    String prefix_phone = response.body().getPrefix_phone();
                    String phone = response.body().getPhone();

                    user = new User(id, mail, password, type, name, lastname, prefix_phone, phone);

                    Log.i(TAG, "post submitted to API.\n" + response.body().toString());

                    System.out.println("Contrasenya " + user.getPassword());
                    if(!user.getMail().equals("User not found")){
                        if(checkPassword(passworduser)){
                            checkTypeUser(user.getType());
                        }else{
                            showMessage("Password incorrect");
                            cleanFields();
                        }
                    }else{
                        showMessage("User not found");
                    }

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
                showMessage("Unable to submit post to API.");
            }
        });
    }

    private void cleanFields(){
        editTextPassword.setText("");
    }

    private void checkTypeUser(String type){


        Intent intent;
        initUserBundle();
        switch(type){
            case "client":
                intent = new Intent(getApplicationContext(), Activity3A_MainUser.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case "professional0":
                showMessage("Professional unactivated");
                break;
            case "professional1":
                intent = new Intent(getApplicationContext(), Activity3B_MainProfessional.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }

    private void initUserBundle(){
        bundle.putString("id", Integer.toString(user.getId()));
        bundle.putString("mail", user.getMail());
        bundle.putString("password", user.getLastname());
        bundle.putString("type", user.getType());
        bundle.putString("name", user.getName());
        bundle.putString("lastname", user.getLastname());
        bundle.putString("prefix_phone", user.getPrefix_phone());
        bundle.putString("phone", user.getPhone());
    }

    private boolean checkPassword(String passworduser){
        boolean flag = false;
        if(user.getPassword().equals(passworduser)) flag = true;
        return flag;
    }

    private void showMessage(String str){
        Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
    }
}
