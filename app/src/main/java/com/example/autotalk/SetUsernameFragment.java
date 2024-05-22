package com.example.autotalk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

public class SetUsernameFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set_username, container, false);

        Button button = view.findViewById(R.id.button_set_username);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextInputEditText usernameEditText = view.findViewById(R.id.inputUsername);
                String username = usernameEditText.getText().toString();

                if (username.isEmpty()) {
                    Toast.makeText(getContext(), "Please enter a username", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Save the user's username
                Bundle bundle = new Bundle();
                bundle.putString("username", username);

                // Go to the next fragment
                SetPhoneFragment setPhoneFragment = new SetPhoneFragment();
                setPhoneFragment.setArguments(bundle);
                ((MainActivity) getActivity()).changeFragment(setPhoneFragment);
            }
        });

        return view;
    }
}
