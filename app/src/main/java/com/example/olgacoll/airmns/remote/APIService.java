package com.example.olgacoll.airmns.remote;

import com.example.olgacoll.airmns.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by olgacoll on 17/5/17.
 */

public interface APIService {

    @GET("/login")
    Call<User> checkLogin(@Field("user") String user,
                          @Field("pwd") String pwd);

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