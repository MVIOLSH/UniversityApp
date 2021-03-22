package com.example.universityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LibraryReseravtions extends AppCompatActivity implements ListAdapter.BooksHolder.OnCardClickListener {
    RecyclerView rv;
    ArrayList<LibraryBook> booksres = new ArrayList<>();
    DatabaseReference dbRef;
    ListAdapter adapter3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_reseravtions);

        rv = findViewById(R.id.rv_library_reservations);
        rv.setLayoutManager(new LinearLayoutManager(LibraryReseravtions.this));
        dbRef = FirebaseDatabase.getInstance().getReference("_LibraryBooks_");



        ValueEventListener listener = new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dss: snapshot.getChildren())
                {
                    LibraryBook book = dss.getValue(LibraryBook.class);
                    if(book.isReserved()  && book.getsId().equals(AppSession.Session.userSession.getStudentId()))
                    {
                        booksres.add(book);
                        adapter3 = new ListAdapter(booksres, LibraryReseravtions.this);
                        rv.setAdapter(adapter3);

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        dbRef.addListenerForSingleValueEvent(listener);

    }

    @Override
    public void onCardClick(int i) {
        Intent intent = new Intent(LibraryReseravtions.this, BookMore.class);
        intent.putExtra("BOOK", booksres.get(i));
        startActivity(intent);

    }
}