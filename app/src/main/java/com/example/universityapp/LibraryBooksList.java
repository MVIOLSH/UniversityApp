package com.example.universityapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class LibraryBooksList extends AppCompatActivity implements ListAdapter.BooksHolder.OnCardClickListener {
    RecyclerView rv;
    ArrayList<LibraryBook> books = new ArrayList<>();
    ArrayList<LibraryBook> booksFiltered = new ArrayList<>();
    DatabaseReference dbRef;
    ListAdapter adapter;
    Spinner category;
    Button categoryFilter;
    ListAdapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_books_list);

        rv = findViewById(R.id.rv_library_books_list);
        rv.setLayoutManager(new LinearLayoutManager(LibraryBooksList.this));
        categoryFilter = findViewById(R.id.btn_library_list_categotyFilter);
        category = findViewById(R.id.sp_liobrary_list_categorySelector);
        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(LibraryBooksList.this, R.array.library_book_categories, R.layout.support_simple_spinner_dropdown_item);
        category.setAdapter(adapterSpinner);

        dbRef = FirebaseDatabase.getInstance().getReference("_LibraryBooks_");


        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dss : snapshot.getChildren()) {
                    LibraryBook book = dss.getValue(LibraryBook.class);
                    if (!book.isReserved()) {
                        books.add(book);
                    }

                }
                adapter = new ListAdapter(books, LibraryBooksList.this);
                rv.setAdapter(adapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        };
        categoryFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String categoryString = category.getSelectedItem().toString();
                booksFiltered.clear();
                for(LibraryBook book : books)
                {
                    if(!book.isReserved() && book.getCategory().equals(categoryString))
                    {

                        booksFiltered.add(book);
                    }
                }
                adapter2 = new ListAdapter(booksFiltered, LibraryBooksList.this);
                rv.setAdapter(adapter2);


            }
        });

        dbRef.addListenerForSingleValueEvent(listener);


    }


    @Override
    public void onCardClick(int i)
    {

        Intent intent = new Intent(LibraryBooksList.this, BookMore.class);
        intent.putExtra("BOOK", books.get(i));
        startActivity(intent);

    }
}