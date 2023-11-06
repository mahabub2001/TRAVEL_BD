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

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.MyViewHolder> {
    private Context context;
    private List<BusTicketHelperClass> uploadList;

    public TicketAdapter(Context context, List<BusTicketHelperClass> uploadList) {
        this.context = context;
        this.uploadList = uploadList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.layout_for_ticket,parent,false);
        return new MyViewHolder(view);
//        return
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, @SuppressLint("RecyclerView") int position) {
        BusTicketHelperClass upload=uploadList.get(position);
//        myViewHolder.Resortnameview.setText(upload.getResortname());
//        myViewHolder.Resortaddressview.setText(upload.getResortaddress());
//        Picasso.get().load(upload.getImageurl()).fit().centerCrop().into(myViewHolder.imageView);



        myViewHolder.cardname.setText("Passenger : "+upload.getName());
        myViewHolder.cardnid.setText("Nid : "+upload.getNid());
        myViewHolder.cardFrom.setText("From : "+upload.getFrom());
        myViewHolder.cardTo.setText("To : "+upload.getTo());
        myViewHolder.cardNumberofTickets.setText("Number of Ticket : "+upload.getTicketnumber());






        myViewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.content.Context context5=view.getContext();
                Toast.makeText(context5, "button is ook", Toast.LENGTH_SHORT).show();


                String _resortowner=upload.getName();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Busticket").child(_resortowner);







                databaseReference.child(_resortowner).removeValue()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                uploadList.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, uploadList.size());
                                Toast.makeText(context5, "Item deleted successfully", Toast.LENGTH_SHORT).show();



                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(context5, "Failed to delete item", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });






    }

    @Override
    public int getItemCount() {
        return uploadList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView cardnid,cardFrom,cardTo,cardNumberofTickets,cardname;
        Button deleteButton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardname=itemView.findViewById(R.id.cardname);
            cardnid=itemView.findViewById(R.id.cardnid);
            cardFrom=itemView.findViewById(R.id.cardfrom);
            cardTo=itemView.findViewById(R.id.cardto);
            cardNumberofTickets=itemView.findViewById(R.id.cardnumberofticket);
//            imageView=itemView.findViewById(R.id.cardImageViewId);
            deleteButton=itemView.findViewById(R.id.deleteButton);
        }
    }
}

