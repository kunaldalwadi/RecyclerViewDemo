package com.dalwadibrothers.kunal.recyclerviewdemo.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dalwadibrothers.kunal.recyclerviewdemo.databinding.OneRowRecyclerView;

/*
This adapter is taking data from strings.xml file

This adapter is an example of how to work with static data.
 */
public class RecyclerViewAdapterStaticData extends RecyclerView.Adapter<RecyclerViewAdapterStaticData.RecyclerViewHolder> {

    private Context context;
    private String[] stringsName;
    private String[] stringsDescription;

    public RecyclerViewAdapterStaticData(Context context, String[] stringsName, String[] stringsDescription) {
        this.context = context;
        this.stringsName = stringsName;
        this.stringsDescription = stringsDescription;
    }


    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        /*
        This function runs everytime this recyclerview is created.

        3 Steps to follow here.
        1. Create the View using LayoutInflator and inflate the single row view you custom created.
        2. Pass that view and give it to your custom ViewHolder you made (which mimic's the single row xml)
        3. return that ViewHolder object
         */
        OneRowRecyclerView oneRowRecyclerView = OneRowRecyclerView.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(oneRowRecyclerView);

        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, @SuppressLint("RecyclerView") int position) {

        /*
        This function binds the data with the UI
         */

        holder.oneRowRecyclerView.tvName.setText(stringsName[position]);
        holder.oneRowRecyclerView.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Clicked this name " + stringsName[position], Toast.LENGTH_SHORT).show();
            }
        });

        holder.oneRowRecyclerView.tvDescription.setText(stringsDescription[position]);
        holder.oneRowRecyclerView.tvDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "This is Description Speaking", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return stringsName.length;
    }


    static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        /*
        ViewHolder is the one that has to mimic the custom one_row_view
        so that it can bind data to the respective UI elements.

        Since in out current case we have 2 TextViews, 1 ImageView and 1 TextView(shows time)
        we will create them here and map them to the xml layout.(unless we do viewBinding)
         */

        OneRowRecyclerView oneRowRecyclerView;

        public RecyclerViewHolder(@NonNull OneRowRecyclerView oneRowRecyclerView) {
            super(oneRowRecyclerView.getRoot());

            this.oneRowRecyclerView = oneRowRecyclerView;
        }
    }

}


