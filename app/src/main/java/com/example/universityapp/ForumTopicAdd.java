package com.example.universityapp;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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

public class ForumTopicAdd extends AppCompatActivity {
    TextView category;
    EditText content, title;
    Button add;
    String id, uName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_topic_add);

        category = findViewById(R.id.et_forumTopicAdd_category);
        content = findViewById(R.id.et_forumTopicAdd_content);
        title = findViewById(R.id.et_forumTopicAdd_title);
        add = findViewById(R.id.btn_forumTopicAdd_add);
        Bundle bundleCategory = getIntent().getExtras();
        String stringCategory = bundleCategory.getString("category");
        category.setText(stringCategory);
        uName = AppSession.Session.uName;
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("_ForumTopic_");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = dbRef.push().getKey();
                ForumTopic topic = new ForumTopic(id, uName, title.getText().toString(), content.getText().toString(), "none", stringCategory, false );
                dbRef.child(id).setValue(topic).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ForumTopicAdd.this, "Topic has been added to Forum", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(ForumTopicAdd.this, ForumMain.class);
                        startActivity(i);

                    }
                });
            }
        });







    }
}