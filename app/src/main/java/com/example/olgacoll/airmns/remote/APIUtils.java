package com.example.olgacoll.airmns.remote;

/**
 * Created by olgacoll on 17/5/17.
 */

public class APIUtils {

    private APIUtils(){}

    public static final String BASE_URL = "http://provenapps.cat:8080/AirmnsWebService/services/UserService/";

    public static APIService getAPIService(){

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}