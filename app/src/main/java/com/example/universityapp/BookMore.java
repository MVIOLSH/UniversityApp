package com.example.universityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class BookMore extends AppCompatActivity {
    TextView title, author,category, isbn;
    Button reserve, reviews, addReview;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_more);

        title = findViewById(R.id.tv_library_more_title);
        author = findViewById(R.id.tv_library_more_author);
        category = findViewById(R.id.tv_library_more_category);
        iv = findViewById(R.id.iv_library_more_cover);
        reserve = findViewById(R.id.btn_library_more_reserve);
        reviews = findViewById(R.id.btn_library_more_bookReviews);
        addReview =findViewById(R.id.btn_bookMore_addReview);
        isbn = findViewById(R.id.tv_library_more_isbnn);

        LibraryBook book = getIntent().getParcelableExtra("BOOK");
        Picasso.get().load(book.getImgUrl()).fit().into(iv);
        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        category.setText(book.getCategory());
        isbn.setText(book.getIsbn());

        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!book.isReserved())
                {
                    book.setsId(AppSession.Session.userSession.getStudentId());
                    book.setReserved(true);
                    DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("_LibraryBooks_");
                    LibraryBook bookPush = book;
                    dbref.child(bookPush.getBookId()).setValue(bookPush);
                }
                else if(book.isReserved())
                {
                    book.setsId("");
                    book.setReserved(false);
                    DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("_LibraryBooks_");
                    LibraryBook bookPush = book;
                    dbref.child(bookPush.getBookId()).setValue(bookPush);
                }
            }
        });
        reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookMore.this, Reviews.class);
                intent.putExtra("BOOKIsbn", book.getIsbn());
                startActivity(intent);

            }
        });
        addReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookMore.this, ReviewAdd.class);
                intent.putExtra("bkISBN", book.getIsbn());
                startActivity(intent);
            }
        });





    }
}