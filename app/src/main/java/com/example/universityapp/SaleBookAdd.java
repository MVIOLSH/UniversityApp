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

public class SaleBookAdd extends AppCompatActivity {
    TextView uname;
    EditText title, description, author, price;
    Button add;
    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_book_add);

        uname = findViewById(R.id.tv_saleBookAdd_uName);
        title = findViewById(R.id.et_saleBookAdd_title);
        author=findViewById(R.id.et_saleBookAdd_author);
        description = findViewById(R.id.et_saleBookAdd_description);
        add = findViewById(R.id.btn_saleBookAdd_Add);
        dbref = FirebaseDatabase.getInstance().getReference("_SaleBook_");
        uname.setText(AppSession.Session.uName);
        price = findViewById(R.id.et_saleBookAdd_price);
        String email = AppSession.Session.userSession.getEm();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = dbref.push().getKey();
                SaleBook book = new SaleBook(title.getText().toString(), author.getText().toString(),description.getText().toString(), uname.getText().toString(),email, price.getText().toString(), id );

                dbref.child(id).setValue(book).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(SaleBookAdd.this, "Ad Added successfully.", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(SaleBookAdd.this, BookSale.class);
                        startActivity(i);
                    }
                });


            }
        });

    }
}