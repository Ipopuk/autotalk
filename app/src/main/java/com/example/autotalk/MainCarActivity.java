package com.example.autotalk;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class MainCarActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private Button buttonA, buttonB, buttonC, buttonD, buttonE;
    protected static final int RESULT_SPEECH = 1;
    private ImageButton btnSpeak;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_main);

        viewPager = findViewById(R.id.view_pager);
        buttonA = findViewById(R.id.button_a);
        buttonB = findViewById(R.id.button_b);
        buttonC = findViewById(R.id.button_c);
        buttonD = findViewById(R.id.button_d);
        buttonE = findViewById(R.id.button_e);

        FragmentAdapter adapter = new FragmentAdapter(this);
        viewPager.setAdapter(adapter);

        btnSpeak = findViewById(R.id.btnSpeak);
        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
                try {
                    startActivityForResult(intent, RESULT_SPEECH);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "Your device doesn't support Speech to Text", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
                buttonA.setBackgroundColor(Color.RED);
                resetButtonColors(buttonA);
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
                buttonB.setBackgroundColor(Color.RED);
                resetButtonColors(buttonB);
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
                buttonC.setBackgroundColor(Color.RED);
                resetButtonColors(buttonC);
            }
        });

        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(3);
                buttonD.setBackgroundColor(Color.RED);
                resetButtonColors(buttonD);
            }
        });

        buttonE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(4);
                buttonE.setBackgroundColor(Color.RED);
                resetButtonColors(buttonE);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case RESULT_SPEECH:
                if(resultCode == RESULT_OK && data!= null){
                    ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String spokenText = text.get(0).toLowerCase();

                    if (spokenText.contains("fragment a") || spokenText.contains("doors")) {
                        viewPager.setCurrentItem(0);
                        buttonA.setBackgroundColor(Color.RED);
                        resetButtonColors(buttonA);
                    } else if (spokenText.contains("light")) {
                        viewPager.setCurrentItem(2);
                        buttonC.setBackgroundColor(Color.RED);
                        resetButtonColors(buttonC);
                    } else if (spokenText.contains("first door")) {
                        Fragment fragment = getSupportFragmentManager().findFragmentByTag("f" + viewPager.getCurrentItem());
                        if (fragment instanceof CarDoorsFragment) {
                            Log.e("MMMMMMMMM", "OPA");
                            Button button = fragment.getView().findViewById(R.id.button1);
                            button.performClick();
                        }
                    } else if (spokenText.contains("button two")) {
                        Fragment fragment = getSupportFragmentManager().findFragmentByTag("f" + viewPager.getCurrentItem());
                        if (fragment instanceof CarDoorsFragment) {
                            Button button = fragment.getView().findViewById(R.id.button2);
                            button.performClick();
                        }
                    } else if (spokenText.contains("button 3")) {
                        Fragment fragment = getSupportFragmentManager().findFragmentByTag("f" + viewPager.getCurrentItem());
                        if (fragment instanceof CarDoorsFragment) {
                            Button button = fragment.getView().findViewById(R.id.button3);
                            button.performClick();
                        }
                    } else if (spokenText.contains("button 4")) {
                        Fragment fragment = getSupportFragmentManager().findFragmentByTag("f" + viewPager.getCurrentItem());
                        if (fragment instanceof CarDoorsFragment) {
                            Button button = fragment.getView().findViewById(R.id.button4);
                            button.performClick();
                        }
                    }
                }
                break;
        }
    }

    private void resetButtonColors(Button excludeButton) {
        buttonA.setBackgroundColor(Color.BLACK);
        buttonB.setBackgroundColor(Color.BLACK);
        buttonC.setBackgroundColor(Color.BLACK);
        buttonD.setBackgroundColor(Color.BLACK);
        buttonE.setBackgroundColor(Color.BLACK);
        excludeButton.setBackgroundColor(Color.RED);
    }

}