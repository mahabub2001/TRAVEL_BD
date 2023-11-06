package com.example.ourtravelapp;

import static com.example.ourtravelapp.R.id.cart;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class CottageBDActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView  navigationView;
    ActionBarDrawerToggle drawerToggle;


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cottage_bdactivity);

        Toast.makeText(CottageBDActivity.this,"ok cotteg",Toast.LENGTH_SHORT).show();

        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                if(item.getItemId()==R.id.home){
                    Toast.makeText(CottageBDActivity.this,"Home Selected",Toast.LENGTH_SHORT).show();

                }
                else if(item.getItemId()==R.id.cart){
                    Toast.makeText(CottageBDActivity.this,"cart Selected",Toast.LENGTH_SHORT).show();
                }
                else if(item.getItemId()==R.id.pending){
                    Toast.makeText(CottageBDActivity.this,"pending Selected",Toast.LENGTH_SHORT).show();
                }
                else if(item.getItemId()==R.id.bookedlist){
                    Toast.makeText(CottageBDActivity.this,"bookedlist Selected",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(CottageBDActivity.this,"logout Selected",Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });
    }


    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }
}