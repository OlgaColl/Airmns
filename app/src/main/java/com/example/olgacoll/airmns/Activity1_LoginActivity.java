package com.example.olgacoll.airmns;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

    private static final String TAG = "Activity1_LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private APIService apiService;
    EditText editTextEmail, editTextPassword;
    Button buttonLogin;
    TextView textViewSignUpLink;
    View.OnClickListener listener;
    List<User> listUsers; //Per fer proves



    // -- OnCreate --

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout1_login);

        editTextEmail = (EditText)findViewById(R.id.input_email_L1_login);
        editTextPassword = (EditText)findViewById(R.id.input_password_L1_login);
        buttonLogin = (Button)findViewById(R.id.btn_login_L1_login);
        textViewSignUpLink = (TextView)findViewById(R.id.link_signup_L1_login);
        apiService = APIUtils.getAPIService();
        //listUsers = loadUsers();

        /*listUsers = new ArrayList<>();
        User user = new User("Olga", "1234", "user", "Olga", "Coll Pérez", "+34", "687452135");
        User user2 = new User("Eric", "1234", "professional", "Eric", "Ayala Andreu", "+34", "674218593");

        listUsers.add(user);
        listUsers.add(user2);*/

        //prepareListener();
        //textViewSignUpLink.setOnClickListener(listener);
        //buttonLogin.setOnClickListener(listener);

        buttonLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //checkLogin(); comentat per les proves del RETROFIT.
                String userRetro = editTextEmail.getText().toString();
                String pwdRetro = editTextPassword.getText().toString();
                retrofitLogin(userRetro, pwdRetro);
            }
        });

        textViewSignUpLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), Activity2_SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                //overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    // -- Longin Methods --

    public void checkLogin(){
        Log.d(TAG, "Login");

        int index = -1;
        for(int i = 0; i < listUsers.size(); i++){
            if( listUsers.get(i).getMail().equals(  editTextEmail.getText().toString() )){ //comprovació per saber si l'usuari es client o profesional
                index = i;
            }
        }

        if (index >= 0) {
            if(listUsers.get(index).getType().equals("user")){
                Intent intent = new Intent(this, Activity3A_MainUser.class);
                startActivity(intent);
            }else{
                Intent intent = new Intent(this, Activity3B_MainProfessional.class);
                startActivity(intent);
            }

        } else {
            //notificar error
        }
    }

    private void retrofitLogin(String userRetro, String pwdRetro) {


        apiService.checkLogin(userRetro, pwdRetro).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                System.out.println(response.body());
                if(response.isSuccessful()) {
                    System.out.println("Status code" + response.code());
                    System.out.println(response.body().getType());
                    Log.i(TAG, "post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }

    /*public void sendPost(String title, String body) {

        mAPIService.addPost(title, body).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if(response.isSuccessful()) {
                    showResponse(response.body().toString());
                    Log.i(TAG, "post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                showErrorMessage();
                Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }*/
}
