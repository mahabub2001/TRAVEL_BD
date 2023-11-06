package com.example.ourtravelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BusTicketActivity extends AppCompatActivity {
    
    EditText editText_name,editText_nid,spinnerFrom,spinnerto,spinnerticketnumber;
    String name_,nid_,from_,to_,ticketnumber_;
    Button button2;
    
    DatabaseReference reference;

    String _name,_username,_email,_phoneNo,_password;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_ticket);
        
        editText_name=findViewById(R.id.editText_name);
        editText_nid=findViewById(R.id.editText_nid);
        spinnerFrom=findViewById(R.id.spinnerFrom);
        spinnerto=findViewById(R.id.spinnerto);
        spinnerticketnumber=findViewById(R.id.spinnerticketnumber);
        button2=findViewById(R.id.button2);
        

        profilefunc();
        
        
        
        reference= FirebaseDatabase.getInstance().getReference("Busticket");
        
        
       
        
        
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                name_=editText_name.getText().toString();
                nid_=editText_nid.getText().toString();
                from_=spinnerFrom.getText().toString();
                to_=spinnerto.getText().toString();
                ticketnumber_=spinnerticketnumber.getText().toString();


                BusTicketHelperClass upload=new BusTicketHelperClass(name_,nid_,from_,to_,ticketnumber_);

                String uploadId=_username;


                reference.child(uploadId).child(uploadId).setValue(upload);
                Toast.makeText(BusTicketActivity.this, "Done", Toast.LENGTH_SHORT).show();
            }
        });
        
        
        
        
        
    }


    public void profilefunc() {
        Intent intent = getIntent();
        _name = intent.getStringExtra("name");
        _username = intent.getStringExtra("username");
        _email = intent.getStringExtra("email");
        _phoneNo = intent.getStringExtra("phoneNo");
        _password = intent.getStringExtra("password");


    }


    public void putDataExtra(Intent intent1){


        profilefunc();

        intent1.putExtra("name", _name);
        intent1.putExtra("username", _username);
        intent1.putExtra("email", _email);
        intent1.putExtra("phoneNo", _phoneNo);
        intent1.putExtra("password", _password);

        startActivity(intent1);

    }
}