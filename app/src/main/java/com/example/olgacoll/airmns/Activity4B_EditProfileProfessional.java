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

public class Activity4B_EditProfileProfessional extends AppCompatActivity {

    //--Attributes

    private static final String TAG = "Activity4EditProfile";
    EditText editTextName, editTextLastname, editTextEmail, editTextMobile, editTextPassword, editTextPassword2;
    Button buttonSaveChanges;
    TextView textViewLinkBack;
    View.OnClickListener listener;



    //--OnCreate--

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout4_edit_profile);
        //Prepare
        prepare();
        prepareListener();
        //Listener
        buttonSaveChanges.setOnClickListener(listener);
    }



    //--OnPrepare--

    public void prepare(){
        editTextName = (EditText)findViewById(R.id.input_name);
        editTextLastname = (EditText)findViewById(R.id.input_lastname);
        editTextEmail = (EditText)findViewById(R.id.input_email_L1_login);
        editTextMobile = (EditText)findViewById(R.id.input_mobile);
        editTextPassword = (EditText)findViewById(R.id.input_password_L1_login);
        editTextPassword2 = (EditText)findViewById(R.id.input_reEnterPassword);
        buttonSaveChanges = (Button)findViewById(R.id.btn_save_changes);
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
        Intent intent = new Intent(this, Activity3A_MainUser.class);
        startActivity(intent);
    }
    
}
