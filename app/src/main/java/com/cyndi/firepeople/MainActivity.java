package com.cyndi.firepeople;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText txtName, txtSurname, txtReason;
    TextView lblName, lblSurname;
    Button btnFire, btnView;
    String fireName, fireSurname, fireReason, currentUser, currentName, currentSurname;
    Person person;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth mAuth;
    DatabaseReference Databaseref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFire=findViewById(R.id.btn_Fired);
        txtName= findViewById(R.id.txt_Name);
        txtSurname= findViewById(R.id.txt_Surname);
        txtReason=findViewById(R.id.txt_Reason);
        btnView=findViewById(R.id.btn_View);
        lblName = findViewById(R.id.lblName);
        lblSurname = findViewById(R.id.lblSurname);
        person = new Person();

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser().getEmail();

        Databaseref = FirebaseDatabase.getInstance().getReference("users");
        Databaseref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> children = dataSnapshot.getChildren(); //Gets all records in db
                for(DataSnapshot child:children) //foreach record
                {
                    User pulledUser = child.getValue(User.class);
                    if(pulledUser.getPublisher().equals(currentUser))
                    {
                        currentName = pulledUser.getFirstName();
                        currentSurname = pulledUser.getSurname();
                        lblName.setText(currentName);
                        lblSurname.setText(currentSurname);
                        Toast.makeText(MainActivity.this,""+currentName+" "+currentSurname,Toast.LENGTH_LONG);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fireName = txtName.getText().toString();
                fireSurname = txtSurname.getText().toString();
                fireReason = txtReason.getText().toString();
                person.firstName = fireName;
                person.surname = fireSurname;
                person.fireReason = fireReason;
                //OR
                /*person.setFirstName(fireName);
                person.setSurname(fireSurname);
                person.setFireReason(fireReason);
                */
                database = FirebaseDatabase.getInstance();
                myRef = database.getReference("persontofire");

                myRef.push().setValue(person);

            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, firedPeople.class);
                startActivity(i);
            }
        });

    }


}
