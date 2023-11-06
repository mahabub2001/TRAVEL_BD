package com.example.ourtravelapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DistrictPageActivity extends AppCompatActivity {
    private CardView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district_page);

        description = findViewById(R.id.descriptionID);
        //description.setOnClickListener(this);
    }
        public void onClick(View v){

            if (v.getId() == R.id.descriptionID) {
                Intent intent = new Intent(DistrictPageActivity.this, DescriptionActivity.class);
                intent.putExtra("name", "National Museaum");
                startActivity(intent);
            }

        }

}