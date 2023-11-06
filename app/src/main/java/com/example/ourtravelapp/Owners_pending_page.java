package com.example.ourtravelapp;

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

public class Owners_pending_page extends AppCompatActivity {

    String _name, _username, _email, _phoneNo, _password;
    private RecyclerView recyclerView;
    private Pending_adapter pending_adapter;
    private List<Resort_booking_helper_class> uploadList;
    private ImageView imageMenu;
    DatabaseReference databaseReference;
    private SwipeRefreshLayout swipeRefreshLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owners_pending_page);


        recyclerView = findViewById(R.id.recyclerViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        uploadList = new ArrayList<>();
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

//        Intent intent = getIntent();
//        _name = intent.getStringExtra("name");
//        _username = intent.getStringExtra("username");
//        _email = intent.getStringExtra("email");
//        _phoneNo = intent.getStringExtra("phoneNo");
//        _password = intent.getStringExtra("password");

        profilefunc();

        imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent8=new Intent(Owners_pending_page.this, MainActivity.class);
                putDataExtra(intent8);
                finish();
            }
        });


//        showfunc();

         databaseReference = FirebaseDatabase.getInstance().getReference("Owner_orders").child(_username);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
//                        Upload upload=dataSnapshot1.child(_username).getValue(Upload.class);
                        Resort_booking_helper_class upload = dataSnapshot1.getValue(Resort_booking_helper_class.class);
                        uploadList.add(upload);
                    }

                    pending_adapter = new Pending_adapter(new Context(), uploadList);
                    recyclerView.setAdapter(pending_adapter);
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
                    Resort_booking_helper_class upload = dataSnapshot1.getValue(Resort_booking_helper_class.class);
                    uploadList.add(upload);
                }
                // Notify the adapter that the data set has changed
                pending_adapter.notifyDataSetChanged();
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
}