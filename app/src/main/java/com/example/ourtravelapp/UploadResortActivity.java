package com.example.ourtravelapp;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class UploadResortActivity extends AppCompatActivity {

//    Button back;
    private ImageView image_view_resort;
    private FloatingActionButton floatingActionButton;
    private ImageView imageMenu;
    private Uri imageuri;
    private Button uploadb;
    private  static  final int IMAGE_REQUEST=1;
    StorageTask uploadtask;

    private TextInputEditText resortnameiet,resortaddressiet,resortphoneiet,resortmailiet;

    StorageReference storageReference;
    DatabaseReference databaseReference,databaseReference2;
    String _name,_username,_email,_phoneNo,_password;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_resort);

//        back=findViewById(R.id.back);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(UploadResortActivity.this,MainActivity.class));
//                finish();
//            }
//        });


        profilefunc();

        resortnameiet=findViewById(R.id.resortnameiet);
        resortaddressiet=findViewById(R.id.resortaddressiet);
        resortphoneiet=findViewById(R.id.resortphoneiet);
        resortmailiet=findViewById(R.id.resortmailiet);
        image_view_resort=findViewById(R.id.image_view_resort);
        uploadb=findViewById(R.id.uploadb);



        uploadb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(UploadResortActivity.this, "hello", Toast.LENGTH_SHORT).show();
                if(uploadtask!=null && uploadtask.isInProgress()){
                    Toast.makeText(UploadResortActivity.this, "Uploading in progress", Toast.LENGTH_SHORT).show();
                }else {


                    saveData();
                }
            }
        });




        databaseReference= FirebaseDatabase.getInstance().getReference("Resorts");
//        databaseReference2= FirebaseDatabase.getInstance().getReference("users").child(_username).child("Myresort");
        databaseReference2=FirebaseDatabase.getInstance().getReference("Owner_resorts");
        storageReference=FirebaseStorage.getInstance().getReference("Resorts2");




        floatingActionButton=findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(UploadResortActivity.this, "heello floating", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent,IMAGE_REQUEST);
            }
        });


        imageMenu=findViewById(R.id.imageMenu);
        imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(UploadResortActivity.this,MainActivity.class));
//                finish();

                Intent intent7=new Intent(UploadResortActivity.this, MainActivity.class);
                putDataExtra(intent7);
                finish();

            }
        });
    }


//    protected void onActivityResult()

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IMAGE_REQUEST && resultCode==RESULT_OK  && data!=null && data.getData()!=null){
            imageuri=data.getData();
            Picasso.get().load(imageuri).into(image_view_resort);
//            Picasso.with(this).load
//            Picasso.w
        }
    }

    public String getFileExtension(Uri imageuri){
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageuri));
    }
    private void saveData(){
        String resortname=resortnameiet.getText().toString().trim();
        String resortaddress=resortaddressiet.getText().toString().trim();
        String resortphone=resortphoneiet.getText().toString().trim();
        String resortmail=resortmailiet.getText().toString().trim();


        if(resortname.isEmpty()){
            resortnameiet.setError("Enter Resort name");
            resortnameiet.requestFocus();
            return ;
        }
        if(resortaddress.isEmpty()){
            resortaddressiet.setError("Enter Resort name");
            resortaddressiet.requestFocus();
            return ;
        }
        if(resortphone.isEmpty()){
            resortphoneiet.setError("Enter Resort name");
            resortphoneiet.requestFocus();
            return ;
        }
        if(resortmail.isEmpty()){
            resortmailiet.setError("Enter Resort name");
            resortmailiet.requestFocus();
            return ;
        }

        StorageReference ref=storageReference.child(System.currentTimeMillis()+"."+getFileExtension(imageuri));



        ref.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(UploadResortActivity.this, "Upload successfull", Toast.LENGTH_SHORT).show();

                Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                while(!uriTask.isSuccessful());
                Uri downloadUrl=uriTask.getResult();



//                Upload upload=new Upload(taskSnapshot.getStorage().getDownloadUrl().toString(),resortname,resortaddress,resortphone,resortmail);


                Upload upload=new Upload(downloadUrl.toString(),resortname,resortaddress,resortphone,resortmail,_username);



//                String uploadid=databaseReference.push().getKey();
                String uploadid=_username+resortname;

                databaseReference.child(uploadid).setValue(upload);
                databaseReference2.child(_username).child(uploadid).setValue(upload);


            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UploadResortActivity.this, "Upload not successfull", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void profilefunc() {
        Intent intent = getIntent();
         _name = intent.getStringExtra("name");
         _username = intent.getStringExtra("username");
         _email = intent.getStringExtra("email");
         _phoneNo = intent.getStringExtra("phoneNo");
         _password = intent.getStringExtra("password");


//        Intent intent1=new Intent(MainActivity.this,User_profile.class);

//        intent1.putExtra("name", _name);
//        intent1.putExtra("username", _username);
//        intent1.putExtra("email", _email);
//        intent1.putExtra("phoneNo", _phoneNo);
//        intent1.putExtra("password", _password);
//
//        startActivity(intent1);
    }


    public void putDataExtra(Intent intent1){
//        Intent intent=getIntent();
//        String _name=intent.getStringExtra("name");
//        String _username=intent.getStringExtra("username");
//        String _email=intent.getStringExtra("email");
//        String _phoneNo=intent.getStringExtra("phoneNo");
//        String _password=intent.getStringExtra("password");

        profilefunc();

        intent1.putExtra("name", _name);
        intent1.putExtra("username", _username);
        intent1.putExtra("email", _email);
        intent1.putExtra("phoneNo", _phoneNo);
        intent1.putExtra("password", _password);

        startActivity(intent1);

    }
}