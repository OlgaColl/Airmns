package com.example.olgacoll.airmns;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.olgacoll.airmns.model.User;

import java.util.ArrayList;
import java.util.List;

//Removes Bind
public class Activity1_LoginActivity extends AppCompatActivity{

    // -- Attributtes --

    private static final String TAG = "Activity1_LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

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

        editTextEmail = (EditText)findViewById(R.id.input_email);
        editTextPassword = (EditText)findViewById(R.id.input_password);
        buttonLogin = (Button)findViewById(R.id.btn_login);
        textViewSignUpLink = (TextView)findViewById(R.id.link_signup);

        //listUsers = loadUsers();

        listUsers = new ArrayList<>();
        User user = new User("Olga", "1234", "user", "Olga", "Coll Pérez", "+34", "687452135");
        User user2 = new User("Eric", "1234", "professional", "Eric", "Ayala Andreu", "+34", "674218593");

        listUsers.add(user);
        listUsers.add(user2);

        //prepareListener();
        //textViewSignUpLink.setOnClickListener(listener);
        //buttonLogin.setOnClickListener(listener);

        buttonLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                checkLogin();
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


}
