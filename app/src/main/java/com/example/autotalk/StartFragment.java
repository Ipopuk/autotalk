package com.example.autotalk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class StartFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, container, false);

        Button buttons = view.findViewById(R.id.button_start);
        buttons.setOnClickListener(v -> ((MainActivity) getActivity()).changeFragment(new SetUsernameFragment()));

        Button buttonj = view.findViewById(R.id.button_join);
        buttonj.setOnClickListener(v -> ((MainActivity) getActivity()).changeFragment(new LoginEmailFragment()));

        return view;
    }
}

