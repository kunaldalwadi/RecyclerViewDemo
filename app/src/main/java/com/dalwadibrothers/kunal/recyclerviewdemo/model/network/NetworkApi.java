package com.dalwadibrothers.kunal.recyclerviewdemo.model.network;

import com.dalwadibrothers.kunal.recyclerviewdemo.model.db.University;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NetworkApi {

    @GET("/search?country=United+States")
    Call<List<University>> getUniNames();
}
