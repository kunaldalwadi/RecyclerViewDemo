package com.dalwadibrothers.kunal.recyclerviewdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dalwadibrothers.kunal.recyclerviewdemo.databinding.OneRecyclerViewNetworkData;

import java.util.List;


/*
This adapter is taking data from internet using Retrofit

This adapter is an example of how to work with data coming from a URL
 */
public class RecyclerViewAdapterNetworkData extends RecyclerView.Adapter<RecyclerViewAdapterNetworkData.RecyclerViewHolderNetworkData> {

    private List<University> universities;

    public RecyclerViewAdapterNetworkData(List<University> universities) {
        this.universities = universities;
    }

    @NonNull
    @Override
    public RecyclerViewHolderNetworkData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new RecyclerViewHolderNetworkData(OneRecyclerViewNetworkData.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolderNetworkData holder, int position) {

        holder.oneRecyclerViewNetworkData.tvUniname.setText(universities.get(position).getName());
        holder.oneRecyclerViewNetworkData.tvCountryname.setText(universities.get(position).getCountry());
        holder.oneRecyclerViewNetworkData.tvWeblink.setText(universities.get(position).getWeb_pages().toString());
        holder.oneRecyclerViewNetworkData.tvDomains.setText(universities.get(position).getDomains().toString());

        holder.oneRecyclerViewNetworkData.cvUnilistCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.oneRecyclerViewNetworkData.tvWeblink.getVisibility() == View.GONE)
                {
                    holder.oneRecyclerViewNetworkData.tvWeblink.setVisibility(View.VISIBLE);
                }
                else if (holder.oneRecyclerViewNetworkData.tvWeblink.getVisibility() == View.VISIBLE)
                {
                    holder.oneRecyclerViewNetworkData.tvWeblink.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return universities.size();
    }

    public static class RecyclerViewHolderNetworkData extends RecyclerView.ViewHolder {

        private OneRecyclerViewNetworkData oneRecyclerViewNetworkData;

        public RecyclerViewHolderNetworkData(@NonNull OneRecyclerViewNetworkData oneRecyclerViewNetworkData) {
            super(oneRecyclerViewNetworkData.getRoot());

            this.oneRecyclerViewNetworkData = oneRecyclerViewNetworkData;
        }
    }
}
