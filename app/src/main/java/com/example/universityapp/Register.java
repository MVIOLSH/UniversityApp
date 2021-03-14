package com.example.universityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity
{
    TextView domain;
    EditText email, pw;
    Button register, goBack;
    FirebaseAuth auth;
    DatabaseReference dbref;
    String emailComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register = findViewById(R.id.btn_registerProceed);
        goBack = findViewById(R.id.btn_backToLogin);
        email = findViewById(R.id.et_emailReg);
        pw = findViewById(R.id.et_passRegister);
        domain = findViewById(R.id.tv_emailDomain);
        auth = FirebaseAuth.getInstance();
        dbref = FirebaseDatabase.getInstance().getReference("_user_");

        register.setOnClickListener(new View.OnClickListener()
                                    {
                                        @Override
                                        public void onClick(View v) {
                                            emailComplete = email.getText().toString() + domain.getText().toString();

                                            if
                                            (
                                                    !(TextUtils.isEmpty(emailComplete)
                                                            && TextUtils.isEmpty(pw.getText().toString()))
                                            ) {

                                                auth.createUserWithEmailAndPassword(emailComplete, pw.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                                        Toast.makeText(Register.this, task.toString(), Toast.LENGTH_LONG).show();
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(Register.this, e.toString(), Toast.LENGTH_LONG).show();
                                                    }

                                                });
                                            }
                                        }
                                        });

                    goBack.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            Intent i = new Intent(Register.this, Login.class);
                            startActivity(i);
                        }
                    });
    }
}


