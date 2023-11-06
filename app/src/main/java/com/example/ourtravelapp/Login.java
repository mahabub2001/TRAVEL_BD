package com.example.ourtravelapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    TextInputLayout username,password;
    Button callsignup,loginbutton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_rp2);

        callsignup=findViewById(R.id.signup_btn);
        loginbutton=findViewById(R.id.login_btn);

        username=findViewById(R.id.login_username);
        password=findViewById(R.id.login_password);


        callsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                isUser();
                loginUser(view);
            }
        });
             
          

    }

    private Boolean validateUsername() {
        String val = username.getEditText().getText().toString();
        if (val.isEmpty()) {
            username.setError("Field cannot be empty");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = password.getEditText().getText().toString();
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }


    public void loginUser(View view) {
        //Validate Login Info
        if (!validateUsername() | !validatePassword()) {
            return;
        } else {
            isUser();
        }
    }

    private void isUser() {
//        Toast.makeText(this, "login successfull", Toast.LENGTH_SHORT).show();
//        progressBar.setVisibility(View.VISIBLE);
        final String userEnteredUsername = username.getEditText().getText().toString().trim();
        final String userEnteredPassword = password.getEditText().getText().toString().trim();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);


        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    username.setError(null);
                    username.setErrorEnabled(false);


                    String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);


                    if (passwordFromDB.equals(userEnteredPassword)) {
                        username.setError(null);
                        username.setErrorEnabled(false);


                        String nameFromDB = dataSnapshot.child(userEnteredUsername).child("name").getValue(String.class);
                        String usernameFromDB = dataSnapshot.child(userEnteredUsername).child("username").getValue(String.class);
                        String phoneNoFromDB = dataSnapshot.child(userEnteredUsername).child("phoneNo").getValue(String.class);
                        String emailFromDB = dataSnapshot.child(userEnteredUsername).child("email").getValue(String.class);


//                        Toast.makeText(Login.this, "Succesfull login", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(getApplicationContext(), UserProfile.class);

                        Intent intent = new Intent(Login.this, MainActivity2.class);
//                        Intent intent1=new Intent(Login.this,User_profile.class);

                        intent.putExtra("name",nameFromDB);
                        intent.putExtra("username",usernameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("phoneNo", phoneNoFromDB);
                        intent.putExtra("password", passwordFromDB);



//                        intent1.putExtra("name", nameFromDB);
//                        intent1.putExtra("username", usernameFromDB);
//                        intent1.putExtra("email", emailFromDB);
//                        intent1.putExtra("phoneNo", phoneNoFromDB);
//                        intent1.putExtra("password", passwordFromDB);


                        startActivity(intent);
                    } else {
//                        progressBar.setVisibility(View.GONE);
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }
                } else {
//                    progressBar.setVisibility(View.GONE);
                    username.setError("No such User exist");
                    username.requestFocus();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
}