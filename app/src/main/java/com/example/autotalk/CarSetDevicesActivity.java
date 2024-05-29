package com.example.autotalk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class CarSetDevicesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_set_devices);

        Button next_btn = (Button) findViewById(R.id.button_confirm);//button class

        next_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){
                //Start new activity class
                Intent myIntent=new Intent(CarSetDevicesActivity.this, MainCarActivity.class);
                startActivity(myIntent);
            }
        });
    }}