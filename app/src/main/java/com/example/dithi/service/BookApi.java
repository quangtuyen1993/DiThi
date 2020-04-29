package com.example.dithi.service;

import com.example.dithi.model.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BookApi {
    @GET("/book")
    Call<List<Book>> fetchAllBook();
}
