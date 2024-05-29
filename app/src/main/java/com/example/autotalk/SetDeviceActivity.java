package com.example.autotalk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class SetDeviceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_device);

        Button next_btn = (Button) findViewById(R.id.button_add_device);//button class

        next_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){
                //Start new activity class
                Intent myIntent=new Intent(SetDeviceActivity.this, HelloCarActivity.class);
                startActivity(myIntent);
            }
        });
    }}