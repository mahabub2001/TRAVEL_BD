package com.example.ourtravelapp;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Context;

import java.util.List;

public class Cart_adapter extends RecyclerView.Adapter<Cart_adapter.MyViewHolder> {
    private Context context;
    private List<Resort_booking_helper_class> uploadList;

    public Cart_adapter(Context context, List<Resort_booking_helper_class> uploadList) {
        this.context = context;
        this.uploadList = uploadList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.layout_for_cart,parent,false);
        return new MyViewHolder(view);
//        return
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, @SuppressLint("RecyclerView") int position) {


        Resort_booking_helper_class upload=uploadList.get(position);




        String _resortowner=upload.getResortowner();
        String _resortName=upload.getResortname();
        String _resortCustomername=upload.getCustomerusername();

        String ownerid=_resortowner+_resortName;
        String customerid=_resortCustomername+_resortName;


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Customer_books").child(_resortCustomername);
        DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("Owner_orders").child(_resortowner);



        myViewHolder.Resortnameview.setText(upload.getResortname());
        myViewHolder.Resortaddressview.setText(upload.getResortaddress());





        databaseReference.child(customerid).child("customerusername").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    String customerUsername = dataSnapshot.getValue(String.class);

                    if (customerUsername.equals("cancelled")) {


                        myViewHolder.acnonac.setText("Book Cancelled by Owner");
                        myViewHolder.roomtype.setText("");
                        myViewHolder.numberofrooms.setText("");
                        myViewHolder.personno.setText("");
                    } else {

//                        myViewHolder.cusname.setText("Customer Name : " + upload.getCustomerusername());


                        myViewHolder.acnonac.setText("Ac or Non Ac : " + upload.getAcornonac());
                        myViewHolder.roomtype.setText("Room Type : " + upload.getRoomtype());
                        myViewHolder.numberofrooms.setText("Number of Rooms : " + upload.getNumberofrooms());
                        myViewHolder.personno.setText("Number of Persons : " + upload.getNumberofpersons());

                    }



                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle potential errors here.
            }
        });








//        myViewHolder.cusname.setText("Customer Name : "+upload.getCustomerusername());
//
//
//        myViewHolder.acnonac.setText("Ac or Non Ac : "+upload.getAcornonac());
//        myViewHolder.roomtype.setText("Room Type : "+upload.getRoomtype());
//        myViewHolder.numberofrooms.setText("Number of Rooms : "+upload.getNumberofrooms());
//        myViewHolder.personno.setText("Number of Persons : "+upload.getNumberofpersons());

        //later update




        myViewHolder.cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                android.content.Context context5=view.getContext();

                databaseReference.child(customerid).removeValue()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                uploadList.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, uploadList.size());
                                Toast.makeText(context5, "Item deleted successfully", Toast.LENGTH_SHORT).show();



                                databaseReference2.child(ownerid).child("customerusername").setValue("cancelled");



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
        myViewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                android.content.Context context5=view.getContext();



                databaseReference.child(customerid).removeValue()
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

                                Toast.makeText(context5, "Failed to delete data from resorts", Toast.LENGTH_SHORT).show();
                            }
                        });


            }
        });


    }


