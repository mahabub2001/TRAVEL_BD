package com.example.ourtravelapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User_profile extends AppCompatActivity {
    TextInputEditText reg_name;
    TextInputEditText reg_username, reg_email, reg_phone, reg_password;


    DatabaseReference reference;

    String _name, _username, _email, _phoneNo, _password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        reg_name = findViewById(R.id.reg_name);
        reg_username = findViewById(R.id.reg_username);
        reg_email = findViewById(R.id.reg_email);
        reg_phone = findViewById(R.id.reg_phoneNo);
        reg_password = findViewById(R.id.reg_password);

        reference = FirebaseDatabase.getInstance().getReference("users");

        showAllUserData();

//        update_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(isNameChanged()){
//                    Toast.makeText(User_profile.this, "Data updated", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    Toast.makeText(User_profile.this, "Data is same , cannot be updated", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

//        update_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(isPasswordChanged() || isNameChanged()){
//                    Toast.makeText(User_profile.this, "Data has been updated", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    Toast.makeText(User_profile.this, "Data is same and cannot be updated", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

    }
    private boolean isNameChanged(){
        if(!_name.equals(reg_name.getText().toString())){
            reference.child(_username).child("name").setValue(reg_name.getText().toString());
            _name=reg_name.getText().toString();
            return true;
        }else{
            return false;
        }
    }
    private boolean isPasswordChanged(){
        if(!_password.equals(reg_password.getText().toString())){
            reference.child(_username).child("password").setValue(reg_password.getText().toString());
            _password=reg_name.getText().toString();
            return true;
        }else{
            return false;
        }
    }
    private boolean isEmailChanged(){
        if(!_email.equals(reg_email.getText().toString())){
            reference.child(_username).child("email").setValue(reg_email.getText().toString());
            _email=reg_email.getText().toString();
            return true;
        }else{
            return false;
        }
    }
    private boolean isPhoneChanged(){
        if(!_phoneNo.equals(reg_phone.getText().toString())){
            reference.child(_username).child("phoneNo").setValue(reg_phone.getText().toString());
            _phoneNo=reg_phone.getText().toString();
            return true;
        }else{
            return false;
        }
    }
    private void isUsernameChanged(){
        reg_username.setError("Cannot be edited");
//        if(!_username.equals(reg_username.getText().toString())){
//            reference.child(_username).child("username").setValue(reg_username.getText().toString());
//            _username=reg_username.getText().toString();
//            return true;
//        }else{
//            return false;
//        }
    }



    public  void update(View view){
        isPasswordChanged();
        isNameChanged();
        isUsernameChanged();
        isEmailChanged();
        isPhoneChanged();
        Toast.makeText(this, "Data has been updated", Toast.LENGTH_SHORT).show();
//        if(isNameChanged() || isPasswordChanged()){
//            Toast.makeText(User_profile.this, "Data has been updated", Toast.LENGTH_SHORT).show();
//        }
//        else{
//            Toast.makeText(User_profile.this, "Data is same and cannot be updated", Toast.LENGTH_SHORT).show();
//        }






    }
    public void back(View view){
        Intent intent = new Intent(User_profile.this, MainActivity.class);
        String update_name,update_username,update_email,update_phoneNo,update_password;


        update_name=reg_name.getText().toString();
        update_username=reg_username.getText().toString();
        update_email=reg_email.getText().toString();
        update_phoneNo=reg_phone.getText().toString();
        update_password=reg_password.getText().toString();


        intent.putExtra("name",update_name);
        intent.putExtra("username",update_username);
        intent.putExtra("email", update_email);
        intent.putExtra("phoneNo", update_phoneNo);
        intent.putExtra("password", update_password);

        startActivity(intent);
        finish();
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
        reg_email.setText(_email);
        reg_phone.setText(_phoneNo);
        reg_password.setText(_password);


    }
}

//    public void showAllUserData(){
//        Intent intent=getIntent();
//
//        String usernamefromintent=intent.getStringExtra("username");
//
//        String passwordfromintent=intent.getStringExtra("password");
//
//        Query checkUser = reference.orderByChild("username").equalTo(usernamefromintent);
//
//
//        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//
//                    String passwordFromfb = dataSnapshot.child(usernamefromintent).child("password").getValue(String.class);
//
//
//                    if (passwordFromfb.equals(passwordfromintent)) {
//
//
//
//                    nameFromDB = dataSnapshot.child(usernamefromintent).child("name").getValue(String.class);
//                    usernameFromDB = dataSnapshot.child(usernamefromintent).child("username").getValue(String.class);
//                    phoneNoFromDB = dataSnapshot.child(usernamefromintent).child("phoneNo").getValue(String.class);
//                    emailFromDB = dataSnapshot.child(usernamefromintent).child("email").getValue(String.class);
//                    passwordFromDB = dataSnapshot.child(usernamefromintent).child("password").getValue(String.class);
//
//
//
//
//                        reg_name.setText(nameFromDB);
//                        reg_username.setText(usernameFromDB);
//                        reg_email.setText(emailFromDB);
//                        reg_phone.setText(phoneNoFromDB);
//                        reg_password.setText(passwordFromDB);
//
//
//
//                    }
//                }
//            }
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {}
//        });





//        String _name= reference.child(usernamefromintent).child("name").toString();
//        String _username=String.valueOf(reference.child(usernamefromintent).child("username"));
//        String _email=String.valueOf(reference.child(usernamefromintent).child("email"));
//        String _phone=String.valueOf(reference.child(usernamefromintent).child("phoneNo"));
//        String _password=String.valueOf(reference.child(usernamefromintent).child("password"));
//
//
//
//        reg_name.setText(_name);
//        reg_username.setText(_username);
//        reg_email.setText(_email);
//        reg_phone.setText(_phone);
//        reg_password.setText(_password);






//    private boolean isPasswordChanged(){
//        if(!passwordFromDB.equals(reg_password.getEditableText().toString().trim()))
//        {
//            reference.child(usernameFromDB).child("password").setValue(reg_password.getText().toString());
//            passwordFromDB=reg_password.getText().toString();
//            return true;
//        }else{
//            return false;
//        }
//    }
//    private boolean isNameChanged(){
//        if(!nameFromDB.equals(reg_name.getEditableText().toString())){
//            reference.child(usernameFromDB).child("name").setValue(reg_name.getText().toString());
//            nameFromDB=reg_name.getText().toString();
//            return true;
//        }else{
//            return false;
//        }
//    }

//    public void func_update(View view){
//        if(isPasswordChanged() || isNameChanged()){
//            Toast.makeText(this, "Data has been updated", Toast.LENGTH_SHORT).show();
//        }
//        else{
//            Toast.makeText(this, "Data is same and cannot be updated", Toast.LENGTH_SHORT).show();
//        }
//    }
