package com.example.autotalk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class LoginEmailFragment extends Fragment {

    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_email, container, false);

        mAuth = FirebaseAuth.getInstance();

        // Initialize UI elements
        Button setEmailButton = view.findViewById(R.id.button_set_email);

        // Set click listener for the Set Email button
        setEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextInputLayout emailInputLayout = view.findViewById(R.id.registerEmail);
                TextInputEditText emailEditText = (TextInputEditText) emailInputLayout.getEditText();
                String email = emailEditText.getText().toString();
                LoginPasswordFragment passwordFragment = new LoginPasswordFragment();
                Bundle bundle = new Bundle();
                bundle.putString("email", email);
                passwordFragment.setArguments(bundle);
                ((MainActivity) getActivity()).changeFragment(passwordFragment);


            }
        });

        return view;
    }

}