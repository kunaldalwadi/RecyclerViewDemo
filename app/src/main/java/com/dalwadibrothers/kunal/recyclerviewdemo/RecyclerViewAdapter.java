package com.dalwadibrothers.kunal.recyclerviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    Context context;
    String[] stringsName;
    String[] stringsDescription;

    public RecyclerViewAdapter(Context context, String[] stringsName, String[] stringsDescription) {
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

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_row_recyclerview, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);

        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        /*
        This function binds the data with the UI
         */

        holder.tvName.setText(stringsName[position]);
        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Clicked this name " + stringsName[position], Toast.LENGTH_SHORT).show();
            }
        });

        holder.tvDesc.setText(stringsDescription[position]);
        holder.tvDesc.setOnClickListener(new View.OnClickListener() {
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

        TextView tvName;
        TextView tvDesc;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDesc = itemView.findViewById(R.id.tv_description);

        }
    }

}


