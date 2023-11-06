package com.example.ourtravelapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Context;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    ImageView imageMenu;

    private RecyclerView recyclerView;
    private MyAdapter myAdapter,adapter;

    private List<Upload> uploadList,filteredList;
    DatabaseReference databaseReference;

    private ProgressBar RecyclerProgressBarId;

    SearchView searchView;
    private SwipeRefreshLayout swipeRefreshLayout;

    ValueEventListener eventListener;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        recyclerView=findViewById(R.id.recyclerViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        uploadList=new ArrayList<>();

        searchView =findViewById(R.id.search);
        searchView.clearFocus();
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);


//        adapter=new MyAdapter(new Context(),uploadList);
//        RecyclerProgressBarId=findViewById(R.id.RecyclerprogressBarId);



        myAdapter=new MyAdapter(new Context(),uploadList);
        recyclerView.setAdapter(myAdapter);
        databaseReference= FirebaseDatabase.getInstance().getReference("Resorts");


        eventListener=databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                uploadList.clear();
                for(DataSnapshot itemSnapshot:snapshot.getChildren()){
                    Upload upload=itemSnapshot.getValue(Upload.class);
                    uploadList.add(upload);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchList(s);
                return true;
            }
        });

//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @SuppressLint("RestrictedApi")
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                for(DataSnapshot dataSnapshot1 : snapshot.getChildren()){
//                    Upload upload=dataSnapshot1.getValue(Upload.class);
//                    uploadList.add(upload);
//                }
//
//                myAdapter=new MyAdapter(new Context(),uploadList);
//
//
//                recyclerView.setAdapter(myAdapter);
//
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(MainActivity.this, "Error : "+error.getMessage(), Toast.LENGTH_SHORT).show();
////                RecyclerProgressBarId.setVisibility(View.INVISIBLE);
//            }
//        });









        // Navagation Drawar------------------------------
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_View);
        imageMenu = findViewById(R.id.imageMenu);

        toggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Drawar click event
        // Drawer item Click event ------



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

//                switch (item.getItemId()) {
//                    case R.id.mHome:
//                        Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
//                        drawerLayout.closeDrawers();
//                        break;
//
//                    case R.id.mShare:
//                        Toast.makeText(MainActivity.this, "Facebook", Toast.LENGTH_SHORT).show();
//                        drawerLayout.closeDrawers();
//                        break;
//
//                }
                if(item.getItemId()==R.id.mProfile){



                    Toast.makeText(MainActivity.this, "profile", Toast.LENGTH_SHORT).show();
                    Intent intent1=new Intent(MainActivity.this,User_profile.class);
                    profilefunc(intent1);
//                    startActivity(new Intent(MainActivity.this,User_profile.class));
//                    finish();
                    drawerLayout.closeDrawers();
                    finish();

                }
                if(item.getItemId()==R.id.mHome){
                    Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(MainActivity.this,HomeActivity.class));
//                    finish();
                    drawerLayout.closeDrawers();
                    finish();

                }
                if(item.getItemId()==R.id.mCart){
                    Toast.makeText(MainActivity.this, "Show cart to consumer", Toast.LENGTH_SHORT).show();
                    Intent intent3=new Intent(MainActivity.this,Carts_page.class);
                    profilefunc(intent3);

                    drawerLayout.closeDrawers();
                    finish();
                }
                if(item.getItemId()==R.id.mPending){
                    Toast.makeText(MainActivity.this, "Show pending to owner", Toast.LENGTH_SHORT).show();
                    Intent intent6=new Intent(MainActivity.this,Owners_pending_page.class);
                    profilefunc(intent6);

                    drawerLayout.closeDrawers();
                    finish();
                }
                if(item.getItemId()==R.id.mUploadp){
//                    Toast.makeText(MainActivity.this, "Show pending to owner", Toast.LENGTH_SHORT).show();

//                    profilefunc();
//                    startActivity(new Intent(MainActivity.this,UploadResortActivity.class));
                    Intent intent1=new Intent(MainActivity.this,UploadResortActivity.class);
                    profilefunc(intent1);

                    drawerLayout.closeDrawers();
                    finish();
                }
                if(item.getItemId()==R.id.myResorts){
                    Toast.makeText(MainActivity.this, "Show my resorts to owner", Toast.LENGTH_SHORT).show();
                    Intent intent1=new Intent(MainActivity.this,My_resorts_page.class);
                    profilefunc(intent1);

                    drawerLayout.closeDrawers();
                    finish();
                }
                if(item.getItemId()==R.id.mRate){
                    Toast.makeText(MainActivity.this, "Playstore", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawers();
                }

                return false;
            }
        });
        //------------------------------

        // ------------------------
        // App Bar Click Event



        imageMenu = findViewById(R.id.imageMenu);
        imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code Here
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


        // ------------------------


        // Initialize uploadList and set up your adapter and databaseReference here

        // Set up SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(() -> {
            // This code will be executed when the user pulls to refresh
            // Fetch data from Firebase or perform your refresh operation here
            fetchDataFromFirebase();
        });



    }

    public void profilefunc(Intent intent1){
        Intent intent=getIntent();
        String _name=intent.getStringExtra("name");
        String _username=intent.getStringExtra("username");
        String _email=intent.getStringExtra("email");
        String _phoneNo=intent.getStringExtra("phoneNo");
        String _password=intent.getStringExtra("password");


//        Intent intent1=new Intent(MainActivity.this,User_profile.class);

        intent1.putExtra("name", _name);
        intent1.putExtra("username", _username);
        intent1.putExtra("email", _email);
        intent1.putExtra("phoneNo", _phoneNo);
        intent1.putExtra("password", _password);

        startActivity(intent1);



//        Intent intent=getIntent();
//        String _name=intent.getStringExtra("name");
//        String _username=intent.getStringExtra("username");
//        String _email=intent.getStringExtra("email");
//        String _phoneNo=intent.getStringExtra("phoneNo");
//        String _password=intent.getStringExtra("password");
//
//
////        Intent intent1=new Intent(MainActivity.this,User_profile.class);
//
//                        intent1.putExtra("name", _name);
//                        intent1.putExtra("username", _username);
//                        intent1.putExtra("email", _email);
//                        intent1.putExtra("phoneNo", _phoneNo);
//                        intent1.putExtra("password", _password);
//
//        startActivity(intent1);
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
                myAdapter.notifyDataSetChanged();
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


    public void searchList(String text){
        ArrayList<Upload> searchList=new ArrayList<>();
        for(Upload dataclass:uploadList){
            if(dataclass.getResortname().toLowerCase().contains(text.toLowerCase())){
                searchList.add(dataclass);
            }
            if(dataclass.getResortaddress().toLowerCase().contains(text.toLowerCase())){
                searchList.add(dataclass);
            }
        }

        myAdapter.searchDataList(searchList);
    }


}