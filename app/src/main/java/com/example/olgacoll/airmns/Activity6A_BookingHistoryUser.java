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

import com.example.olgacoll.airmns.model.Reserve;
import com.example.olgacoll.airmns.model.User;
import com.example.olgacoll.airmns.remote.APIService;
import com.example.olgacoll.airmns.remote.APIUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by olgacoll on 25/4/17.
 */

public class Activity6A_BookingHistoryUser extends AppCompatActivity {

    private static final String TAG = "Activity6A_BookingHistoryUser";
    APIService apiService;
    Bundle bundle;
    User user;
    String mail, password, type, name, lastname, prefix_phone, phone;
    int id;
    ListView listView;
    TextView textViewTitle, textViewInfo;
    String[] reserves;
    String[] items;
    List<Reserve> dataReserves;
    AdapterView.OnItemClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_history_user);

        initComponents();
        initBundle();
        onPrepare();
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, reserves);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(listener);*/
    }

    private void initComponents(){
        apiService = APIUtils.getAPIService();
        //reserves = new String[15];
        //items = new String[20];
        //items = new String[] { "Milk", "Butter", "Yogurt", "Toothpaste", "Ice Cream" };
        /*for(int i = 0; i < 20; i++){
            items[i] = "Reserva " + i;
        }*/
        dataReserves = new ArrayList();

        textViewTitle = (TextView)findViewById(R.id.tvtitle);
        textViewInfo= (TextView)findViewById(R.id.tvinfo);
        listView = (ListView)findViewById(R.id.listview);
    }

    private void initBundle() {
        bundle = this.getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getString("id") != null) {
                id = Integer.parseInt(bundle.getString("id"));
                System.out.println("ID Francina: " + id);
                getUser();
            }
        }
    }

    private void onPrepare(){
        listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startDescBookingUser(items[position]);
            }
        };
    }

    private void getUser(){
        apiService.selectUser(id).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                System.out.println("Response code: " + response.code());
                System.out.println(response.body());
                loadBooking();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                showMessage("Unable to submit post to API.");
                System.out.println(t.getCause() + t.getMessage());
            }
        });
    }

    private void loadBooking(){
        apiService.listAllReserves(id).enqueue(new Callback<List<Reserve>>() {
            @Override
            public void onResponse(Call<List<Reserve>> call, Response<List<Reserve>> response) {
                System.out.println("Response code: " + response.code());
                reserves = new String[response.body().size()];

                for(int i = 0; i < reserves.length; i++){
                    reserves[i] = response.body().get(i).toString();
                    dataReserves.add(response.body().get(i));
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, reserves);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(listener);

            }

            @Override
            public void onFailure(Call<List<Reserve>>call, Throwable t) {
                showMessage("Unable to submit post to API.");
                System.out.println(t.getCause() + t.getMessage());
            }
        });
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

    private void showMessage(String str){
        Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
    }
}