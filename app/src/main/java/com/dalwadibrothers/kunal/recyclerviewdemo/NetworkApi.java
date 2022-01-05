package com.dalwadibrothers.kunal.recyclerviewdemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NetworkApi {

    @GET("/search?country=United+States")
    Call<List<University>> getUniNames();
}
