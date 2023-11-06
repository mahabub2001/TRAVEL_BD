package com.example.ourtravelapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class DemoActivity extends AppCompatActivity {
    private Button logout;
    FloatingActionButton floatingActionButton;
    TextInputEditText inputtxt;
    Button button;
    TextView textView2;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);



        inputtxt=findViewById(R.id.inputtxt);
//        textView2=findViewById(R.id.textView3);


//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String val=inputtxt.getText().toString();
//                textView2.setText(val);
//            }
//        });

//        logout=findViewById(R.id.logout_d);
//        floatingActionButton=findViewById(R.id.floatingActionButton);

//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(DemoActivity.this, "hello", Toast.LENGTH_SHORT).show();
//            }
//        });

//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut();
//                Toast.makeText(DemoActivity.this, "Logging out!", Toast.LENGTH_SHORT).show();
////                startActivity(new Intent(DemoActivity.this, StartActivity.class));
//                finish();
//            }
//        });

//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(DemoActivity.this,"hello",Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}