package com.example.ourtravelapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {


    TextInputLayout regName, regUsername, regEmail, regPhoneNo, regPassword;
    Button regBtn, regToLoginBtn;
    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        regName = findViewById(R.id.reg_name);
        regUsername = findViewById(R.id.reg_username);
        regEmail = findViewById(R.id.reg_email);
        regPhoneNo = findViewById(R.id.reg_phoneNo);
        regPassword = findViewById(R.id.reg_password);
        regBtn = findViewById(R.id.reg_btn);
        regToLoginBtn = findViewById(R.id.reg_login_btn);
        //Save data in FireBase on button click
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");
                //Get all the values
                String name = regName.getEditText().getText().toString();
                String username = regUsername.getEditText().getText().toString();
                String email = regEmail.getEditText().getText().toString();
                String phoneNo = regPhoneNo.getEditText().getText().toString();
                String password = regPassword.getEditText().getText().toString();
//                UserHelperClass helperClass = new UserHelperClass(name, username, email, phoneNo, password);
//                reference.child(phoneNo).setValue(helperClass);

                UserHelplerClass helplerClass=new UserHelplerClass(name,username,email,phoneNo,password);
//                reference.child(phoneNo).setValue(helplerClass);

                //update ******************
                Toast.makeText(SignUp.this, "inputed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUp.this, Login.class);


                intent.putExtra("name",name);
                intent.putExtra("username",username);
                intent.putExtra("email", email);
                intent.putExtra("phoneNo", phoneNo);
                intent.putExtra("password", password);
                reference.child(username).setValue(helplerClass);
                startActivity(intent);
            }
        });

        regToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignUp.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }


}