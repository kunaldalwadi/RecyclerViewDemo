package com.dalwadibrothers.kunal.recyclerviewdemo.view.activity;

import android.os.Bundle;

import com.dalwadibrothers.kunal.recyclerviewdemo.BaseApplication;
import com.dalwadibrothers.kunal.recyclerviewdemo.R;
import com.dalwadibrothers.kunal.recyclerviewdemo.databinding.MainActivityBinding;
import com.dalwadibrothers.kunal.recyclerviewdemo.model.db.University;
import com.dalwadibrothers.kunal.recyclerviewdemo.view.adapter.RecyclerViewAdapterNetworkData;
import com.dalwadibrothers.kunal.recyclerviewdemo.view.adapter.RecyclerViewAdapterStaticData;
import com.dalwadibrothers.kunal.recyclerviewdemo.viewmodel.MainActivityViewModel;
import com.dalwadibrothers.kunal.recyclerviewdemo.viewmodel.ViewModelFactory;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private MainActivityBinding mainActivityBinding;
    private MainActivityViewModel mainActivityViewModel;
    private ViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

         /*
        1. Set Layout Manager for RecyclerView
        2. Initialize the AdapterClass
        3. setAdapter
         */
//        mainActivityBinding.rvList.setLayoutManager(new LinearLayoutManager(this));
//        RecyclerViewAdapterStaticData recyclerViewAdapterStaticData = new RecyclerViewAdapterStaticData(this, getResources().getStringArray(R.array.sample_names), getResources().getStringArray(R.array.sample_description));
//        mainActivityBinding.rvList.setAdapter(recyclerViewAdapterStaticData);

        viewModelFactory = new ViewModelFactory(((BaseApplication)getApplication()).getUniversityRepository());

        mainActivityViewModel = new ViewModelProvider(this, viewModelFactory).get(MainActivityViewModel.class);
        setupObserver();
        fetchData();

    }

    private void setupObserver() {

        mainActivityViewModel.getLiveDataFromViewModel().observe(this, new Observer<List<University>>() {
            @Override
            public void onChanged(List<University> universities) {
                sendItToAdapterNetworkData(universities);
            }
        });
    }

    private void fetchData() {
        mainActivityViewModel.getDataFromRepository();
    }

    private void sendItToAdapterNetworkData(List<University> universityList) {

         /*
        1. Set Layout Manager for RecyclerView
        2. Initialize the AdapterClass
        3. setAdapter
         */

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