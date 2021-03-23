package com.example.universityapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ForumReplyAdapter extends RecyclerView.Adapter<ForumReplyAdapter.ForumReplyHolder>
{
    ArrayList<ForumTopic> list;

    public ForumReplyAdapter(ArrayList<ForumTopic> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ForumReplyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.forumreplycard, parent, false);
        ForumReplyHolder holder = new ForumReplyHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ForumReplyHolder holder, int position) {
        holder.author.setText(list.get(position).getuName());
        holder.reply.setText(list.get(position).getContent());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ForumReplyHolder extends RecyclerView.ViewHolder
    {
        TextView author, reply;

        public ForumReplyHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.tv_forumReplyCard_author);
            reply = itemView.findViewById(R.id.tv_forumReplyCard_reply);

        }
    }
}
