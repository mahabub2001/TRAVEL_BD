package com.example.ourtravelapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DestinationAdapter extends RecyclerView.Adapter<DestinationAdapter.MyViewHolder> {

    Context context;
    String[]destinationName;
    int[]image;

    public DestinationAdapter(Context context, String[] destinationName, int[] image) {

        this.context=context;
        this.destinationName=destinationName;
        this.image=image;
    }

    @NonNull
    @Override
    public DestinationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.destination_layout_design,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull DestinationAdapter.MyViewHolder holder, int position) {
        holder.name_image.setImageResource(image[position]);
        holder.name_destination.setText(destinationName[position]);
    }

    @Override
    public int getItemCount() {
        return image.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView name_destination;
        private ImageView name_image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name_destination=itemView.findViewById(R.id.titleId2);
            name_image=itemView.findViewById(R.id.imageId2);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            String destination=destinationName[position];
            int size=destinationName.length;

            Intent intent = new Intent(context,DescriptionActivity.class);
            intent.putExtra("selectedItem1",destination);
            intent.putExtra("name","National Museaum");
            //intent.putExtra("name","Ahsan Mojil");
            context.startActivity(intent);
        }
    }
}
