package com.example.ourtravelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FeedbackActivity extends AppCompatActivity {

    EditText editTextContactEmailSubject, editTextContactEmailMessage;
    Button buttonContactUsSendMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        editTextContactEmailSubject=findViewById(R.id.contact_us_email_subject);
        editTextContactEmailMessage=findViewById(R.id.contact_us_email_message);

        buttonContactUsSendMail=findViewById(R.id.contact_us_send_email_button);

        buttonContactUsSendMail.setOnClickListener(view -> {
            String recipient = "mahabub9463@gmail.com";
            String subject = editTextContactEmailSubject.getText().toString();
            String message = editTextContactEmailMessage.getText().toString();
            sendEmail(recipient, subject, message);
        });

    }
    private void sendEmail(String recipient , String subject , String message)
    {
        Intent emailIntent = new Intent (Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {recipient});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try{
            startActivity(Intent.createChooser(emailIntent, "Choose an Email Content"));

        }
        catch (Exception e)
        {
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }
}