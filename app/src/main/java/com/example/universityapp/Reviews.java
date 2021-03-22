package com.example.universityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class Reviews extends AppCompatActivity {
    TextView uName, title, review;
    Date date;
    String isbn;
    FloatingActionButton addReview;
    RecyclerView rv;
    ArrayList<BookReview> list = new ArrayList<>();
    DatabaseReference dbRef;
    RevAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        rv = findViewById(R.id.rv_reviews);
        rv.setLayoutManager(new LinearLayoutManager(Reviews.this));
        dbRef = FirebaseDatabase.getInstance().getReference("_Reviews");
        Bundle bookExtras = getIntent().getExtras();
        String bookIsbn = bookExtras.getString("BOOKIsbn");

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dss: snapshot.getChildren())
                {

                    BookReview bookReview = dss.getValue(BookReview.class);
                    if (bookReview.getISBN().equals(bookIsbn))
                    {
                        list.add(bookReview);
                    }
                }
                adapter = new RevAdapter(list);
                rv.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        dbRef.addListenerForSingleValueEvent(listener);
    }

}