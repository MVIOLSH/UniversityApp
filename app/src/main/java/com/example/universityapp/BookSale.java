package com.example.universityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BookSale extends AppCompatActivity {
    Button booksForSale, addBookForSale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_sale);
        booksForSale = findViewById(R.id.btn_saleBook_books);
        addBookForSale = findViewById(R.id.btn_saleBook_addBook);

        booksForSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BookSale.this, SaleBookList.class);
                startActivity(i);
            }
        });

        addBookForSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BookSale.this, SaleBookAdd.class);
                startActivity(i);
            }
        });
    }
}