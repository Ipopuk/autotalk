package com.example.autotalk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPasswordFragment extends Fragment {

    private FirebaseAuth mAuth;
    private String email; // Declare the email variable here

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_password, container, false);

        mAuth = FirebaseAuth.getInstance();

        // Initialize UI elements
        Button setPasswordButton = view.findViewById(R.id.button_set_password);

        // Get the email from the bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            email = bundle.getString("email"); // Change the key to "Email"
        }

        // Set click listener for the Set Password button
        setPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = ((TextInputEditText) view.findViewById(R.id.loginPassword)).getText().toString();

                // Proceed with Firebase authentication using the entered email and password
                mAuth.signInWithEmailAndPassword("honenene@yandex.ru", "Honenene123!")
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // User signed in successfully
                                    Toast.makeText(getActivity(), "Signed in successfully", Toast.LENGTH_SHORT).show();
                                    // Move to the next fragment or activity
                                } else {
                                    // If sign in fails, display a message to the user.
                                    try {
                                        throw task.getException();
                                    } catch (Exception e) {
                                        Toast.makeText(getActivity(), "Authentication failed: " + e.getMessage(),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });

            }
        });

        return view;
    }
}