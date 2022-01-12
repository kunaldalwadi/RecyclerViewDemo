package com.dalwadibrothers.kunal.recyclerviewdemo;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dalwadibrothers.kunal.recyclerviewdemo.databinding.MainActivityBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private MainActivityBinding mainActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        /*
        1. Set Layout Manager for RecyclerView
        2. Initialize the AdapterClass
        3. setAdapter
         */

        mainActivityBinding.rvList.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapterStaticData recyclerViewAdapterStaticData = new RecyclerViewAdapterStaticData(this, getResources().getStringArray(R.array.sample_names), getResources().getStringArray(R.array.sample_description));
        mainActivityBinding.rvList.setAdapter(recyclerViewAdapterStaticData);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                makeApiCall();

            }
        }, 5000);
    }

    //Single responsibility principle - only makes the call
    public void makeApiCall() {

        NetworkApi networkApi = NetworkModule.getRetrofit().create(NetworkApi.class);

        Call<List<University>> uniNames = networkApi.getUniNames();

        Log.d(TAG, "makeApiCall: URL = " + uniNames.request().url().toString());

        uniNames.enqueue(new Callback<List<University>>() {
            @Override
            public void onResponse(Call<List<University>> call, Response<List<University>> response) {

                if (response.isSuccessful())
                {
                    if (response.body() != null)
                    {
                        List<University> universityList = response.body();
                        sendItToAdapterNetworkData(universityList);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<University>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failure during API call : " + t.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });
    }

    private void sendItToAdapterNetworkData(List<University> universityList) {
        mainActivityBinding.rvList.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapterNetworkData recyclerViewAdapterNetworkData = new RecyclerViewAdapterNetworkData(universityList);
        mainActivityBinding.rvList.setAdapter(recyclerViewAdapterNetworkData);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainActivityBinding = null;
    }
}