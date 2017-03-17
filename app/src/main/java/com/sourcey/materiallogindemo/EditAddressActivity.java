package com.sourcey.materiallogindemo;

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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;

/*
    TODO modificar una direcció en concret que gestionarem des d'un Spinner, a l'Activity anterior (Edit ProfileActivity),
    completar amb opcions escrites a l'agenda.
 */
public class EditAddressActivity extends AppCompatActivity{

    private static final String TAG = "EditAddressActivity";

    @Bind(R.id.change_address) EditText _addressText;
    @Bind(R.id.change_postalcode) EditText _postalcodeText;
    @Bind(R.id.change_address) EditText _addressText;
    @Bind(R.id.change_city) EditText _cityText;
    @Bind(R.id.change_province) EditText _provinceText;
    @Bind(R.id.change_country) EditText _countryText;
    @Bind(R.id.btn_saveChanges) Button _saveChanges;
    @Bind(R.id.link_back) TextView _linkBack;
}
