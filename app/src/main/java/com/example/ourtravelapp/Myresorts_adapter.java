package com.example.ourtravelapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Context;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Myresorts_adapter extends RecyclerView.Adapter<Myresorts_adapter.MyViewHolder> {
    private Context context;
    private List<Upload> uploadList;

    public Myresorts_adapter(Context context, List<Upload> uploadList) {
        this.context = context;
        this.uploadList = uploadList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.my_resort_layout,parent,false);
        return new MyViewHolder(view);
//        return
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, @SuppressLint("RecyclerView") int position) {
        Upload upload=uploadList.get(position);
        myViewHolder.Resortnameview.setText(upload.getResortname());
        myViewHolder.Resortaddressview.setText(upload.getResortaddress());
        Picasso.get().load(upload.getImageurl()).fit().centerCrop().into(myViewHolder.imageView);







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

                Intent intent = new Intent(context1,Resort_Booking_page.class);


                intent.putExtra("resortname",_resortname);
                intent.putExtra("resortaddress", _resortaddress);
                intent.putExtra("resortmail", _resortmail);
                intent.putExtra("resortphone", _resortphone);
                intent.putExtra("resortpicaddress",_resortpicaddress);


                context1.startActivity(intent);
            }
        });




//        myViewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                android.content.Context context1=view.getContext();
//////                Toast.makeText(context1, upload.getImageurl(), Toast.LENGTH_SHORT).show();
////                String _resortname= upload.getResortname();
////                String _resortaddress=upload.getResortaddress();
////                String _resortmail=upload.getResortmail();
////                String _resortphone=upload.getResortphone();
////                String _resortpicaddress=upload.getImageurl();
////
////                Intent intent = new Intent(context1,Resort_Booking_page.class);
////
////
////                intent.putExtra("resortname",_resortname);
////                intent.putExtra("resortaddress", _resortaddress);
////                intent.putExtra("resortmail", _resortmail);
////                intent.putExtra("resortphone", _resortphone);
////                intent.putExtra("resortpicaddress",_resortpicaddress);
////
////
////                context1.startActivity(intent);
//            }
//        });

//        myViewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                android.content.Context context1=view.getContext();
//                Toast.makeText(context1, "delete clicked", Toast.LENGTH_SHORT).show();
//
//
//
//
//
//            }
//        });



//        Upload upload2 = uploadList.get(position);

        // Handle delete button click
        myViewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the reference to the Firebase database node you want to delete the item from
                android.content.Context context5=view.getContext();
                String _resortowner=upload.getOwner_username();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Owner_resorts").child(_resortowner);
                DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("Resorts");


                // Get the unique key of the item to be deleted

                String resortname5= upload.getResortname();

                String uploadId = _resortowner+resortname5;

                // Remove the item from Firebase
                databaseReference.child(uploadId).removeValue()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Item deleted successfully from Firebase
                                // Now remove the item from your local list and update the RecyclerView
                                uploadList.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, uploadList.size());
                                Toast.makeText(context5, "Item deleted successfully", Toast.LENGTH_SHORT).show();

                                databaseReference2.child(uploadId).removeValue()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                // Data deleted successfully from the "resorts" node
                                                Toast.makeText(context5, "Data deleted successfully from resorts", Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                // Failed to delete data from the "resorts" node
                                                Toast.makeText(context5, "Failed to delete data from resorts", Toast.LENGTH_SHORT).show();
                                            }
                                        });



                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Failed to delete the item from Firebase
                                Toast.makeText(context5, "Failed to delete item", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        // ... (rest of the code)




    }

    @Override
    public int getItemCount() {
        return uploadList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Resortnameview,Resortaddressview;
        ImageView imageView;
        Button deleteButton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Resortnameview=itemView.findViewById(R.id.cardResortViewId);
            Resortaddressview=itemView.findViewById(R.id.cardAddressViewId);
            imageView=itemView.findViewById(R.id.cardImageViewId);
            deleteButton=itemView.findViewById(R.id.deleteButton);
        }
    }
}
