package com.example.universityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SaleBookMore extends AppCompatActivity {
    TextView title, author,description;
    Button contactSeller, markAsSold;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_book_more);
        SaleBook book = getIntent().getParcelableExtra("BOOK");
        title = findViewById(R.id.tv_lsaleBook_more_title);
        author = findViewById(R.id.tv_saleBook_more_author);
        description = findViewById(R.id.tv_saleBook_more_description);
        contactSeller = findViewById(R.id.btn_saleBook_more_contactSeller);
        markAsSold = findViewById(R.id.btn_saleBook_more_markAsSold);
        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        description.setText(book.getDescription());



        //Only the owner can delete ad
        if(!book.getuName().equals(AppSession.Session.uName))
        {
            markAsSold.setVisibility(View.INVISIBLE);
        }


        contactSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto",book.getSeller_email(), null));
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
            }
        });

        markAsSold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("_SaleBook_");
                dbref.child(book.getsaleId()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(SaleBookMore.this, "Add have been removed", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(SaleBookMore.this, BookSale.class);
                        startActivity(i);
                    }
                });
            }
        });
    };
}