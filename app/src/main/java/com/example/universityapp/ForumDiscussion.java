package com.example.universityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ForumDiscussion extends AppCompatActivity {
    RecyclerView rv;
    Button reply;
    TextView author, title, content;
    ArrayList<ForumTopic> list = new ArrayList<>();
    ForumReplyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_discussion);

        author=findViewById(R.id.tv_forumTopicView_uName);
        title = findViewById(R.id.tv_forumTopicView_title);
        content = findViewById(R.id.tv_forumTopicView_content);
        reply = findViewById(R.id.btn_forumTopicView_reply);
        rv = findViewById(R.id.rv_topicForumView_recycler);
        rv.setLayoutManager(new LinearLayoutManager(ForumDiscussion.this));
        ForumTopic passedTopic = getIntent().getParcelableExtra("topic");
        author.setText(passedTopic.getuName());
        title.setText(passedTopic.getTitle());
        content.setText(passedTopic.getContent());
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("_ForumTopic_");

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dss: snapshot.getChildren())
                {
                    ForumTopic topic = dss.getValue(ForumTopic.class);
                    if(topic.getAReply() == true){
                    if(topic.getParentPost().equals(passedTopic.getId()))
                    {
                        list.add(topic);
                    }
                }}
                adapter = new ForumReplyAdapter(list);
                rv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        };
        dbref.addListenerForSingleValueEvent(listener);

        reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ForumDiscussion.this,ForumReplyAdd.class);
                i.putExtra("topic", passedTopic);
                startActivity(i);
            }
        });
    }


}