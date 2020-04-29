package com.example.dithi.service;

import com.example.dithi.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ProductApi {
    @GET("/product")
    Call<List<Product>> onFetchProduct();

    @POST("/product")
    @FormUrlEncoded
    Call<Product> onInsert(
            @Field("name")
            String name,
            @Field("description")
            String description,
            @Field("type")
            String type,
            @Field("price")
            String price
    );
}
