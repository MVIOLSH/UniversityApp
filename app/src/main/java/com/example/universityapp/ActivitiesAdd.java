package com.example.universityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ActivitiesAdd extends AppCompatActivity {
    DatePicker datePicker;
    EditText time, location, description;
    Button add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_add);

        datePicker = findViewById(R.id.datePicker);
        time = findViewById(R.id.et_activeAdd_time);
        location = findViewById(R.id.et_activAdd_location);
        description = findViewById(R.id.et_activeAdd_descripton);
        add = findViewById(R.id.btn_activAdd_add);
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("_Activities_");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateStr = datePicker.getDayOfMonth() + "/0" + (datePicker.getMonth()+1)+"/" + datePicker.getYear();
                String id = dbref.push().getKey();

                Activity event = new Activity(id, dateStr, location.getText().toString(), time.getText().toString(), description.getText().toString());
                dbref.child(id).setValue(event).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ActivitiesAdd.this, "Event have been added", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(ActivitiesAdd.this, ActivitiesMain.class);
                        startActivity(i);
                    }
                });

            }
        });

    }
}