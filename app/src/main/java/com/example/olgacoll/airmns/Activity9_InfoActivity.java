package com.example.olgacoll.airmns;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olgacoll.airmns.model.Info;
import com.example.olgacoll.airmns.remote.APIService;
import com.example.olgacoll.airmns.remote.APIUtils;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by olgacoll on 7/3/17.
 */

public class Activity9_InfoActivity extends AppCompatActivity {

    //--Attributes--

    TextView textViewCompanyInfo, textViewAppInfo, textViewDevApp, textViewMail, textViewPhone;
    String textCompany, textApp, textDevApp, textMail, textPhone;
    private APIService apiService;
    private static final String TAG = "Activity9_InfoActivity";



    //--OnCreate--

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout9_info);
        //Prepare
        initComponents();
    }



    //--OnPrepare--

    public void initComponents(){
        textViewCompanyInfo = (TextView)findViewById(R.id.company_info_text_L9_info);
        textViewAppInfo = (TextView)findViewById(R.id.app_info_text_L9_info);
        textViewDevApp = (TextView)findViewById(R.id.dev_app_info_text_L9_info);
        textViewMail = (TextView)findViewById(R.id.mail_info_text_L9_info);
        textViewPhone = (TextView)findViewById(R.id.phone_info_text_L9_info);
        apiService = APIUtils.getAPIService();
        loadInfo();
    }



    //--Methods--

    private void loadInfo(){
        apiService.selectInfo().enqueue(new Callback<Info>() {
            @Override
            public void onResponse(Call<Info> call, Response<Info> response) {
                System.out.println(response.body().toString());
                if(response.isSuccessful()){
                    System.out.println("Status code " + response.code());
                    textCompany = response.body().getCompany_info();
                    textApp = response.body().getApp_information();
                    textDevApp = response.body().getCustomer_service_manager();
                    textMail = response.body().getMail();
                    textPhone = response.body().getPhone_number();
                    loadData();
                    Log.i(TAG, "post submitted to API.\n" + response.body().toString());
                }
            } 

            @Override
            public void onFailure(Call<Info> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
                showMessage("Unable to submit post to API.");
            }
        });
    }

    private void loadData(){
        textViewCompanyInfo.setText(textCompany);
        textViewAppInfo.setText(textApp);
        textViewDevApp.setText("Developers: " + textDevApp);
        textViewMail.setText("Mail: " + textMail);
        textViewPhone.setText("Phone: " + textPhone);
    }



    //--ShowMessage--

    private void showMessage(String str){
        Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
    }

}