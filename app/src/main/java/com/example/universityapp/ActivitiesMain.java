package com.example.universityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ActivitiesMain extends AppCompatActivity {
    Spinner picker;
    TextView pickText, pickedDate;
    Button search;
    FloatingActionButton add;
    RecyclerView rv;
    ArrayList<Activity> events = new ArrayList<>();
    ActivityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_main);
        picker = findViewById(R.id.spn_activ_pickADay);
        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(ActivitiesMain.this, R.array.daysweek, R.layout.support_simple_spinner_dropdown_item);
        picker.setAdapter(adapterSpinner);
        pickedDate = findViewById(R.id.tv_activ_pickedDate);
        search = findViewById(R.id.btn_activ_filtetr);
        add = findViewById(R.id.fbtn_activ_add);
        rv = findViewById(R.id.rv_activ_recycler);
        rv.setLayoutManager(new LinearLayoutManager(ActivitiesMain.this));
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("_Activities_");
        //CALENDAR DECLARATION TO GET FIRST DAY OF CURRENT WEEK
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        String[] days = new String[7];
        for (int i = 0; i < 7; i++) {
            days[i] = format.format(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            pickedDate.setText(days[0]);


            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String dayOfTheWeek = picker.getSelectedItem().toString();
                    if (dayOfTheWeek.equals("Tuesday"))
                    {
                      pickedDate.setText(days[1]);
                    }
                    else if(dayOfTheWeek.equals("Wednesday"))
                    {
                        pickedDate.setText(days[2]);
                    }
                    else if(dayOfTheWeek.equals("Thursday"))
                    {
                        pickedDate.setText(days[3]);
                    }
                    else if(dayOfTheWeek.equals("Friday"))
                    {
                        pickedDate.setText(days[4]);
                    }
                    else if(dayOfTheWeek.equals("Saturday"))
                    {
                        pickedDate.setText(days[5]);
                    }
                    else if(dayOfTheWeek.equals("Sunday"))
                    {
                        pickedDate.setText(days[6]);
                    }else if(dayOfTheWeek.equals("Monday"))
                    {
                        pickedDate.setText(days[0]);
                    }

                    ValueEventListener listener = new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            events.clear();
                            for(DataSnapshot dss: snapshot.getChildren())
                            {
                                Activity oneEvent = dss.getValue(Activity.class);
                                if(oneEvent.getDate().equals(pickedDate.getText().toString()))
                            {
                                events.add(oneEvent);
                            }
                                adapter = new ActivityAdapter(events);
                                rv.setAdapter(adapter);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    };
                    dbref.addListenerForSingleValueEvent(listener);
                }
            });
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ActivitiesMain.this, ActivitiesAdd.class);
                    startActivity(i);
                }
            });


        }


    }
}