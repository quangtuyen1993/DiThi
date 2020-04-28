package com.example.dithi.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private static Retrofit INSTANCE;
    private static String BASE_URL = "http://192.168.1.135:3000/";

    public static Retrofit getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return INSTANCE;
    }
}
