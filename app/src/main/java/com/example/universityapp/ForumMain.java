package com.example.universityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ForumMain extends AppCompatActivity {
    Button modules, university, studentLife, mixed;
    String categoryUni, categoryMod, categorySLife, categoryMixed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_main);
        modules = findViewById(R.id.btn_forumMain_topic_modules);
        university = findViewById(R.id.btn_forumMain_topic_uni);
        studentLife = findViewById(R.id.btn_forumMain_topic_studentLife);
        mixed = findViewById(R.id.btn_forumMain_topic_misc);
        categoryUni = "university";
        categoryMod = "modules";
        categoryMixed = "mixed";
        categorySLife = "student life";

        modules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ForumMain.this, ForumTopicList.class);
                i.putExtra("category", categoryMod);
                startActivity(i);
            }
        });
        university.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ForumMain.this, ForumTopicList.class);
                i.putExtra("category", categoryUni);
                startActivity(i);
            }
        });
        studentLife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ForumMain.this, ForumTopicList.class);
                i.putExtra("category", categorySLife);
                startActivity(i);
            }
        });
        mixed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ForumMain.this, ForumTopicList.class);
                i.putExtra("category", categoryMixed);
                startActivity(i);
            }
        });
    }
}