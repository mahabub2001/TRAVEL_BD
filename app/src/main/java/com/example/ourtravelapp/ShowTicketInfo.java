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

public class ShowTicketInfo extends AppCompatActivity {

    String _name, _username, _email, _phoneNo, _password;
    private RecyclerView recyclerView;
    private TicketAdapter ticketAdapter;
    private List<BusTicketHelperClass> uploadList;
    private ImageView imageMenu;
    DatabaseReference databaseReference;
    private SwipeRefreshLayout swipeRefreshLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ticket_info);


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
                Intent intent8=new Intent(ShowTicketInfo.this, SettingsActivity.class);
                putDataExtra(intent8);
                finish();
            }
        });


//        showfunc();

        databaseReference = FirebaseDatabase.getInstance().getReference("Busticket").child(_username);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {

                        BusTicketHelperClass upload = dataSnapshot1.getValue(BusTicketHelperClass.class);
                        uploadList.add(upload);
                    }

                    ticketAdapter = new TicketAdapter(new Context(), uploadList);
                    recyclerView.setAdapter(ticketAdapter);
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

        uploadList.clear();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    BusTicketHelperClass upload = dataSnapshot1.getValue(BusTicketHelperClass.class);
                    uploadList.add(upload);
                }

                ticketAdapter.notifyDataSetChanged();

                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}