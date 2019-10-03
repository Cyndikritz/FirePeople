package com.cyndi.firepeople;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    EditText txtName, txtSurname;
    Button btnSave;

    private DatabaseReference DatabaseRef;
    FirebaseAuth fbAuth;
    String fbUser;
    String uploadID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtName = findViewById(R.id.txtName);
        txtSurname = findViewById(R.id.txtSurname);
        btnSave = findViewById(R.id.btnSave);

        DatabaseRef = FirebaseDatabase.getInstance().getReference("users"); //Creating a 'users' table in Firebase
        fbAuth = FirebaseAuth.getInstance(); //Gives you instance of authorisation
        fbUser = fbAuth.getCurrentUser().getEmail(); //Getting the email of the current user

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname, lastname;
                firstname = txtName.getText().toString();
                lastname = txtSurname.getText().toString();

                uploadID = DatabaseRef.push().getKey(); //Gets unique container key to allow for editing later on

                User newUser = new User(firstname,lastname,fbUser,uploadID); //Push the object to the place with the specific container id

                DatabaseRef.child(uploadID).setValue(newUser);

                Intent intent = new Intent(Register.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
