package com.example.ourtravelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity2 extends AppCompatActivity {

    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        nav=findViewById(R.id.navigation);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId()==R.id.home)
                {
                    Toast.makeText(MainActivity2.this,"Home clicked",Toast.LENGTH_SHORT).show();
                }
                if(item.getItemId()==R.id.destination)
                {
                    Intent intent = new Intent(MainActivity2.this,DivisionActivity.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity2.this,"Destination clicked",Toast.LENGTH_SHORT).show();
                }
                if(item.getItemId()==R.id.transport)
                {
                    Toast.makeText(MainActivity2.this,"Transport clicked",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity2.this,BusTicketActivity.class);
//                    startActivity(intent);

                    profilefunc(intent);
                }
                if(item.getItemId()==R.id.hotel)
                {
                    Toast.makeText(MainActivity2.this,"Hotel clicked",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity2.this,MainActivity.class);
//                    startActivity(intent);

                    profilefunc(intent);
                }
                if(item.getItemId()==R.id.settings)
                {
                    Toast.makeText(MainActivity2.this,"Settings clicked",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity2.this,SettingsActivity.class);
//                   startActivity(intent);

                    profilefunc(intent);
                }



                return true;
            }
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



    }
}