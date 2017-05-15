package com.example.olgacoll.airmns;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by olgacoll on 25/4/17.
 */

public class Activity6A_BookingHistoryUser extends AppCompatActivity {

    ListView listView;
    TextView textViewTitle, textViewInfo;
    String[] reserves;
    Bundle bundle;
    AdapterView.OnItemClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_history_user);
        initComponents();
        onPrepare();

        String[] items = { "Milk", "Butter", "Yogurt", "Toothpaste", "Ice Cream" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);

        listView.setAdapter(adapter);
    }

    private void initComponents(){
        reserves = new String[15];
        textViewTitle = (TextView)findViewById(R.id.tvtitle);
        textViewInfo= (TextView)findViewById(R.id.tvinfo);
        listView = (ListView)findViewById(R.id.listview);

        //loadReserves();
    }

    private void onPrepare(){
        listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                DescBookingUserActivity(reserves[position]);
            }
        };
    }

    private void DescBookingUserActivity(String reserves){
        Intent intent = new Intent(this, DescBookingUserActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("reserva", reserves);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void loadReserves(){

        for(int i = 1; i < reserves.length; i++){
            reserves[i] = "Reserva " + i;
            System.out.println(reserves[i]);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.listitem, reserves);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(listener);
    }
}