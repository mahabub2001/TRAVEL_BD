package com.example.ourtravelapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class DestinationActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    public DestinationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        Bundle bundle=getIntent().getExtras();
        String districtName = bundle.getString("selectedItem1");

        if(bundle!=null)
        {
            if(districtName.equals("ঢাকা জেলা"))
            {
                dhakaDistrictDestination();
            }
            else if(districtName.equals("চট্টগ্রাম জেলা"))
            {
                chittagongDistrictDestination();
            }
            else if(districtName.equals("সিলেট জেলা"))
            {
                shyletDistrictDestination();
            }
            else if(districtName.equals("বরিশাল জেলা"))
            {
                barishalDistrictDestination();
            }
            else if(districtName.equals("খুলনা জেলা"))
            {
                khulnaDistrictDestination();
            }
            else if(districtName.equals("রাজশাহী জেলা"))
            {
                rajshahiDistrictDestination();
            }
            else if(districtName.equals("রংপুর জেলা"))
            {
                rangpurDistrictDestination();
            }
            else if(districtName.equals("ময়মনসিংহ জেলা"))
            {
                mymenshsighDistrictDestination();
            }
        }

    }

    private void barishalDistrictDestination() {
    }

    private void shyletDistrictDestination() {

    }

    private void khulnaDistrictDestination() {

    }

    private void rajshahiDistrictDestination() {

    }

    private void rangpurDistrictDestination() {

    }

    private void mymenshsighDistrictDestination() {

    }

    private void chittagongDistrictDestination() {

    }

    private void dhakaDistrictDestination() {
        recyclerView = findViewById(R.id.recyclerviewId);
        String[] destinationName;
        destinationName = getResources().getStringArray(R.array.dhaka_district_destination_name);
        int[] image = {R.drawable.national_museum1, R.drawable.ahsan_manzil};

        adapter = new DestinationAdapter(DestinationActivity.this, destinationName, image);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}