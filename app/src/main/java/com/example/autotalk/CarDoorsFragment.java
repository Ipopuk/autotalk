package com.example.autotalk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class CarDoorsFragment extends Fragment {
    private int[] buttonIds = {R.id.button1, R.id.button2, R.id.button3, R.id.button4};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_doors, container, false);

        for (int i = 0; i < buttonIds.length; i++) {
            final int index = i;
            Button button = view.findViewById(buttonIds[i]);
            button.setOnClickListener(new View.OnClickListener() {
                private boolean imageChanged = false;

                @Override
                public void onClick(View v) {
                    ImageView imageView = view.findViewById(getResources().getIdentifier("circle" + (index + 1), "id", getActivity().getPackageName()));
                    if (!imageChanged) {
                        imageView.setImageResource(R.drawable.red_circle);
                        imageChanged = true;
                    } else {
                        imageView.setImageResource(R.drawable.green_circle);
                        imageChanged = false;
                    }
                }
            });
        }

        return view;
    }
}