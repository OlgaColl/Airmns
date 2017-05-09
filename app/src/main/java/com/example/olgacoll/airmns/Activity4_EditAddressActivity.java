package com.example.olgacoll.airmns;

/**
 * Created by olgacoll on 14/3/17.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
    TODO modificar una direcció en concret que gestionarem des d'un Spinner, a l'Activity anterior (Edit ProfileActivity),

 */
public class Activity4_EditAddressActivity extends AppCompatActivity{

    private static final String TAG = "Activity4_EditAddressActivity";

    /*@Bind(R.id.change_address) EditText _addressText;
    @Bind(R.id.change_postal_code) EditText _postal_codeText;
    @Bind(R.id.change_city) EditText _cityText;
    @Bind(R.id.change_province) EditText _provinceText;
    @Bind(R.id.change_country) EditText _countryText;
    @Bind(R.id.btn_saveChanges) Button _saveChanges;
    @Bind(R.id.link_back) TextView _linkBack;*/

    EditText _addressText;
    EditText _postal_codeText;
    EditText _cityText;
    EditText _provinceText;
    EditText _countryText;
    Button _saveChanges;
    TextView _linkBack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout4_edit_address);
        //ButterKnife.bind(this);

        /*_saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges();
            }
        });

        _linkBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(), Activity4A_EditProfileClient.class);
                startActivity(intent);
                finish();
                //overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });*/
    }

    public void saveChanges() {
        //Log.d(TAG, "SaveChanges");

        if (!validate()) {
            onEditAddressFailed();
            return;
        }

        _saveChanges.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Activity4_EditAddressActivity.this, R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Saving changes...");
        progressDialog.show();

        String address = _addressText.getText().toString();
        String postal_code = _postal_codeText.getText().toString();
        String city = _cityText.getText().toString();
        String province = _provinceText.getText().toString();
        String country = _countryText.getText().toString();

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onEditAddressSuccess();
                        // onEditAddressFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onEditAddressSuccess() {
        _saveChanges.setEnabled(true);
        setResult(RESULT_OK, null);
        Intent intent = new Intent(getApplicationContext(), Activity4A_EditProfileClient.class);
        startActivity(intent);
        finish();
        //overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void onEditAddressFailed() {
        Toast.makeText(getBaseContext(), "Edit address failed", Toast.LENGTH_LONG).show();

        _saveChanges.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String address = _addressText.getText().toString();
        String postal_code = _postal_codeText.getText().toString();
        String city = _cityText.getText().toString();
        String province = _provinceText.getText().toString();
        String country = _countryText.getText().toString();

        if (address.isEmpty() || address.length() < 10) {
            _addressText.setError("at least 10 characters");
            valid = false;
        } else {
            _addressText.setError(null);
        }

        if(postal_code.isEmpty() || postal_code.length() < 4){
            _postal_codeText.setError("at least 4 characters");
            valid = false;
        } else{
            _postal_codeText.setError(null);
        }

        if (city.isEmpty() || city.length() < 3) {
            _cityText.setError("at least 3 characters");
            valid = false;
        } else {
            _cityText.setError(null);
        }

        if (province.isEmpty() || province.length() < 4) {
            _provinceText.setError("at least 4 characters");
            valid = false;
        } else {
            _provinceText.setError(null);
        }

        if (country.isEmpty() || country.length() < 4) {
            _countryText.setError("at least 4 characters");
            valid = false;
        } else {
            _countryText.setError(null);
        }

        return valid;
    }
}
