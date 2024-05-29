package com.example.autotalk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class SetPhoneOrEmailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set_phone_or_email, container, false);

        Button button1 = view.findViewById(R.id.button_choose_phone);
        button1.setOnClickListener(v -> ((MainActivity) getActivity()).changeFragment(new SetPhoneFragment()));

        Button button2 = view.findViewById(R.id.button_choose_email);
//        button2.setOnClickListener(v -> ((MainActivity) getActivity()).changeFragment(new SetEmailFragment()));

        return view;
    }

}
