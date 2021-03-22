package com.example.universityapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class Library extends AppCompatActivity {
    FloatingActionButton addBook;
    Button library, reservations;


    public Library(){

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        addBook = findViewById(R.id.fbtn_addBook);
        library = findViewById(R.id.btn_library_library);
        reservations = findViewById(R.id.btn_library_reservations);

        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Library.this, LibraryBooksList.class);
                startActivity(i);
            }
        });

        reservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Library.this, LibraryReseravtions.class);
                startActivity(i);
            }
        });








        //Paragraph below checks at the moment if logged user is the one with permission to add books.
        // It is very easily changeable to add enum in user field and inside this enum put few different user types with different permissions.
        //Because it wasn't stated in the requirements, i've just simply create activity to add books for my own comfort.
        //CREDENTIALS FOR THIS USER: EMAIL: szymansm@roehampton.ac.uk, PASSWORD: Lokolo@12, STUDENT ID: szyma123
        if (!AppSession.Session.userSession.getStudentId().equalsIgnoreCase("szyma123"))
        {
            addBook.hide();
        }
        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Library.this, Library_add.class);
                startActivity(i);
            }
        });
        //End of the paragraph for this activity related to adding the books to the library.

    }



}