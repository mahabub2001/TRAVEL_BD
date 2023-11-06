package com.example.ourtravelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{

    private Button my_profile,dark_mode,language,location,report_problem,contact_us,signoutbtn,developers;
    private Intent intent;
    String _name, _username, _email, _phoneNo, _password;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        my_profile=findViewById(R.id.button_myaccount);
        dark_mode=findViewById(R.id.button_dark_mode_ID);
        language=findViewById(R.id.button_language_id);
        location=findViewById(R.id.button_location_id);
        report_problem=findViewById(R.id.button_report_problemID);
        contact_us=findViewById(R.id.button_cu);
        signoutbtn=findViewById(R.id.button_signout_id);
        developers=findViewById(R.id.DevelopersID);

        my_profile.setOnClickListener(this);
        dark_mode.setOnClickListener((View.OnClickListener) this);
        language.setOnClickListener(this);
        location.setOnClickListener(this);
        report_problem.setOnClickListener(this);
        contact_us.setOnClickListener(this);
        signoutbtn.setOnClickListener(this);
        developers.setOnClickListener(this);





    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button_myaccount)
        {
            intent=new Intent(SettingsActivity.this,settingsprofile.class);
//            startActivity(intent);
            putDataExtra(intent);
        }
        if(v.getId()==R.id.button_dark_mode_ID)
        {
            intent=new Intent(SettingsActivity.this,UnderConstructionActivity.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.button_language_id)
        {
            intent=new Intent(SettingsActivity.this,AboutUsActivity.class);
            startActivity(intent);
            //putDataExtra(intent);
        }
        if(v.getId()==R.id.button_location_id)
        {
            intent=new Intent(SettingsActivity.this,ShowTicketInfo.class);
            //startActivity(intent);
            putDataExtra(intent);

        }
        if(v.getId()==R.id.button_report_problemID)
        {
            intent=new Intent(SettingsActivity.this,FeedbackActivity.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.button_cu)
        {
            intent=new Intent(SettingsActivity.this,ContactActivity.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.button_signout_id)
        {
            intent=new Intent(SettingsActivity.this, Login.class);
            startActivity(intent);
            finish();
        }
        if(v.getId()==R.id.DevelopersID)
        {
            intent=new Intent(SettingsActivity.this,DevelopersActivity.class);
            startActivity(intent);
        }
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