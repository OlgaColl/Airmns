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

import com.example.olgacoll.airmns.model.Address;
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

    //--Attributtes--
    //Objects
    private static final String TAG = "Activity6A_BookingHistoryUser";
    APIService apiService;
    Bundle bundle;
    User user;
    double price;
    String time, observations, value, date, address, start_time, comments;
    Date bookingDate;
    int id, id_address;
    String order="";
    //Views
    ListView listView;
    TextView textViewTitle, textViewInfo;
    String[] bookings;
    String[] items;
    List<Booking> dataBooking;
    AdapterView.OnItemClickListener listener;



    //--OnCreate--

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_history_user);
        //Prepares
        initComponents();
        initBundle();
        onPrepare();
    }



    //--Prepares--

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

            if (bundle.getString("order") != null) {
                order= bundle.getString("order");
            }
        }
    }

    private void onPrepare(){
        listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                date = sdf.format(dataBooking.get(position).getDate_time());
                value = String.valueOf( dataBooking.get(position).getQualification_service() );
                observations = dataBooking.get(position).getObservations();
                comments = dataBooking.get(position).getComments();
                price = dataBooking.get(position).getTotal_price();
                start_time = String.valueOf(dataBooking.get(position).getStart_time());
                time = String.valueOf(dataBooking.get(position).getLong_time());
                //date = dataBooking.get(position).getDate_time();

                bookingDate = dataBooking.get(position).getDate_time();
                id_address = dataBooking.get(position).getId_address();

                startDescBookingUser(dataBooking.get(position).toString());
            }
        };
    }

    private void loadAddress() {
        System.out.println("ID_ADDRESS: " + id_address);
        apiService.selectAddress(id_address).enqueue(new Callback<Address>() {
            @Override
            public void onResponse(Call<Address> call, Response<Address> response) {
                address = response.body().toString();
            }

            @Override
            public void onFailure(Call<Address> call, Throwable t) {
                showMessage("Unable to submit post to API.");
            }
        });
    }


    //--Methods--

    private void getUser(){
        apiService.selectUser(id).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
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
        //More than now (for professional)
        if(order.equals("more_now")){
            apiService.listBookingsByDate(id).enqueue(new Callback<List<Booking>>() {
                @Override
                public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                    System.out.println("Response code: " + response.code());
                    bookings = new String[response.body().size()];

                    for (int i = 0; i < bookings.length; i++) {
                        bookings[i] = response.body().get(i).toString();
                        dataBooking.add(response.body().get(i));
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, bookings);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(listener);
                }

                @Override
                public void onFailure(Call<List<Booking>> call, Throwable t) {
                    showMessage("Unable to submit post to API.");
                    System.out.println(t.getCause() + t.getMessage());
                    showMessage("Can't access to server.");
                }
            });
        }
        //All time
        else{
            apiService.listAllReserves(id).enqueue(new Callback<List<Booking>>() {
                @Override
                public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                    System.out.println("Response code: " + response.code());
                    bookings = new String[response.body().size()];

                    for (int i = 0; i < bookings.length; i++) {
                        bookings[i] = response.body().get(i).toString();
                        dataBooking.add(response.body().get(i));
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, bookings);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(listener);
                }

                @Override
                public void onFailure(Call<List<Booking>> call, Throwable t) {
                    showMessage("Unable to submit post to API.");
                    System.out.println(t.getCause() + t.getMessage());
                    showMessage("Can't access to server.");
                }
            });
        }
    }

    private void setBundles(){
        bundle.putString("start_time", start_time);
        bundle.putString("time", time);
        bundle.putString("observations", observations);
        bundle.putString("comments", comments);
        bundle.putString("value", value);
        bundle.putDouble("price", price);
        bundle.putString("date", date);
        bundle.putString("addressSelected", address);
    }

    private void startDescBookingUser(String booking){
        loadAddress();
        System.out.println(address);
        setBundles();
        Intent intent = new Intent(this, Activity6B_DescBookingUserActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }



    //--ShowMessage--

    private void showMessage(String str){
        Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
    }

}