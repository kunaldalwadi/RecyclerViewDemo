package com.dalwadibrothers.kunal.recyclerviewdemo;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.dalwadibrothers.kunal.recyclerviewdemo.databinding.MainActivityBinding;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private MainActivityBinding mainActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        /*
        1. Set Layout Manager for RecyclerView
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

        Call<University> uniNames = networkApi.getUniNames();

        Log.d(TAG, "makeApiCall: URL = " + uniNames.request().url().toString());

        uniNames.enqueue(new Callback<University>() {
            @Override
            public void onResponse(Call<University> call, Response<University> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        sendItToAdapterNetworkData(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<University> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failure during API call : " + t.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });
    }

    private void sendItToAdapterNetworkData(University body) {

        University university = new University(body.getCountry(), body.getDomains(), body.getName(),body.getStateProvince(), body.getWeb_pages(), body.getAlpha_two_code());

        mainActivityBinding.rvList.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapterNetworkData recyclerViewAdapterNetworkData = new RecyclerViewAdapterNetworkData(university);
        mainActivityBinding.rvList.setAdapter(recyclerViewAdapterNetworkData);

    }
}