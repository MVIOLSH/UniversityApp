package com.example.universityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    EditText em, pw, sId;
    Button login, goToRegister, goBack;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        login = findViewById(R.id.btn_logIn);
        goToRegister = findViewById(R.id.btn_goToRegister);
        goBack = findViewById(R.id.btn_gobackToMain);
        em = findViewById(R.id.et_emailLogin);
        pw = findViewById(R.id.et_passLogin);
        sId = findViewById(R.id.et_studentIdLogin);
        mAuth = FirebaseAuth.getInstance();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.signInWithEmailAndPassword(em.getText().toString(), pw.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                     //   if (mAuth.getCurrentUser().isEmailVerified() == false) {  Method to verify that user is verified or not. Turned off for test purposes.
                         //   Toast.makeText(Login.this, "Please verify your email before signing in", Toast.LENGTH_LONG).show();
                      //  } else {
                            AppSession.Session.authUser = mAuth.getCurrentUser();
                            Query dbref = FirebaseDatabase.getInstance().getReference("_user_")
                                    .orderByKey()
                                    .equalTo(mAuth.getCurrentUser().getUid()); //Query to DB comparing unique UID and key  which have to be unique and the same for the user.

                            dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for (DataSnapshot dss : snapshot.getChildren())
                                        AppSession.Session.userSession = dss.getValue(User.class);

                                    if (AppSession.Session.userSession.getStudentId().equals(sId.getText().toString())) { //Check if student ID for the user from DB is equal to the one in text edit field.
                                        Intent i = new Intent(Login.this, Menu.class); //if its true, let user to get to menu.
                                        startActivity(i);
                                    } else {
                                        Toast.makeText(Login.this, "Student ID doesn't match. Please check and try again.", Toast.LENGTH_LONG).show(); //If it's not, show proper message.
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(Login.this, error.toString(), Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                   // } Method to verify that user is verified or not. Turned off for test purposes.
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Login.this, "Login failed, wrong email or password. Please try again", Toast.LENGTH_LONG).show(); // message for wrong email or password.
                    }
                });
            }
        });

        goToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}