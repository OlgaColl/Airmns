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
import android.widget.Toast;

import com.example.olgacoll.airmns.model.Address;
import com.example.olgacoll.airmns.model.Client;
import com.example.olgacoll.airmns.model.User;
import com.example.olgacoll.airmns.remote.APIService;
import com.example.olgacoll.airmns.remote.APIUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity4A_EditProfileClient extends AppCompatActivity {


    //--Attributes--
    private static final String TAG = "Activity4A_EditProfileClient";
    APIService apiService;
    //Objects
    User user;
    int id;
    String mail, name, lastname, prefix_phone, phone, type, password, password2;
    EditText editTextMail, editTextName, editTextLastname, editTextPrefix, editTextMobile, editTextPassword, editTextPassword2;
    //EditText editTextAddress;
    Button buttonAddAddress, buttonModifyAddress, buttonRemoveAddress, buttonSaveChanges;
    Spinner spinnerAddress;
    String dataAddress[];
    List<Address> dataObjectAddress;
    int indexAddress;
    //Bundle
    Bundle bundle;
    //Listener
    View.OnClickListener listener;
    AdapterView.OnItemSelectedListener listenerSpinner;



    //--OnCreate--

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout4_edit_profile);
        //Prepares
        initComponents();
        prepareListener();
        initBundle();
        controlSpinner();
        addListener();
    }



    //--Prepares--

    public void initComponents(){
        apiService = APIUtils.getAPIService();
        editTextMail = (EditText) findViewById(R.id.input_mail);
        editTextName = (EditText) findViewById(R.id.input_name);
        editTextLastname = (EditText) findViewById(R.id.input_lastname);
        editTextPrefix = (EditText) findViewById(R.id.input_prefix);
        editTextMobile = (EditText) findViewById(R.id.input_mobile);
        editTextPassword = (EditText) findViewById(R.id.input_password_L1_login);
        editTextPassword2 = (EditText) findViewById(R.id.input_reEnterPassword);
        buttonAddAddress = (Button) findViewById(R.id.button_add_L4_edit_profile);
        buttonModifyAddress = (Button) findViewById(R.id.button_modify_L4_edit_profile);
        buttonRemoveAddress = (Button) findViewById(R.id.button_remove_L4_edit_profile);
        buttonSaveChanges = (Button) findViewById(R.id.btn_save_changes);
        spinnerAddress = (Spinner) findViewById(R.id.spinner_address_5A_reserve);
        dataObjectAddress = new ArrayList();
        indexAddress = 0; //assignamos un indice por defecto
        bundle = new Bundle();
    }

    public void prepareListener() {
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.button_add_L4_edit_profile:
                        addAddress();
                        break;
                    case R.id.button_modify_L4_edit_profile:
                        modifyAddress();
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

    //TODO ID no gestionada
    private void initBundle(){
        bundle = this.getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getString("id") != null) {
                id = Integer.parseInt(bundle.getString("id"));
            }
            if (bundle.getString("mail") != null) {
                mail = bundle.getString("mail");
            }
            if (bundle.getString("name") != null) {
                name = bundle.getString("name");
            }
            if (bundle.getString("lastname") != null) {
                lastname = bundle.getString("lastname");
            }
            if (bundle.getString("type") != null) {
                type = bundle.getString("type");
            }
            if (bundle.getString("prefix_phone") != null) {
                prefix_phone = "+" + bundle.getString("prefix_phone");
            }
            if (bundle.getString("phone") != null) {
                phone = bundle.getString("phone");
            }
        }
        initFields();
        user = new Client(id, mail, password, type, name, lastname, prefix_phone, phone);
        System.out.println("ID Client" + id);
        System.out.println(user.toString());
    }

    //Control address Spinner
    private void controlSpinner() {
        System.out.println("ID USER en control sppiner " + id);
        apiService.listAllAddress(id).enqueue(new Callback<List<Address>>() {
            @Override
            public void onResponse(Call<List<Address>> call, Response<List<Address>> response) {
                //System.out.println(response.body().get(1).toString());
                System.out.println("Response code: " + response.code());

                dataAddress = new String[response.body().size()];

                //fillAddressSpinner
                for(int i = 0; i < response.body().size(); i++){
                    dataAddress[i] = response.body().get(i).toString();
                    dataObjectAddress.add(response.body().get(i));
                }

                for(int i = 0; i < dataObjectAddress.size(); i++){
                    System.out.println(dataObjectAddress.get(i).toString());
                }

                ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_style, dataAddress);
                spinnerAddress.setAdapter(adaptador);
                prepareItemListener();
                spinnerAddress.setOnItemSelectedListener(listenerSpinner);
            }

            @Override
            public void onFailure(Call<List<Address>> call, Throwable t) {
                showMessage("Unable to submit post to API.");
            }
        });

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
                //editTextAddress.setText(dateAvailability[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }

    private void addListener(){
        buttonAddAddress.setOnClickListener(listener);
        buttonModifyAddress.setOnClickListener(listener);
        buttonRemoveAddress.setOnClickListener(listener);
        buttonSaveChanges.setOnClickListener(listener);
    }



    //--Methods--

    private void initFields(){
        editTextMail.setText(mail);
        editTextName.setText(name);
        editTextLastname.setText(lastname);
        editTextPrefix.setText(prefix_phone);
        editTextMobile.setText(phone);
    }

    //Per afegir, passem com a bundle id_user
    public void addAddress() {
        //Put bundle
        Log.d("Add address", "Add address");
        bundle.putString("controlAddress", "addAddress");
        //Put bundle
        Intent intent = new Intent(this, Activity4_EditAddressActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        //Close activity
        this.finish();
    }

    //Per modificar, el bundle serà id_address
    public void modifyAddress(){
        //Put bundle
        bundle.putString("controlAddress", "modifyAddress");
        bundle.putInt("controlIdAddress", dataObjectAddress.get(indexAddress).getId_address());
        //Instance intnent
        Intent intent = new Intent(this, Activity4_EditAddressActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        //Close activity
        this.finish();
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
                        apiService.removeAddress(dataObjectAddress.get(indexAddress).getId_address()).enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                System.out.println("Status code " + response.code());
                                System.out.println(response.body());
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                showMessage("Unable to submit post to API.");
                            }
                        });
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
        if (!validate()) {
            editProfileFailed();
        }else{
            editProfileSuccess();
        }
    }

    public boolean validate() {
        boolean valid = true;

        name = editTextName.getText().toString();
        lastname = editTextLastname.getText().toString();
        mail = editTextMail.getText().toString();
        prefix_phone = editTextPrefix.getText().toString();
        phone = editTextMobile.getText().toString();
        password = editTextPassword.getText().toString();
        password2 = editTextPassword2.getText().toString();

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

        if (phone.isEmpty() || phone.length()!= 9) {
            editTextMobile.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            editTextMobile.setError(null);
        }

        if (mail.isEmpty() /*|| !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()*/) {
            editTextMail.setError("enter a valid email address");
            valid = false;
        } else {
            editTextMail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 15) {
            editTextPassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            editTextPassword.setError(null);
        }

        if (password2.isEmpty() || password2.length() < 4 || password2.length() > 15 || !(password2.equals(password))) {
            editTextPassword2.setError("Password do not match");
            valid = false;
        } else{
            editTextPassword2.setError(null);
        }

        return valid;
    }

    private void editProfileFailed(){
        showMessage("Error editing profile");
    }

    private void editProfileSuccess(){

        System.out.println("ID:" + id + " mail:" + mail + "name: " + name + "lastname: " + lastname + "prefix_phone: " + prefix_phone + " phone: " + phone);
        apiService.editUser(id, mail, password, name, lastname, prefix_phone, phone).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println("Status code " + response.code());
                System.out.println(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                showMessage("Unable to submit post to API.");
            }
        });

        //showMessage("Profile edited successfully");
        //Intent intent = new Intent(this, Activity3A_MainUser.class);
        //startActivity(intent);
    }

    private void showMessage(String str){
        Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
    }
}
