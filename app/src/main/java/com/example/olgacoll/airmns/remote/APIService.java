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

    //@GET("/login")
    /*Call<String> checkLogin(@Field("user") String user,
                          @Field("pwd") String pwd);*/

    //Post mirar layout registre, modificar variables es facil? Pensar a afegir id user a la taula address
    @GET("login")
    Call<User> login(@Query("user") String user);

    @POST("createUser")
    @FormUrlEncoded
    Call<String> addUser(@Field("mail") String mail,
                         @Field("password") String password,
                         @Field("type") String type,
                         @Field("name") String name,
                         @Field("lastname") String lastname,
                         @Field("prefix_phone") String prefix_phone,
                         @Field("phone") String phone);

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