//    @Override
//    public void onBindViewHolder(@NonNull Cart_adapter.MyViewHolder myViewHolder, @SuppressLint("RecyclerView") int position) {
//        Resort_booking_helper_class upload=uploadList.get(position);
//
////        myViewHolder.Resortnameview.setText(upload.getResortname());
////        myViewHolder.Resortaddressview.setText(upload.getResortaddress());
//
////        myViewHolder.acnonac.setText(upload.getAcornonac());
////        myViewHolder.roomtype.setText("Room Type : "+upload.getRoomtype());
////        myViewHolder.numberofrooms.setText("Number of Rooms : "+upload.getNumberofrooms());
////        myViewHolder.personno.setText("Number of Persons : "+upload.getNumberofpersons());
//
////        myViewHolder.cardacnonacViewId.setText(upload.getAcornonac());
////        myViewHolder.cardTypeViewId.setText(upload.getRoomtype());
////        myViewHolder.cardPersonNoViewId.setText(upload.getNumberofpersons());
////        myViewHolder.cardRoomNoViewId.setText(upload.getNumberofrooms());
//
//
//
//        String _resortowner=upload.getResortowner();
//        String _resortName=upload.getResortname();
//        String _resortCustomername=upload.getCustomerusername();
//
//        String ownerid=_resortowner+_resortName;
//        String customerid=_resortCustomername+_resortName;
//
//
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Customer_books").child(_resortCustomername);
//        DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("Owner_orders").child(_resortowner);
//
//
//        myViewHolder.Resortnameview.setText(upload.getResortname());
//        myViewHolder.Resortaddressview.setText(upload.getResortaddress());
//
//        databaseReference.child(customerid).child("customerusername").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
////                    // dataSnapshot.getValue() will contain the String value of customerusername
//                    String customerUsername = dataSnapshot.getValue(String.class);
//
//                    if (customerUsername.equals("cancelled")) {
////                        myViewHolder.cusname.setText("Book Cancelled");
//
//
//                        myViewHolder.acnonac.setText("");
//                        myViewHolder.roomtype.setText("");
//                        myViewHolder.numberofrooms.setText("");
//                        myViewHolder.personno.setText("");
//                    } else {
//
////                        myViewHolder.cusname.setText("Customer Name : " + upload.getCustomerusername());
//
//
//                        myViewHolder.acnonac.setText("Ac or Non Ac : " + upload.getAcornonac());
//                        myViewHolder.roomtype.setText("Room Type : " + upload.getRoomtype());
//                        myViewHolder.numberofrooms.setText("Number of Rooms : " + upload.getNumberofrooms());
//                        myViewHolder.personno.setText("Number of Persons : " + upload.getNumberofpersons());
//
//                    }
//
//
//
//                }
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // Handle potential errors here.
//            }
//        });
//
//        myViewHolder.cancel_Button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Get the reference to the Firebase database node you want to delete the item from
//                android.content.Context context5=view.getContext();
//
////                String _resortowner=upload.getResortowner();
////                String _resortName=upload.getResortname();
////                String _resortCustomername=upload.getCustomerusername();
////
////                String ownerid=_resortowner+_resortName;
////                String customerid=_resortCustomername+_resortName;
////
////
////                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Customer_books").child(_resortCustomername);
////                DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("Owner_orders").child(_resortowner);
//
////                String resortname5= upload.getResortname();
////
////                String uploadId = _resortowner+resortname5;
//
//                // Remove the item from Firebase
//                databaseReference.child(customerid).removeValue()
//                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                // Item deleted successfully from Firebase
//                                // Now remove the item from your local list and update the RecyclerView
//                                uploadList.remove(position);
//                                notifyItemRemoved(position);
//                                notifyItemRangeChanged(position, uploadList.size());
//                                Toast.makeText(context5, "Item deleted successfully", Toast.LENGTH_SHORT).show();
//
//
//
////                                databaseReference2.child(ownerid).removeValue()
////                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
////                                            @Override
////                                            public void onSuccess(Void aVoid) {
////                                                // Data deleted successfully from the "resorts" node
////                                                Toast.makeText(context5, "Data deleted successfully from resorts", Toast.LENGTH_SHORT).show();
////                                            }
////                                        })
////                                        .addOnFailureListener(new OnFailureListener() {
////                                            @Override
////                                            public void onFailure(@NonNull Exception e) {
////                                                // Failed to delete data from the "resorts" node
////                                                Toast.makeText(context5, "Failed to delete data from resorts", Toast.LENGTH_SHORT).show();
////                                            }
////                                        });
//
//
//                                databaseReference2.child(ownerid).child("customerusername").setValue("cancelled");
//
//
//
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                // Failed to delete the item from Firebase
//                                Toast.makeText(context5, "Failed to delete item", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//            }
//        });
//
//        myViewHolder.delete_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                android.content.Context context5=view.getContext();
//                databaseReference.child(customerid).removeValue()
//                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                // Item deleted successfully from Firebase
//                                // Now remove the item from your local list and update the RecyclerView
//                                uploadList.remove(position);
//                                notifyItemRemoved(position);
//                                notifyItemRangeChanged(position, uploadList.size());
//                                Toast.makeText(context5, "Item deleted successfully", Toast.LENGTH_SHORT).show();
//
//
//
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                // Failed to delete the item from Firebase
//                                Toast.makeText(context5, "Failed to delete item", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//            }
//        });
//
//
//
//
////        Upload upload2 = uploadList.get(position);
//
//        // Handle delete button click
////        myViewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                // Get the reference to the Firebase database node you want to delete the item from
////                android.content.Context context5=view.getContext();
////                String _resortowner=upload.getResortowner();
////
////
////                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Owner_resorts").child(_resortowner);
////                DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("Resorts");
////
////
////                // Get the unique key of the item to be deleted
////
////                String resortname5= upload.getResortname();
////
////                String uploadId = _resortowner+resortname5;
////
////                // Remove the item from Firebase
////                databaseReference.child(uploadId).removeValue()
////                        .addOnSuccessListener(new OnSuccessListener<Void>() {
////                            @Override
////                            public void onSuccess(Void aVoid) {
////                                // Item deleted successfully from Firebase
////                                // Now remove the item from your local list and update the RecyclerView
////                                uploadList.remove(position);
////                                notifyItemRemoved(position);
////                                notifyItemRangeChanged(position, uploadList.size());
////                                Toast.makeText(context5, "Item deleted successfully", Toast.LENGTH_SHORT).show();
////
////                                databaseReference2.child(uploadId).removeValue()
////                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
////                                            @Override
////                                            public void onSuccess(Void aVoid) {
////                                                // Data deleted successfully from the "resorts" node
////                                                Toast.makeText(context5, "Data deleted successfully from resorts", Toast.LENGTH_SHORT).show();
////                                            }
////                                        })
////                                        .addOnFailureListener(new OnFailureListener() {
////                                            @Override
////                                            public void onFailure(@NonNull Exception e) {
////                                                // Failed to delete data from the "resorts" node
////                                                Toast.makeText(context5, "Failed to delete data from resorts", Toast.LENGTH_SHORT).show();
////                                            }
////                                        });
////
////
////
////                            }
////                        })
////                        .addOnFailureListener(new OnFailureListener() {
////                            @Override
////                            public void onFailure(@NonNull Exception e) {
////                                // Failed to delete the item from Firebase
////                                Toast.makeText(context5, "Failed to delete item", Toast.LENGTH_SHORT).show();
////                            }
////                        });
////            }
////        });
//
//        // ... (rest of the code)
//
//
//
//
//    }

    @Override
    public int getItemCount() {
        return uploadList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Resortnameview,Resortaddressview;
//                cardPersonNoViewId,cardacnonacViewId,cardTypeViewId,cardRoomNoViewId;
//        ImageView imageView;
        Button deleteButton,cancel_button;
        TextView acnonac,roomtype,numberofrooms,personno;

//        TextView cardacnonacViewId,cardTypeViewId,cardRoomNoViewId;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Resortnameview=itemView.findViewById(R.id.cardResortViewId);
            Resortaddressview=itemView.findViewById(R.id.cardAddressViewId);

            acnonac=itemView.findViewById(R.id.cardacnonacViewId);
            roomtype=itemView.findViewById(R.id.cardTypeViewId);
            numberofrooms=itemView.findViewById(R.id.cardRoomNoViewId);
            personno=itemView.findViewById(R.id.cardPersonNoViewId);
//            imageView=itemView.findViewById(R.id.cardImageViewId);
            cancel_button=itemView.findViewById(R.id.cancelButton);
            deleteButton=itemView.findViewById(R.id.deleteButton);
        }
    }
}













