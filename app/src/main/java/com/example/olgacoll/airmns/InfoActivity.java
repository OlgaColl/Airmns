package com.example.olgacoll.airmns;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by olgacoll on 7/3/17.
 * This Activity shows info to company, app, contact and Google Maps NO!! ¿?¿?¿?
 */

public class InfoActivity extends AppCompatActivity {

    //@Bind(R.id.textCompany)
    TextView _textCompany;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        //ButterKnife.bind(this);
    }

}