package com.example.universityapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.util.ArrayList;

public class SaleBookAdapter extends RecyclerView.Adapter<SaleBookAdapter.SaleBookHolder>
{
ArrayList<SaleBook> list;
SaleBookHolder.OnCardClickListener listener;

    public SaleBookAdapter(ArrayList<SaleBook> list, SaleBookHolder.OnCardClickListener _listener) {
        this.list = list;
        this.listener = _listener;
    }

    @NonNull
    @Override
    public SaleBookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.salebookcard,parent , false);
        SaleBookHolder holder = new SaleBookHolder(v, listener);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SaleBookHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.author.setText(list.get(position).getAuthor());
        holder.description.setText(list.get(position).getDescription());
        holder.price.setText(list.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class SaleBookHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView title, author, description, price;
        Button moreDetails;
        OnCardClickListener listener;

        public SaleBookHolder(@NonNull View itemView,OnCardClickListener _listener) {
            super(itemView);

            moreDetails = itemView.findViewById(R.id.btn_salecard_moreDetails);
            title = itemView.findViewById(R.id.tv_salecard_title);
            author = itemView.findViewById(R.id.tv_salecard_author);
            price = itemView.findViewById(R.id.tv_salecard_price);
            description = itemView.findViewById(R.id.tv_salecard_description);
            this.listener = _listener;
            moreDetails.setOnClickListener(this);
        }

        public interface OnCardClickListener
        {
            public void onCardClick(int i);
        }

        @Override
        public void onClick(View v) {
            this.listener.onCardClick(getAdapterPosition());
        }
    }


}



