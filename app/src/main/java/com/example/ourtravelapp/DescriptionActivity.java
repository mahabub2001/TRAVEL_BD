package com.example.ourtravelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DescriptionActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        imageView = findViewById(R.id.profileImageId);
        textView = findViewById(R.id.profileTextId);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            String countryName = bundle.getString("name");
            showDetails(countryName);
        }
    }
    void showDetails(String countryName) {
        if (countryName.equals("National Museaum")) {
            imageView.setImageResource(R.drawable.national_museum1);
            textView.setText(R.string.national);
        }
    }
}