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
public class Activity1_LoginActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = "Activity1_LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    /*@Bind(R.id.input_email) EditText _emailText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.btn_login) Button _loginButton;
    @Bind(R.id.link_signup) TextView _signupLink;*/

    /*EditText _emailText;
    EditText _passwordText;
    Button _loginButton;
    TextView _signupLink;*/

    EditText editTextEmail, editTextPassword;
    Button buttonLogin;
    TextView textViewSignUpLink;
    View.OnClickListener listener;
    List<User> listUsers; //Per fer proves

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
        User user = new User("Olga", "1234", "user", "Olga", "Coll Pérez", "687452135");
        User user2 = new User("Eric", "1234", "professional", "Eric", "Ayala Andreu", "674218593");

        listUsers.add(user);
        listUsers.add(user2);

        //prepareListener();
        textViewSignUpLink.setOnClickListener(listener);
        buttonLogin.setOnClickListener(listener);

        //Navigation menu
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        //ButterKnife.bind(this);

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

    //Proves per saltar directament
    //User(String mail, String password, String name, String lastname, String phone)
    public List<User> loadUsers(){
        List<User> list = new ArrayList();
        User user = new User("Olga", "1234", "Olga", "Coll Pérez", "687452135", "user");
        User user2 = new User("Eric", "1234", "Eric", "Ayala Andreu", "674218593", "professional");
        listUsers.add(user);
        listUsers.add(user2);
        return list;
    }


    public void prepareListener(){
        listener = new View.OnClickListener(){
            @Override
            public void onClick(View view){
                switch(view.getId()){
                    case R.id.btn_login:
                        checkLogin();
                        break;
                    case R.id.link_signup:
                        initSignUp();
                        break;
                }
            }
        };
    }


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


    //Go to sign up user
    public void initSignUp(){
        Intent intent = new Intent(this, Activity2_SignupActivity.class);
        startActivity(intent);
    }

    /*public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Activity1_LoginActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

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

        return valid;
    }
*/
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
