package com.example.autotalk;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class HelloCarActivity extends AppCompatActivity {
    TextView harpreet_text,studio_text;
    Animation fade_out_anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_hello);
        harpreet_text = findViewById(R.id.harpreet_text);
        studio_text = findViewById(R.id.studio_text);
        fade_out_anim = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_out);
        fade_out_anim.setDuration(2000); // set animation duration to 2 seconds
        harpreet_text.startAnimation(fade_out_anim);
        studio_text.startAnimation(fade_out_anim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(HelloCarActivity.this, CarSetDevicesActivity.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }
}