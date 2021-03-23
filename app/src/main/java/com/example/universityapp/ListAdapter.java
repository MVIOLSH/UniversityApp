package com.example.universityapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.BooksHolder> {

    ArrayList<LibraryBook> list;
    BooksHolder.OnCardClickListener listener;

    public ListAdapter(ArrayList<LibraryBook> list, BooksHolder.OnCardClickListener _listener) {
        this.list = list;
        this.listener = _listener;
    }

    @NonNull
    @Override
    public BooksHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.librarybookcardlayout,parent , false);
        BooksHolder holder = new BooksHolder(v,listener);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BooksHolder holder, int position) {

        holder.title.setText(list.get(position).getTitle());
        holder.author.setText(list.get(position).getAuthor());
        holder.category.setText(list.get(position).getCategory());
        holder.isbn.setText(list.get(position).getIsbn());
        Picasso.get().load(list.get(position).getImgUrl()).fit().into(holder.iv);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class BooksHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, author, category, isbn;
        ImageView iv;
        Button more;
        OnCardClickListener listener;

        public BooksHolder(@NonNull View itemView, OnCardClickListener _listener) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_library_card_title);
            author= itemView.findViewById(R.id.tv_library_card_author);
            category = itemView.findViewById(R.id.tv_library_card_category);
            isbn = itemView.findViewById(R.id.tv_library_card_isbnn);
            iv = itemView.findViewById(R.id.iv_library_card_cover);
            more = itemView.findViewById(R.id.btn_library_card_more);
            this.listener = _listener;
            more.setOnClickListener( this);
        }

        public interface OnCardClickListener
        {
          public void onCardClick(int i);
        }

        @Override
        public void onClick(View v)
        {
            this.listener.onCardClick(getAdapterPosition());
        }
    }

}
