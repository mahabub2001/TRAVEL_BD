package com.example.ourtravelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;

public class settingsprofile extends AppCompatActivity {
    TextInputEditText reg_name,reg_username,reg_mail,reg_phone,reg_password;
    String _name, _username, _email, _phoneNo, _password;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingsprofile);


        reg_name=findViewById(R.id.reg_name);
        reg_username=findViewById(R.id.reg_username);
        reg_mail=findViewById(R.id.reg_email);
        reg_phone=findViewById(R.id.reg_phoneNo);
        reg_password=findViewById(R.id.reg_password);


        showAllUserData();

    }

    public void showAllUserData(){
        Intent intent=getIntent();
        _name=intent.getStringExtra("name");
        _username=intent.getStringExtra("username");
        _email=intent.getStringExtra("email");
        _phoneNo=intent.getStringExtra("phoneNo");
        _password=intent.getStringExtra("password");


        reg_name.setText(_name);
        reg_username.setText(_username);
        reg_mail.setText(_email);
        reg_phone.setText(_phoneNo);
        reg_password.setText(_password);


    }
}