//    <?xml version="1.0" encoding="utf-8"?>
//<androidx.cardview.widget.CardView xmlns:android="http://schemas.android.com/apk/res/android"
//        android:layout_width="match_parent"
//        android:layout_height="wrap_content"
//        android:layout_margin="16dp"
//        android:background="#F5F5F5"
//        android:elevation="8dp">
//
//<LinearLayout
//        android:layout_width="match_parent"
//                android:layout_height="match_parent"
//                android:orientation="vertical">
//
//
//
//<LinearLayout
//            android:layout_width="match_parent"
//                    android:layout_height="wrap_content"
//                    android:background="#D8D8E6"
//                    android:orientation="vertical"
//                    android:padding="16dp">
//
//<TextView
//                android:id="@+id/cardResortViewId"
//                        android:layout_width="match_parent"
//                        android:layout_height="wrap_content"
//                        android:fontFamily="sans-serif-medium"
//                        android:gravity="start"
//                        android:text="Resort Name"
//                        android:textColor="#000000"
//                        android:textSize="20sp" />
//
//<TextView
//                android:id="@+id/cardAddressViewId"
//                        android:layout_width="match_parent"
//                        android:layout_height="wrap_content"
//                        android:layout_marginTop="8dp"
//                        android:fontFamily="sans-serif"
//                        android:gravity="start"
//                        android:text="Resort Address"
//                        android:textColor="#666666"
//                        android:textSize="14sp" />
//<TextView
//                android:id="@+id/cardacnonacViewId"
//                        android:layout_width="match_parent"
//                        android:layout_height="wrap_content"
//                        android:layout_marginTop="8dp"
//                        android:fontFamily="sans-serif"
//                        android:gravity="start"
//                        android:text="Ac or Non AC"
//                        android:textColor="#666666"
//                        android:textSize="14sp" />
//<TextView
//                android:id="@+id/cardTypeViewId"
//                        android:layout_width="match_parent"
//                        android:layout_height="wrap_content"
//                        android:layout_marginTop="8dp"
//                        android:fontFamily="sans-serif"
//                        android:gravity="start"
//                        android:text="Room Type"
//                        android:textColor="#666666"
//                        android:textSize="14sp" />
//<TextView
//                android:id="@+id/cardRoomNoViewId"
//                        android:layout_width="match_parent"
//                        android:layout_height="wrap_content"
//                        android:layout_marginTop="8dp"
//                        android:fontFamily="sans-serif"
//                        android:gravity="start"
//                        android:text="Room No"
//                        android:textColor="#666666"
//                        android:textSize="14sp" />
//<TextView
//                android:id="@+id/cardPersonNoViewId"
//                        android:layout_width="match_parent"
//                        android:layout_height="wrap_content"
//                        android:layout_marginTop="8dp"
//                        android:fontFamily="sans-serif"
//                        android:gravity="start"
//                        android:text="Person no"
//                        android:textColor="#666666"
//                        android:textSize="14sp" />
//<Button
//                android:id="@+id/cancelButton"
//                        android:layout_width="wrap_content"
//                        android:layout_height="wrap_content"
//                        android:layout_gravity="end"
//                        android:layout_marginEnd="16dp"
//                        android:backgroundTint="@color/colorAccent"
//                        android:text="Cancel"
//                        android:textColor="@android:color/white" />
//
//<Button
//                android:id="@+id/deleteButton"
//                        android:layout_width="wrap_content"
//                        android:layout_height="wrap_content"
//                        android:layout_gravity="end"
//                        android:layout_marginEnd="16dp"
//                        android:backgroundTint="@color/colorAccent"
//                        android:text="Delete"
//                        android:textColor="@android:color/white" />
//
//
//</LinearLayout>
//
//</LinearLayout>
//</androidx.cardview.widget.CardView>
