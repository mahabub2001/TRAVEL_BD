package com.example.ourtravelapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DivisionAdapter extends RecyclerView.Adapter<DivisionAdapter.MyViewHolder>{

    private Context context;

    private List<DivisionItem> divission_item;

    public DivisionAdapter(Context context, ArrayList<DivisionItem> itemList) {
        this.context=context;
        divission_item=itemList;
    }

    @NonNull
    @Override
    public DivisionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.division_layout_design, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DivisionAdapter.MyViewHolder holder, int position) {
        DivisionItem item = divission_item.get(position);
        holder.name_image.setImageResource(item.getImage());
        holder.name_divission.setText(item.getDivision());
    }

    @Override
    public int getItemCount() {
        return divission_item.size();
    }

    public void filterList(List<DivisionItem>filterlist){
        divission_item = filterlist;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView name_divission;
        private ImageView name_image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name_divission = itemView.findViewById(R.id.titleId);
            name_image = itemView.findViewById(R.id.imageId);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            DivisionItem item = divission_item.get(position);
            String name = item.getDivision();
            System.out.println(name);
            //Log.i("value", "onClick: "+name);

            Intent intent = new Intent(context, DistrictActivity.class);
            intent.putExtra("selectedItem", name);
            context.startActivity(intent);
        }
    }
}
