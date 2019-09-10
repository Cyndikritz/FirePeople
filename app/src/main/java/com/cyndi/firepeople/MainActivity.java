package com.cyndi.firepeople;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText txtName, txtSurname, txtReason;
    Button btnFire, btnView;
    String fireName, fireSurname, fireReason;
    Person person;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFire=findViewById(R.id.btn_Fired);
        txtName= findViewById(R.id.txt_Name);
        txtSurname= findViewById(R.id.txt_Surname);
        txtReason=findViewById(R.id.txt_Reason);
        btnView=findViewById(R.id.btn_View);
        person = new Person();

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
