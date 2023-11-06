package com.example.ourtravelapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class DivisionActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<DivisionItem> filteredList;
    private ArrayList<DivisionItem> itemList;
    private DivisionAdapter adapter;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_division);

        recyclerView = findViewById(R.id.recyclerviewId);

        itemList = new ArrayList<>();
        itemList.add(new DivisionItem(R.drawable.dhakadivission, "Dhaka Division"));
        itemList.add(new DivisionItem(R.drawable.chitagongdivission, "Chittagong Division"));
        itemList.add(new DivisionItem(R.drawable.rajshahidivission, "Rajshahi Division"));
        itemList.add(new DivisionItem(R.drawable.sylhetdivission, "Sylhet Division"));
        itemList.add(new DivisionItem(R.drawable.mymynshingdivission, "Mymensingh Division"));
        itemList.add(new DivisionItem(R.drawable.barisaldivission, "Barisal Division"));
        itemList.add(new DivisionItem(R.drawable.rangpurdivission, "Rangpur Division"));
        itemList.add(new DivisionItem(R.drawable.khulnadivission, "Khulna Division"));

        adapter = new DivisionAdapter(DivisionActivity.this, itemList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        filteredList = new ArrayList<>(itemList);
    }
    private void filter(String text){
        List<DivisionItem> filterList= new ArrayList<>();
        for(DivisionItem obj : itemList ){
            if(obj.getDivision().toLowerCase().contains(text.toLowerCase())){
                filterList.add(obj);
            }
        }
        adapter.filterList(filterList);
    }
}