package com.example.olgacoll.airmns.remote;

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

    /*@GET("Search") //i.e https://api.test.com/Search?
    Call<Products> getProducts(@Query("one") String one, @Query("two") String two,
                               @Query("key") String key)*/

    /*@POST("/posts")
    @FormUrlEncoded
    Call<Post> savePost(@Field("title") String title,
                        @Field("body") String body);

    @PUT("/posts")
    @FormUrlEncoded
    Call<Post> addPost(@Field("title") String title,
                       @Field("body") String body);

    @GET("/posts")
    Call<List<Post>> getPosts();

    @POST("/posts")
    Call<Post> addPost(@Body Post post);

    @PUT("/posts/{id}")
    @FormUrlEncoded
    Call<Post> updatePost(@Path("id") long id,
                          @Field("title") String title,
                          @Field("body") String body,
                          @Field("userId") long userId);

    @DELETE("/posts/{id}")
    Call<Post> deletePost(@Path("id") long id);*/
}