package com.dalwadibrothers.kunal.recyclerviewdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dalwadibrothers.kunal.recyclerviewdemo.databinding.OneRecyclerViewNetworkData;

public class RecyclerViewAdapterNetworkData extends RecyclerView.Adapter<RecyclerViewAdapterNetworkData.RecyclerViewHolderNetworkData> {

    private University university;

    public RecyclerViewAdapterNetworkData(University university) {
        this.university = university;
    }

    @NonNull
    @Override
    public RecyclerViewHolderNetworkData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new RecyclerViewHolderNetworkData(OneRecyclerViewNetworkData.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolderNetworkData holder, int position) {

        holder.oneRecyclerViewNetworkData.tvUniname.setText(university.getName());
        holder.oneRecyclerViewNetworkData.tvCountryname.setText(university.getCountry());
        holder.oneRecyclerViewNetworkData.tvWeblink.setText(university.getWeb_pages().get(position));
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public static class RecyclerViewHolderNetworkData extends RecyclerView.ViewHolder {

        private OneRecyclerViewNetworkData oneRecyclerViewNetworkData;

        public RecyclerViewHolderNetworkData(@NonNull OneRecyclerViewNetworkData oneRecyclerViewNetworkData) {
            super(oneRecyclerViewNetworkData.getRoot());

            this.oneRecyclerViewNetworkData = oneRecyclerViewNetworkData;
        }
    }
}
