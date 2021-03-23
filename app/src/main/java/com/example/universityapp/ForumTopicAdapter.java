package com.example.universityapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

public class ForumTopicAdapter  extends RecyclerView.Adapter<ForumTopicAdapter.ForumTopicHolder>
{
    ArrayList<ForumTopic> topics;
    ForumTopicHolder.OnCardClickListener listener;

    public ForumTopicAdapter(ArrayList<ForumTopic> topics, ForumTopicHolder.OnCardClickListener _listener) {
        this.topics = topics;
        this.listener = _listener;
    }

    @NonNull
    @Override
    public ForumTopicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.forumtopiccard, parent, false);
        ForumTopicHolder holder = new ForumTopicHolder(v, listener);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ForumTopicHolder holder, int position)
    {
        holder.author.setText(topics.get(position).getuName());
        holder.content.setText(topics.get(position).getContent());
        holder.title.setText(topics.get(position).getTitle());


    }
    @Override
    public int getItemCount() { return topics.size(); }


    public static class ForumTopicHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView author, title, content;
        Button goToDiscussion;
        OnCardClickListener listener;

        public ForumTopicHolder(@NonNull View itemView, OnCardClickListener _listener) {
            super(itemView);
            author = itemView.findViewById(R.id.tv_forumTopicCard_uName);
            title = itemView.findViewById(R.id.tv_forumTopicCard_title);
            content = itemView.findViewById(R.id.tv_forumTopicCard_content);
            goToDiscussion = itemView.findViewById(R.id.btn_forumTopicCard_goToDiscussion);
            this.listener = _listener;
            goToDiscussion.setOnClickListener(this);
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
