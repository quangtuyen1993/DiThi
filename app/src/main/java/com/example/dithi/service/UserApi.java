package com.example.dithi.service;

import com.example.dithi.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserApi {
    @GET("user")
    Call<List<User>> onFetchAllUser();

    @POST("user/login")
    @FormUrlEncoded
    Call<User> onLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @POST("user/register")
    @FormUrlEncoded
    Call<User> onRegister(
            @Field("email") String email,
            @Field("password") String password,
            @Field("name") String name
    );
}
