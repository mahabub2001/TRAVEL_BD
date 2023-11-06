package com.example.ourtravelapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.core.Context;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private List<Upload> uploadList;
//    private List<Upload> filteredList;

    private List<Upload> filteredList;
    public MyAdapter(Context context, List<Upload> uploadList) {
        this.context = context;
        this.uploadList = uploadList;
//        this.filteredList = new ArrayList<>(uploadList);
        this.filteredList = new ArrayList<>(uploadList);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(context)
//        View view=layoutInflater.inflate(R.layout.item_layout,viewGroup,false);
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.item_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        Upload upload=uploadList.get(position);
        myViewHolder.Resortnameview.setText(upload.getResortname());
        myViewHolder.Resortaddressview.setText(upload.getResortaddress());
        Picasso.get().load(upload.getImageurl()).fit().centerCrop().into(myViewHolder.imageView);
//        Picasso.get().load(upload.getImageurl()).fit().centerCrop().into(myViewHolder.imageView);
//        Picasso.get().load(upload.getImageurl()).placeholder(R.mipmap.ic_launcher_round).fit().centerCrop().into(myViewHolder.imageView);
        //        Picasso.get().load(upload.getImageurl()).into(myViewHolder.imageView);








        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.content.Context context1=view.getContext();
//                Toast.makeText(context1, upload.getImageurl(), Toast.LENGTH_SHORT).show();
                String _resortname= upload.getResortname();
                String _resortaddress=upload.getResortaddress();
                String _resortmail=upload.getResortmail();
                String _resortphone=upload.getResortphone();
                String _resortpicaddress=upload.getImageurl();
                String _resortowner=upload.getOwner_username();

                Intent intent = new Intent(context1,Resort_Booking_page.class);


                intent.putExtra("resortname",_resortname);
                intent.putExtra("resortaddress", _resortaddress);
                intent.putExtra("resortmail", _resortmail);
                intent.putExtra("resortphone", _resortphone);
                intent.putExtra("resortpicaddress",_resortpicaddress);
                intent.putExtra("resortowner",_resortowner);


                context1.startActivity(intent);
//                ((Activity) context1).finish();

            }
        });



    }



    @Override
    public int getItemCount() {
        return uploadList.size();
    }

    public void searchDataList(ArrayList<Upload> searchList){
        uploadList=searchList;
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Resortnameview,Resortaddressview;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Resortnameview=itemView.findViewById(R.id.cardResortViewId);
            Resortaddressview=itemView.findViewById(R.id.cardAddressViewId);
            imageView=itemView.findViewById(R.id.cardImageViewId);
        }
    }


}
