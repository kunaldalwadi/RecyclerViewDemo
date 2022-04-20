package com.dalwadibrothers.kunal.recyclerviewdemo.model.remotedatasource;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.dalwadibrothers.kunal.recyclerviewdemo.model.db.University;
import com.dalwadibrothers.kunal.recyclerviewdemo.model.network.NetworkApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class UniversityRDS {

    private static final String TAG = UniversityRDS.class.getSimpleName();
    private final NetworkApi networkApi;
    private static UniversityRDS universityRDS;

    public UniversityRDS(NetworkApi networkApi) {
        this.networkApi = networkApi;
    }

    public static UniversityRDS getInstance(NetworkApi networkApi) {
        if (universityRDS == null) {
            synchronized (UniversityRDS.class) {
                if (universityRDS == null) {
                    universityRDS = new UniversityRDS(networkApi);
                }
            }
        }
        return universityRDS;
    }
    
    //Single responsibility principle - only makes the call
    public List<University> makeNetworkCallGetUniversities() {

        Call<List<University>> uniNames = networkApi.getUniNames();

        Log.d(TAG, "makeApiCall: URL = " + uniNames.request().url().toString());
        Log.d(TAG, "makeApiCall: -------------------------------------------------------------");

        try {
            Response<List<University>> response = uniNames.execute();

            if (response.isSuccessful()) {
                    if (response.body() != null) {
                        return response.body();
                    }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
