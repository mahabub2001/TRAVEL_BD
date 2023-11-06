package com.example.ourtravelapp;

import static com.example.ourtravelapp.R.id.recyclerViewId;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Context;

import java.util.ArrayList;
import java.util.List;

public class My_resorts_page extends AppCompatActivity {
    String _name, _username, _email, _phoneNo, _password;
    private RecyclerView recyclerView;
    private Myresorts_adapter myresorts_adapter;
    private List<Upload> uploadList;
    private ImageView imageMenu;
    private SwipeRefreshLayout swipeRefreshLayout;
    DatabaseReference databaseReference;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_resorts_page);


        recyclerView=findViewById(R.id.recyclerViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        uploadList=new ArrayList<>();
        imageMenu=findViewById(R.id.imageMenu);




        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        // Initialize uploadList and set up your adapter and databaseReference here

        // Set up SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(() -> {
            // This code will be executed when the user pulls to refresh
            // Fetch data from Firebase or perform your refresh operation here
            fetchDataFromFirebase();
        });
//        showAllUserData();

//        Intent intent=getIntent();
//        _name=intent.getStringExtra("name");
//        _username=intent.getStringExtra("username");
//        _email=intent.getStringExtra("email");
//        _phoneNo=intent.getStringExtra("phoneNo");
//        _password=intent.getStringExtra("password");



//        showfunc();


        profilefunc();

        imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent8=new Intent(My_resorts_page.this, MainActivity.class);
                putDataExtra(intent8);
                finish();
            }
        });

         databaseReference= FirebaseDatabase.getInstance().getReference("Owner_resorts").child(_username);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot dataSnapshot1 : snapshot.getChildren()){
//                        Upload upload=dataSnapshot1.child(_username).getValue(Upload.class);
                        Upload upload=dataSnapshot1.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    myresorts_adapter=new Myresorts_adapter(new Context(),uploadList);
                    recyclerView.setAdapter(myresorts_adapter);
                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(MainActivity.this, "Error : "+error.getMessage(), Toast.LENGTH_SHORT).show();
//                RecyclerProgressBarId.setVisibility(View.INVISIBLE);
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


    private void fetchDataFromFirebase() {
        // Fetch data from Firebase or perform your refresh operation here
        // Once data is fetched or the refresh operation is done, stop the refreshing animation
        // For example, if you are fetching data from Firebase, update the adapter and stop refreshing like this:

        // Clear the existing data and fetch new data from Firebase
        uploadList.clear();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    Upload upload = dataSnapshot1.getValue(Upload.class);
                    uploadList.add(upload);
                }
                // Notify the adapter that the data set has changed
                myresorts_adapter.notifyDataSetChanged();
                // Stop the refreshing animation
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error if necessary
                // Stop the refreshing animation
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    // Rest of your code...
}







//    public void showAllUserData(){
//        Intent intent=getIntent();
//        _name=intent.getStringExtra("name");
//        _username=intent.getStringExtra("username");
//        _email=intent.getStringExtra("email");
//        _phoneNo=intent.getStringExtra("phoneNo");
//        _password=intent.getStringExtra("password");
//

//    }


//    private void showfunc() {
//
//        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Owner_resorts");
//
//        databaseReference.child(_username).addValueEventListener(new ValueEventListener() {
//            @SuppressLint("RestrictedApi")
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists()){
//                    for(DataSnapshot dataSnapshot1 : snapshot.getChildren()){
//                        Upload upload=dataSnapshot1.child(_username).getValue(Upload.class);
//                        uploadList.add(upload);
//                    }
//
//                    myresorts_adapter=new Myresorts_adapter(new Context(),uploadList);
//                    recyclerView.setAdapter(myresorts_adapter);
//                }
//
//
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
////                Toast.makeText(MainActivity.this, "Error : "+error.getMessage(), Toast.LENGTH_SHORT).show();
////                RecyclerProgressBarId.setVisibility(View.INVISIBLE);
//            }
//        });

//    }












//    <?xml version="1.0" encoding="utf-8"?>
//<androidx.cardview.widget.CardView xmlns:android="http://schemas.android.com/apk/res/android"
//        android:layout_width="match_parent"
//        android:layout_height="wrap_content"
//        xmlns:app="http://schemas.android.com/apk/res-auto"
//        android:layout_margin="16dp"
//        android:background="#F5F5F5"
//        android:elevation="8dp">
//
//<LinearLayout
//        android:layout_width="match_parent"
//                android:layout_height="match_parent"
//                android:orientation="vertical">
//
//<ImageView
//            android:id="@+id/cardImageViewId"
//                    android:layout_width="match_parent"
//                    android:layout_height="200dp"
//                    android:scaleType="centerCrop" />
//
//
//<LinearLayout
//            android:layout_width="match_parent"
//                    android:layout_height="wrap_content"
//                    android:background="#D8D8E6"
//                    android:orientation="vertical"
//                    android:padding="16dp">
//
//<TextView
//                android:id="@+id/cardResortViewId"
//                        android:layout_width="match_parent"
//                        android:layout_height="wrap_content"
//                        android:fontFamily="sans-serif-medium"
//                        android:gravity="start"
//                        android:text="Resort Name"
//                        android:textColor="#000000"
//                        android:textSize="20sp" />
//
//<TextView
//                android:id="@+id/cardAddressViewId"
//                        android:layout_width="match_parent"
//                        android:layout_height="wrap_content"
//                        android:layout_marginTop="8dp"
//                        android:fontFamily="sans-serif"
//                        android:gravity="start"
//                        android:text="Resort Address"
//                        android:textColor="#666666"></TextView>
//
//</LinearLayout>
//
//</LinearLayout>
//</androidx.cardview.widget.CardView>
