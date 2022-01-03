package com.dalwadibrothers.kunal.recyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.dalwadibrothers.kunal.recyclerviewdemo.databinding.MainActivityBinding;

public class MainActivity extends AppCompatActivity {

    private MainActivityBinding mainActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        mainActivityBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        /*
        1. Set Layout Manager for RecyclerView
         */

        mainActivityBinding.rvList.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, getResources().getStringArray(R.array.sample_names), getResources().getStringArray(R.array.sample_description));
        mainActivityBinding.rvList.setAdapter(recyclerViewAdapter);
    }
}