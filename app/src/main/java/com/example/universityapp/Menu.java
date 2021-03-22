package com.example.universityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;

public class Menu extends AppCompatActivity {
    Button timetable, floorplan, library, bookSale, forum, activities, moodle;
    TextView welcome;
    String uname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        timetable = findViewById(R.id.btn_menu_timetable);
        floorplan = findViewById(R.id.btn_menu_floormap);
        library = findViewById(R.id.btn_menu_library);
        bookSale = findViewById(R.id.btn_menu_market);
        forum = findViewById(R.id.btn_menu_forum);
        activities = findViewById(R.id.btn_menu_activities);
        moodle = findViewById(R.id.btn_menu_moodle);
        welcome = findViewById(R.id.tv_menu);
        AppSession.Session.uName = AppSession.Session.userSession.getFn() + " " + AppSession.Session.userSession.getSn(); // Saved the Name and Surname as a one String for future reference as a username.
        welcome.setText("Welcome " + AppSession.Session.uName + ". Please choose one of the following options:"); // Personalization of the app Menu.

        timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://ttportalqalive.com/2021/studentlogin.html";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        moodle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://partnerships.moodle.roehampton.ac.uk/login/index.php";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });

        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this, Library.class);
                startActivity(i);
            }
        });
        floorplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this, Floorplan.class);
                startActivity(i);
            }
        });

    }
}