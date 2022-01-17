package com.dalwadibrothers.kunal.recyclerviewdemo.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dalwadibrothers.kunal.recyclerviewdemo.R;
import com.dalwadibrothers.kunal.recyclerviewdemo.databinding.MainActivityBinding;
import com.dalwadibrothers.kunal.recyclerviewdemo.model.db.University;
import com.dalwadibrothers.kunal.recyclerviewdemo.model.db.UniversityDAO;
import com.dalwadibrothers.kunal.recyclerviewdemo.model.network.NetworkApi;
import com.dalwadibrothers.kunal.recyclerviewdemo.model.network.NetworkModule;
import com.dalwadibrothers.kunal.recyclerviewdemo.viewmodel.MainActivityViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private MainActivityBinding mainActivityBinding;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        setupObserver();


         /*
        1. Set Layout Manager for RecyclerView
        2. Initialize the AdapterClass
        3. setAdapter
         */
        mainActivityBinding.rvList.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapterStaticData recyclerViewAdapterStaticData = new RecyclerViewAdapterStaticData(this, getResources().getStringArray(R.array.sample_names), getResources().getStringArray(R.array.sample_description));
        mainActivityBinding.rvList.setAdapter(recyclerViewAdapterStaticData);

    }

    private void setupObserver() {

       mainActivityViewModel.getAllUniversitiesListLiveData().observe(this, new Observer<List<University>>() {
           @Override
           public void onChanged(List<University> universities) {
               sendItToAdapterNetworkData(universities);
           }
       });

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