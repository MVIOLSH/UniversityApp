package com.example.universityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity
{
    TextView domain;
    EditText email, pw, name, surname, studentId;
    Button register, goBack;
    FirebaseAuth m_auth;
    DatabaseReference dbref;
    String emailComplete;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register = findViewById(R.id.btn_registerProceed);
        goBack = findViewById(R.id.btn_backToLogin);
        email = findViewById(R.id.et_emailReg);
        pw = findViewById(R.id.et_passRegister);
        name = findViewById(R.id.et_nameReg);
        surname = findViewById(R.id.et_surnameReg);
        domain = findViewById(R.id.tv_emailDomain);
        studentId = findViewById(R.id.et_studentIdReg);
        m_auth = FirebaseAuth.getInstance();
        dbref = FirebaseDatabase.getInstance().getReference("_user_");

        register.setOnClickListener(new View.OnClickListener()
                                    {
                                        @Override
                                        public void onClick(View v) {

                                            emailComplete = email.getText().toString() + domain.getText().toString();
                                            FirebaseAuth.getInstance().signOut();

                                            if
                                            (
                                                    !(TextUtils.isEmpty(emailComplete)
                                                            && TextUtils.isEmpty(pw.getText().toString()))
                                            ) {
                                                FirebaseAuth.getInstance().signOut();
                                                m_auth.createUserWithEmailAndPassword(emailComplete, pw.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                                    @Override
                                                    public void onSuccess(AuthResult authResult)
                                                    {
                                                        Toast.makeText(Register.this, "Registration successful, check you email", Toast.LENGTH_LONG).show();
                                                        dbref.child(m_auth.getUid()).setValue(new User(name.getText().toString(), surname.getText().toString(),emailComplete, studentId.getText().toString()));
                                                        //m_auth.getCurrentUser().sendEmailVerification();  Function to authenticate user by email verification. I've turned it off for testing purposes.
                                                        Intent i = new Intent(Register.this, Login.class);
                                                        startActivity(i);
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


