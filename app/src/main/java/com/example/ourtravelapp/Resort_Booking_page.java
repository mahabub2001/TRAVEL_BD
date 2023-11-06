package com.example.ourtravelapp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Resort_Booking_page extends AppCompatActivity {

    String resort_name,resort_address,resort_mail,resort_phone,resort_pic_url,resort_owner;

    TextInputEditText resortnameiet,resortaddressiet;
    TextInputEditText reg_inputdate,reg_outputdate,reg_name,reg_email,reg_phone;
    ImageView image_view_resort;
    DatabaseReference reference;
    String acnonac,nop,roomtype,nor;

    Button book_btn,inputdatebtn,outputdatebtn;
    String _name,_username,_email,_phoneNo,_password;
    ImageView imageMenu;

    String[] items =  {"AC","Non AC"};
    String[] items2 =  {"1","2","3","4","5","6"};
    String[] items3 =  {"1","2","3","4","5","6","7","8","9","10"};
    String[] items4 =  {"Single Room - Designed for single occupancy","Double Room - Equipped with a double bed, designed for two occupants",
            "Twin Room - Two separate single beds","Triple Room - Designed to accommodate three people"};
    AutoCompleteTextView auto_complete_txt,auto_complete_txt2,auto_complete_txt3,auto_complete_txt4;
    ArrayAdapter<String> adapterItems,adapterItems2,adapterItems3,adapterItems4;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resort_booking_page);

//        profilefunc();



        resortnameiet=findViewById(R.id.resortnameiet);
        resortaddressiet=findViewById(R.id.resortaddressiet);
        image_view_resort=findViewById(R.id.image_view_resort);
        reference= FirebaseDatabase.getInstance().getReference("users");
        inputdatebtn=findViewById(R.id.inputdatebtn);
        outputdatebtn=findViewById(R.id.outputdatebtn);

        reg_inputdate=findViewById(R.id.reg_inputdate);
        reg_outputdate=findViewById(R.id.reg_outputdate);


        reg_name=findViewById(R.id.reg_name);
        reg_phone=findViewById(R.id.reg_phone);
        reg_email=findViewById(R.id.reg_email);



        inputdatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openDatePicker();
                Toast.makeText(Resort_Booking_page.this, "inputdate", Toast.LENGTH_SHORT).show();

            }
        });
        outputdatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openDatePicker2();
                Toast.makeText(Resort_Booking_page.this, "inputdate22", Toast.LENGTH_SHORT).show();

            }
        });
        showResortData();
        profilefunc();



        auto_complete_txt = findViewById(R.id.auto_complete_txt);
        auto_complete_txt2 = findViewById(R.id.auto_complete_txt2);
        auto_complete_txt3 = findViewById(R.id.auto_complete_txt3);
        auto_complete_txt4=findViewById(R.id.auto_complete_txt4);

        adapterItems = new ArrayAdapter<String>(this,R.layout.choice_layout,items);
        auto_complete_txt.setAdapter(adapterItems);

//        auto_complete_txt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String item = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
//            }
//        });

        adapterItems2 = new ArrayAdapter<String>(this,R.layout.choice_layout,items2);
        auto_complete_txt2.setAdapter(adapterItems2);

//        auto_complete_txt2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String item = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
//            }
//        });

        adapterItems3 = new ArrayAdapter<String>(this,R.layout.choice_layout,items3);
        auto_complete_txt3.setAdapter(adapterItems3);


//        auto_complete_txt3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String item = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
//            }
//        });


        adapterItems4 = new ArrayAdapter<String>(this,R.layout.choice_layout,items4);
        auto_complete_txt4.setAdapter(adapterItems4);

