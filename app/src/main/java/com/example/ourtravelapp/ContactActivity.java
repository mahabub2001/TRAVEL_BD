package com.example.ourtravelapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ContactActivity extends AppCompatActivity {

    private CardView phone,message,mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        phone=findViewById(R.id.phoneID);
        message=findViewById(R.id.messageID);
        mail=findViewById(R.id.mailID);

        /*phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number="01964540702";
                Intent callIntent=new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse(number));
                startActivity(callIntent);
                Intent phoneCallIntent = new Intent(Intent.ACTION_CALL);
phoneCallIntent.setData(Uri.parse("tel:" + Uri.encode("*222*"+ rechargeNumber + "#");));
startActivity(phoneCallIntent);
            }
        });*/

        phone.setOnClickListener(view -> {
            // getting phone number from edit text and changing it to String
            String phone_number = "01964540702";
            // Getting instance of Intent with action as ACTION_CALL
            //Intent phone_intent = new Intent(Intent.ACTION_CALL);

            // Set data of Intent through Uri by parsing phone number
            //phone_intent.setData(Uri.parse("tel:" + phone_number));

            // start Intent
            //startActivity(phone_intent);
            sent_call(phone_number);
        });

        mail.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                String to="mahabub9463@gmail.com";
                //String subject=editTextSubject.getText().toString();
                //String message=editTextMessage.getText().toString();


                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
                //email.putExtra(Intent.EXTRA_SUBJECT, subject);
                //email.putExtra(Intent.EXTRA_TEXT, message);

                //need this to prompts email client only
                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose an Email client :"));

            }

        });


    }

    private void sent_call(String phone_number) {

        Intent phoneCallIntent = new Intent(Intent.ACTION_CALL);
        phoneCallIntent.setData(Uri.parse("tel:" + Uri.encode("*222*"+ phone_number + "#")));
        startActivity(phoneCallIntent);
        try{
            startActivity(Intent.createChooser(phoneCallIntent, "Choose a phone number"));

        }
        catch (Exception e)
        {
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }
}