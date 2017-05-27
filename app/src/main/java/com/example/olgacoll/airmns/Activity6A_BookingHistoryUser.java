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

import com.example.olgacoll.airmns.model.Booking;
import com.example.olgacoll.airmns.model.User;
import com.example.olgacoll.airmns.remote.APIService;
import com.example.olgacoll.airmns.remote.APIUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    double price;
    String time, observations, value, date;
    Date bookingDate;
    int id;
    ListView listView;
    TextView textViewTitle, textViewInfo;
    String[] bookings;
    String[] items;
    List<Booking> dataBooking;
    AdapterView.OnItemClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_history_user);

        initComponents();
        initBundle();
        onPrepare();
    }

    private void initComponents(){
        apiService = APIUtils.getAPIService();
        dataBooking = new ArrayList();

        textViewTitle = (TextView)findViewById(R.id.tvtitle);
        textViewInfo= (TextView)findViewById(R.id.tvinfo);
        listView = (ListView)findViewById(R.id.listview);
    }

    private void initBundle() {
        bundle = this.getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getString("id") != null) {
                id = Integer.parseInt(bundle.getString("id"));
                getUser();
            }
        }
    }

    private void onPrepare(){
        listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                date = sdf.format(dataBooking.get(position).getDate_time());
                value = dataBooking.get(position).getObservations();
                observations = dataBooking.get(position).getComments();
                price = dataBooking.get(position).getTotal_price();
                //date = dataBooking.get(position).getDate_time();

                bookingDate = dataBooking.get(position).getDate_time();
                startDescBookingUser(dataBooking.get(position).toString());
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
        apiService.listAllReserves(id).enqueue(new Callback<List<Booking>>() {
            @Override
            public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                System.out.println("Response code: " + response.code());
                bookings = new String[response.body().size()];

                for(int i = 0; i < bookings.length; i++){
                    bookings[i] = response.body().get(i).toString();
                    dataBooking.add(response.body().get(i));
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, bookings);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(listener);
            }

            @Override
            public void onFailure(Call<List<Booking>>call, Throwable t) {
                showMessage("Unable to submit post to API.");
                System.out.println(t.getCause() + t.getMessage());
            }
        });
    }

    private void setBundles(){
        bundle.putString("time", time);
        bundle.putString("observations", observations);
        bundle.putString("value", value);
        bundle.putDouble("price", price);
        bundle.putString("date", date);
    }

    private void startDescBookingUser(String booking){
        setBundles();
        //bundle.putString("booking", booking);

        Intent intent = new Intent(this, DescBookingUserActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    private void showMessage(String str){
        Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
    }
}