package com.example.olgacoll.airmns.remote;

import com.example.olgacoll.airmns.model.Address;
import com.example.olgacoll.airmns.model.Availability;
import com.example.olgacoll.airmns.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by olgacoll on 17/5/17.
 */

public interface APIService {
    
    @GET("login")
    Call<User> login(@Query("user") String user);

    @POST("createUser")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<String> addUser(@Query("mail") String mail,
                         @Query("password") String password,
                         @Query("type") String type,
                         @Query("name") String name,
                         @Query("lastname") String lastname,
                         @Query("prefix_phone") String prefix_phone,
                         @Query("phone") String phone);

    @POST("modifyUser")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<String> editUser(@Query("user_id") int id,
                          @Query("mail") String mail,
                          @Query("password") String password,
                          @Query("name") String name,
                          @Query("lastname") String lastname,
                          @Query("prefix_phone") String prefix_phone,
                          @Query("phone") String phone);

    //TODO Gestionar mail com a user not found.
    @GET("selectUser")
    Call<String> selectUser(@Query("user_id") int id);

    @GET("listAllAddress")
    Call<List<Address>> listAllAddress(@Query("id_user") int id);

    @POST("addAddress")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<String> addAddress(@Query("user_id") int user_id,
                            @Query("street") String street,
                            @Query("number") String number,
                            @Query("floor") String floor,
                            @Query("stair") String stair,
                            @Query("door") String door,
                            @Query("city") String city,
                            @Query("postal_code") String postal_code);

    @POST("modifyAddress")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<String> modifyAddress(@Query("id_address") int id_address,
                               @Query("street") String street,
                               @Query("number") String number,
                               @Query("floor") String floor,
                               @Query("stair") String stair,
                               @Query("door") String door,
                               @Query("city") String city,
                               @Query("postal_code") String postal_code);

    @POST("removeAddress")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<String> removeAddress(@Query("id_address") int id_address);

    @GET
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<String> selectAddress(@Query("id_address") int id_address);

    @GET("listAvailability")
    Call<List<Availability>> listAvailability(@Query("id_user") int id);

    @POST
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<String> inputAvailability(@Query("date") String date,
                                   @Query("id_user") int id_user,
                                   @Query("start_time") int start_time,
                                   @Query("end_time") int end_time);

    @POST
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<String> updateAvailability(@Query("date") String date,
                                   @Query("id_user") int id_user,
                                   @Query("start_time") int start_time,
                                   @Query("end_time") int end_time);

    @POST
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<String> removeAvailability(@Query("date") String date,
                                    @Query("id_user") int id_user);

    @GET("listAllReserves")
    Call<String> listAllReserves(@Query("id_user") int id);

    @GET("listReservesByDate")
    Call<String> listReservesByDate(@Query("id_user") int id);

    @POST
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<String> addReserve(@Query("id_user_client") int id_user_client,
                            @Query("id_user_professional") int id_user_professional,
                            @Query("id_address") int id_address,
                            @Query("date_time") String date_time,
                            @Query("start_time") int start_time,
                            @Query("long_time") int long_time,
                            @Query("total_price") double total_price,
                            @Query("observations") String observations,
                            @Query("iva") double iva);

    @POST
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<String> rateReserve(@Query("id_reservation") int id_reservation,
                             @Query("qualification_service") int qualification_service,
                             @Query("comments") String comments);

    @GET("listReservesNotRate")
    Call<String> listReservesNotRate(@Query("id_user") int id_user);
}