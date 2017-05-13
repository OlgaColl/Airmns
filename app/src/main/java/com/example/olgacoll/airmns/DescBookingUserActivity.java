package com.example.olgacoll.airmns;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by olgacoll on 13/5/17.
 */

public class DescBookingUserActivity extends Activity {

    /*TextView textViewUserId, textViewId, textViewTitle, textViewBody;

    String userId, id, title, body;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout6_desc_booking_user);

        /*textViewUserId = (TextView) findViewById(R.id.textViewUserId);
        textViewId = (TextView) findViewById(R.id.textViewId);
        textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        textViewBody = (TextView) findViewById(R.id.textViewBody);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            userId = (String) extras.get("userId").toString();
            id = (String) extras.get("id").toString();
            title = (String) extras.get("title");
            body = (String) extras.get("body");
        }

        textViewUserId.setText(userId);
        textViewId.setText(id);
        textViewTitle.setText(title);
        textViewBody.setText(body);*/
    }
}