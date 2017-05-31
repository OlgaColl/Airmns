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
 * Created by olgacoll on 28/5/17.
 */

public class Activity8A_BookingToRate extends AppCompatActivity {

    //--Attributtes--
    //Objects
    private static final String TAG = "Activity8A_BookingToRate";
    APIService apiService;
    Bundle bundle;
    User user;
    String name, lastname, mail, prefix, phone, address, start_time, duration, observations, value, date;
    Date bookingDate;
    int id, id_address, id_reserve;
    String order = "";
    //Views
    ListView listView;
    TextView textViewTitle, textViewInfo;
    String[] bookings;
    List<Booking> dataBooking;
    AdapterView.OnItemClickListener listener;



    //--OnCreate--

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout8a_booking_to_rate);
        //Prepares
        initComponents();
        initBundle();
        onPrepare();
    }



    //--Prepares--

    private void initComponents() {
        //Api
        apiService = APIUtils.getAPIService();
        dataBooking = new ArrayList();
        //Views
        textViewTitle = (TextView) findViewById(R.id.tvtitle);
        textViewInfo = (TextView) findViewById(R.id.tvinfo);
        listView = (ListView) findViewById(R.id.listview);
    }

    private void initBundle() {
        bundle = this.getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getString("id") != null) {
                id = Integer.parseInt(bundle.getString("id"));
                getUser();
            }

            if (bundle.getString("order") != null) {
                order = bundle.getString("order");
            }
        }
    }

    private void onPrepare() {
        listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                date = sdf.format(dataBooking.get(position).getDate_time());
                value = dataBooking.get(position).getObservations();
                observations = dataBooking.get(position).getComments();
                start_time = String.valueOf(dataBooking.get(position).getStart_time());
                id_address = dataBooking.get(position).getId_address();
                id_reserve = dataBooking.get(position).getId_reserve();
                duration = String.valueOf(dataBooking.get(position).getLong_time());

                bookingDate = dataBooking.get(position).getDate_time();
                //startRateBooking();
                loadAddress();
            }
        };
    }



    //--Methods--

    private void getUser() {
        apiService.selectUser(id).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                //Attributes
                name = response.body().getName();
                lastname = response.body().getLastname();
                mail = response.body().getMail();
                prefix = response.body().getPrefix_phone();
                phone = response.body().getPhone();
                //Load Booking
                loadBooking();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                showMessage("Can't access to server..");
            }
        });
    }

    private void loadAddress() {
        //ApiService
        apiService.selectAddress(id_address).enqueue(new Callback<Address>() {
            @Override
            public void onResponse(Call<Address> call, Response<Address> response) {
                //Get value
                address = response.body().toString();
                //When is founded
                startRateBooking();
            }

            @Override
            public void onFailure(Call<Address> call, Throwable t) {
                //Notify
                showMessage("Can't access to server.");
            }
        });
    }

    private void loadBooking() {
        //ApiService
        apiService.listBookingsNotRate(id).enqueue(new Callback<List<Booking>>() {
            @Override
            public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                //Instance bookings array
                bookings = new String[response.body().size()];
                dataBooking.clear();
                //Insert bookings in array
                for (int i = 0; i < bookings.length; i++) {
                    bookings[i] = response.body().get(i).toString();
                    dataBooking.add(response.body().get(i));
                }
                //If list is empty notify
                if (dataBooking.isEmpty()) showMessage("There aren't bookings.");
                //ArrayAdapter & Listener
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, bookings);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(listener);
            }

            @Override
            public void onFailure(Call<List<Booking>> call, Throwable t) {
                showMessage("Can't access to server.");
            }
        });
    }

    private void setBundles() {
        bundle.putInt("id_reserve", id_reserve);
        bundle.putString("duration", duration);
        bundle.putString("observations", observations);
        bundle.putString("start_time", start_time);
        bundle.putString("address", address);
        bundle.putString("date", date);
        bundle.putString("name", name);
        bundle.putString("lastname", lastname);
        bundle.putString("mail", mail);
        bundle.putString("prefix", prefix);
        bundle.putString("phone", phone);
    }

    private void startRateBooking(){
        //When finish set bundles
        setBundles();
        //Intent
        Intent intent = new Intent(this, Activity8B_RateBooking.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }



    //--ShowMessage--

    private void showMessage(String str) {
        Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
    }



    //--OnResume--

    @Override
    protected void onResume() {
        loadBooking();
        super.onResume();
    }

}

