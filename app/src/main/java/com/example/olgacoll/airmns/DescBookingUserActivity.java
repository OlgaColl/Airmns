package com.example.olgacoll.airmns;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by olgacoll on 13/5/17.
 */

public class DescBookingUserActivity extends Activity {

    TextView textView;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout6_desc_booking_user);

        textView = (TextView) findViewById(R.id.textView);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            text = (String) extras.get("nom").toString();
        }

        textView.setText(text);
    }
}