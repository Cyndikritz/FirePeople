package com.cyndi.firepeople;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class firedPeople extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("persontofire");

    Person pulledPeople = new Person();
    ListView lv_pulledPeople;
    ArrayList<Person> people;
    ArrayAdapter<Person> adapter;
    static String TAG = "pulling from Firebase";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fired_people);

        people = new ArrayList<Person>();
        //adapter = new ArrayAdapter<Person>();
        //final List<Person> people = new ArrayList<Person>();
        lv_pulledPeople = findViewById(R.id.lstPeople);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) //Every time data is changed
            {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren(); //Gets all records in db
                for(DataSnapshot child:children) //foreach record
                {
                    Log.i(TAG, "Data in children"+child);
                    Person pulledPeople = child.getValue(Person.class);
                    Log.i(TAG, "Data in object"+pulledPeople.toString());
                    people.add(pulledPeople); //adding to array list
                }
                adapter = new ArrayAdapter<Person>(firedPeople.this, android.R.layout.simple_list_item_1, people);
                lv_pulledPeople.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
