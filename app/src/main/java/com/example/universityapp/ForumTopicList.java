package com.example.universityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ForumTopicList extends AppCompatActivity implements ForumTopicAdapter.ForumTopicHolder.OnCardClickListener {
    RecyclerView rv;
    ArrayList<ForumTopic> list = new ArrayList<>();
    DatabaseReference dbref;
    ForumTopicAdapter adapter;
    FloatingActionButton addTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_topic_list);

        rv = findViewById(R.id.rv_forumTopicList_recycler);
        rv.setLayoutManager(new LinearLayoutManager(ForumTopicList.this));
        dbref = FirebaseDatabase.getInstance().getReference("_ForumTopic_");
        addTopic = findViewById(R.id.fbtn_forumTopicList_addpost);
        Bundle bundleCategory = getIntent().getExtras();
        String category = bundleCategory.getString("category");

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dss: snapshot.getChildren())
                {
                    ForumTopic topic = dss.getValue(ForumTopic.class);
                    if(!topic.getAReply()&& topic.getCategory().equals(category))
                    {
                        list.add(topic);
                    }
                }
                    adapter = new ForumTopicAdapter(list, ForumTopicList.this);
                    rv.setAdapter(adapter);
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        };
        dbref.addListenerForSingleValueEvent(listener);

        addTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ForumTopicList.this, ForumTopicAdd.class);
                i.putExtra("category", category );
                startActivity(i);
            }
        });
    }


    @Override
    public void onCardClick(int i)
    {
        Intent intent = new Intent(ForumTopicList.this,ForumDiscussion.class );
        intent.putExtra("topic", list.get(i));
        startActivity(intent);

    }


}