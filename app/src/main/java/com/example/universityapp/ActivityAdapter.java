package com.example.universityapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ActivityHolder>
{
    ArrayList<Activity> list;

    public ActivityAdapter(ArrayList<Activity> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ActivityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activitycard, parent, false);
        ActivityHolder holder = new ActivityHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityHolder holder, int position) {
        holder.contact.setText(list.get(position).getDescription());
        holder.location.setText(list.get(position).getLocation());
        holder.time.setText(list.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ActivityHolder extends RecyclerView.ViewHolder
    {
        TextView time, location, contact;

        public ActivityHolder(@NonNull View itemView) {
            super(itemView);

            time = itemView.findViewById(R.id.tv_activeCard_time);
            location = itemView.findViewById(R.id.tv_activeCard_location);
            contact = itemView.findViewById(R.id.tv_activCard_descript);
        }

    }
}
