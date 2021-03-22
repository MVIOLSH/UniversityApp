package com.example.universityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReviewAdd extends AppCompatActivity {
    TextView uName,  isbn, date;
    EditText title, review;
    Button add;
    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_add);
        uName = findViewById(R.id.tv_reviewAdd_uName);
        title = findViewById(R.id.et_reviewAdd_Title);
        review = findViewById(R.id.et_reviewAdd_reviewText);
        isbn = findViewById(R.id.tv_reviewAdd_isbn);
        date = findViewById(R.id.tv_ReviewAdd_Date);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        String dateTime = simpleDateFormat.format(cal.getTime());
        Bundle bundle = getIntent().getExtras();
        isbn.setText(bundle.getString("bkISBN"));
        uName.setText(AppSession.Session.uName);
        date.setText(dateTime);
        add = findViewById(R.id.btn_reviewAdd_add);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbref = FirebaseDatabase.getInstance().getReference("_Reviews");
                String id = dbref.push().getKey();
                String revText = review.getText().toString();
                BookReview review = new BookReview(uName.getText().toString(), isbn.getText().toString(), revText, date.getText().toString(), title.getText().toString());
                dbref.child(id).setValue(review).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ReviewAdd.this, "Review Added successfully.", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(ReviewAdd.this, Library.class);
                        startActivity(i);
                    }
                });


            }
        });

    }
}