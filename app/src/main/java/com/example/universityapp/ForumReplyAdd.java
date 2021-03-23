package com.example.universityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ForumReplyAdd extends AppCompatActivity {
    TextView category, replyTo;
    EditText reply;
    Button addReply;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_reply_add);

        category= findViewById(R.id.tv_forumReplyAdd_category);
        replyTo = findViewById(R.id.tv_forumReplyAdd_replyTo);
        reply = findViewById(R.id.et_forumReplyAdd_content);
        addReply = findViewById(R.id.btn_forumReplyAdd_add);
        ForumTopic passedTopic = getIntent().getParcelableExtra("topic");
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("_ForumTopic_");
        category.setText(passedTopic.getCategory());
        replyTo.setText(passedTopic.getTitle());

        addReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = dbref.push().getKey();
                ForumTopic topic = new ForumTopic(id, AppSession.Session.uName, passedTopic.getTitle(), reply.getText().toString(), passedTopic.getId(), passedTopic.getCategory(), true  );
                dbref.child(id).setValue(topic).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ForumReplyAdd.this, "Reply has been added to Forum", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(ForumReplyAdd.this, ForumMain.class);
                        startActivity(i);
                    }
                });
            }
        });
    }
}