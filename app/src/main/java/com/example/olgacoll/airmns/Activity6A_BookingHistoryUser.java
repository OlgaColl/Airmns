package com.example.olgacoll.airmns;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olgacoll.airmns.model.User;

import java.util.List;

/**
 * Created by olgacoll on 25/4/17.
 */

public class Activity6A_BookingHistoryUser extends AppCompatActivity {

    Bundle bundle;
    User user;
    String mail, password, type, name, lastname, prefix_phone, phone;
    int id;
    ListView listView;
    TextView textViewTitle, textViewInfo;
    String[] reserves;
    String[] items;
    AdapterView.OnItemClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_history_user);

        initComponents();
        onPrepare();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(listener);
    }

    private void initComponents(){
        reserves = new String[15];
        items = new String[20];
        //items = new String[] { "Milk", "Butter", "Yogurt", "Toothpaste", "Ice Cream" };
        for(int i = 0; i < 20; i++){
            items[i] = "Reserva " + i;
        }
        textViewTitle = (TextView)findViewById(R.id.tvtitle);
        textViewInfo= (TextView)findViewById(R.id.tvinfo);
        listView = (ListView)findViewById(R.id.listview);

        bundle = this.getIntent().getExtras();
        if(bundle == null)  bundle = new Bundle();
    }

    private void onPrepare(){
        listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startDescBookingUser(items[position]);
            }
        };
    }

    private void startDescBookingUser(String items){
        bundle.putString("nom", items);
        Intent intent = new Intent(this, DescBookingUserActivity.class);
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