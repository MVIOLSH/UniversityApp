package com.example.universityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class Floorplan extends AppCompatActivity {
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floorplan);
        iv = findViewById(R.id.iv_floorplan);
        Picasso.get().load("gs://universityapp-331b1.appspot.com/images/-MWIxCtMt49hnJNGmRmh.jpg").into(iv);
    }
}