//        imageMenu=findViewById(R.id.imageMenu);
//        imageMenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                startActivity(new Intent(UploadResortActivity.this,MainActivity.class));
////                finish();
//
//                Intent intent7=new Intent(Resort_Booking_page.this, MainActivity.class);
//                putDataExtra(intent7);
//                finish();
//
//            }
//        });
    }



    public void bookfunc(View view){

//        Toast.makeText(this, "click on booked", Toast.LENGTH_SHORT).show();
//        String acnonac,nop,roomtype,nor;

        auto_complete_txt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 acnonac = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
            }
        });

        auto_complete_txt2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 nop = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
            }
        });


        auto_complete_txt3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 nor = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
            }
        });

        auto_complete_txt4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 roomtype = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(),"Item: "+roomtype,Toast.LENGTH_SHORT).show();
            }
        });


        DatabaseReference customer=FirebaseDatabase.getInstance().getReference("Customer_books");
        DatabaseReference owner_orders=FirebaseDatabase.getInstance().getReference("Owner_orders");

        String customerusername1=reg_name.getText().toString();
        String indate=reg_inputdate.getText().toString();
        String outdate =reg_outputdate.getText().toString();
        String phone=reg_phone.getText().toString();
        String email=reg_email.getText().toString();

        String acnac2=auto_complete_txt.getText().toString();
        String nop2=auto_complete_txt2.getText().toString();
        String roomtype2=auto_complete_txt4.getText().toString();
        String nor2=auto_complete_txt3.getText().toString();



        Resort_booking_helper_class uploader=new Resort_booking_helper_class(resort_name,resort_address,customerusername1,acnac2,nop2,roomtype2,nor2,indate,outdate,phone,email,resort_owner);

        String uploadId=customerusername1+resort_name;
        customer.child(customerusername1).child(uploadId).setValue(uploader);

        owner_orders.child(resort_owner).child(resort_owner+resort_name).setValue(uploader);


        Toast.makeText(this, "Booking Done", Toast.LENGTH_SHORT).show();
        
//
//        Toast.makeText(this, roomtype+" "+nop, Toast.LENGTH_SHORT).show();

//        Toast.makeText(this, nop2 + " "+nor2, Toast.LENGTH_SHORT).show();

    }
    public void showResortData(){



        Intent intent=getIntent();

        resort_name=intent.getStringExtra("resortname");
        resort_address=intent.getStringExtra("resortaddress");
        resort_pic_url=intent.getStringExtra("resortpicaddress");
        resort_phone=intent.getStringExtra("resortphone");
        resort_mail=intent.getStringExtra("resortmail");
        resort_owner=intent.getStringExtra("resortowner");






        resortnameiet.setText(resort_name);
        resortaddressiet.setText(resort_address);



        Picasso.get()
                .load(resort_pic_url)
                .fit() // Optional: Resize the image to fit the ImageView while maintaining aspect ratio
                .centerCrop() // Optional: Crop the image from the center if necessary
                .into(image_view_resort);
    }

    private void openDatePicker(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                //Showing the picked value in the textView
                reg_inputdate.setText(String.valueOf(year)+ "."+String.valueOf(month)+ "."+String.valueOf(day));

            }
        }, 2023, 10, 20);

        datePickerDialog.show();
    }
    private void openDatePicker2(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                //Showing the picked value in the textView
                reg_outputdate.setText(String.valueOf(year)+ "."+String.valueOf(month)+ "."+String.valueOf(day));

            }
        }, 2023, 10, 20);

        datePickerDialog.show();
    }

    public void profilefunc() {
        Intent intent = getIntent();
        _name = intent.getStringExtra("name");
        _username = intent.getStringExtra("username");
        _email = intent.getStringExtra("email");
        _phoneNo = intent.getStringExtra("phoneNo");
        _password = intent.getStringExtra("password");

        reg_name.setText(_username);


//        Intent intent1=new Intent(MainActivity.this,User_profile.class);

//        intent1.putExtra("name", _name);
//        intent1.putExtra("username", _username);
//        intent1.putExtra("email", _email);
//        intent1.putExtra("phoneNo", _phoneNo);
//        intent1.putExtra("password", _password);
//
//        startActivity(intent1);
    }


    public void putDataExtra(Intent intent1){
//        Intent intent=getIntent();
//        String _name=intent.getStringExtra("name");
//        String _username=intent.getStringExtra("username");
//        String _email=intent.getStringExtra("email");
//        String _phoneNo=intent.getStringExtra("phoneNo");
//        String _password=intent.getStringExtra("password");

        profilefunc();

        intent1.putExtra("name", _name);
        intent1.putExtra("username", _username);
        intent1.putExtra("email", _email);
        intent1.putExtra("phoneNo", _phoneNo);
        intent1.putExtra("password", _password);

        startActivity(intent1);

    }
}