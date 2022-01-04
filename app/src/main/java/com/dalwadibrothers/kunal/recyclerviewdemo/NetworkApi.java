package com.dalwadibrothers.kunal.recyclerviewdemo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface NetworkApi {

    @GET("/search?country=United+States")
    Call<University> getUniNames();
}
