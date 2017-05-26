package com.example.olgacoll.airmns;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olgacoll.airmns.model.Info;
import com.example.olgacoll.airmns.remote.APIService;
import com.example.olgacoll.airmns.remote.APIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by olgacoll on 7/3/17.
 * This Activity shows info to company, app, contact and Google Maps NO!! ¿?¿?¿?
 */

public class Activity9_InfoActivity extends AppCompatActivity {

    //@Bind(R.id.textCompany)
    TextView textCompany, textApp;
    private APIService apiService;
    private static final String TAG = "Activity9_InfoActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout9_info);
        initComponents();
    }

    public void initComponents(){
        textCompany = (TextView)findViewById(R.id.textCompany_L9_info);
        textApp = (TextView)findViewById(R.id.infoApp_L9_info);
        apiService = APIUtils.getAPIService();
        loadInfo();
    }

    public void loadInfo(){
        apiService.selectInfo().enqueue(new Callback<Info>() {
            @Override
            public void onResponse(Call<Info> call, Response<Info> response) {
                System.out.println(response.body().toString());
                Log.i(TAG, "post submitted to API.\n" + response.body());
            }

            @Override
            public void onFailure(Call<Info> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }

    private void showMessage(String str){
        Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
    }
}