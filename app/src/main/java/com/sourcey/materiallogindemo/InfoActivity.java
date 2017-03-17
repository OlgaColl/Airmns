package com.sourcey.materiallogindemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by olgacoll on 7/3/17.
 * This Activity shows info to company, app, contact and Google Maps
 */

public class InfoActivity extends AppCompatActivity {

    @Bind(R.id.textCompany)
    TextView _textCompany;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);
    }

}