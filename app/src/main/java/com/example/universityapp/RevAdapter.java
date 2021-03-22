package com.example.universityapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RevAdapter extends RecyclerView.Adapter<RevAdapter.RevHolder>
{
    ArrayList<BookReview> reviewList;

    public RevAdapter(ArrayList<BookReview> reviewList) {
        this.reviewList = reviewList;
    }

    @NonNull
    @Override
    public RevHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.reviewcard, parent, false);
        RevHolder holder = new RevHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RevHolder holder, int position) {
        holder.title.setText(reviewList.get(position).getTitle());
        holder.uName.setText(reviewList.get(position).getuName());
        holder.date.setText(reviewList.get(position).getDate());
        holder.isbn.setText(reviewList.get(position).getISBN());
        holder.review.setText(reviewList.get(position).getText());


    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public class RevHolder extends RecyclerView.ViewHolder
    {
        TextView title, uName, date, isbn, review;

        public RevHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_card_review_title);
            uName = itemView.findViewById(R.id.tv_card_review_uName);
            date = itemView.findViewById(R.id.tv_card_review_date);
            isbn = itemView.findViewById(R.id.tv_card_review_isbn);
            review = itemView.findViewById(R.id.tv_card_review_review);
        }
    }

}
