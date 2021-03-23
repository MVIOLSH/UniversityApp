package com.example.universityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SaleBookList extends AppCompatActivity implements SaleBookAdapter.SaleBookHolder.OnCardClickListener{
    RecyclerView rv;
    ArrayList<SaleBook> books = new ArrayList<>();
    DatabaseReference dbref;
    SaleBookAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_book_list);

        rv = findViewById(R.id.rv_saleBook_recycler);
        rv.setLayoutManager(new LinearLayoutManager(SaleBookList.this));
        dbref = FirebaseDatabase.getInstance().getReference("_SaleBook_");


        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dss: snapshot.getChildren())
                {
                    SaleBook book = dss.getValue(SaleBook.class);
                    books.add(book);
                }
                adapter = new SaleBookAdapter(books, SaleBookList.this);
                rv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        };
        dbref.addListenerForSingleValueEvent(listener);


    }


    @Override
    public void onCardClick(int i) {
        Intent intent = new Intent(SaleBookList.this, SaleBookMore.class);
        intent.putExtra("BOOK", books.get(i));
        startActivity(intent);

    }
}