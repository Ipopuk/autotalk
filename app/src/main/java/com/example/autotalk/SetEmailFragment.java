package com.example.autotalk;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SetEmailFragment extends Fragment {

    private String username;
    String email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set_email, container, false);

        // Get the user's username from the previous fragment
        Bundle bundle = getArguments();
        if (bundle!= null) {
            username = bundle.getString("username");
        }

        Button button = view.findViewById(R.id.button_set_email);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextInputLayout phoneLayout = view.findViewById(R.id.registerEmail);
                EditText phoneEditText = phoneLayout.getEditText();
                email = phoneEditText.getText().toString();

                if (email.isEmpty()) {
                    Toast.makeText(getContext(), "Please enter an email", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d("EmailAuth", "Email: " + email);

                sendVerificationEmail();
            }
        });

        return view;
    }
    private void sendVerificationEmail() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email, "temporaryPassword") // create a temporary user
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = task.getResult().getUser();
                            user.sendEmailVerification()
                                    .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(getContext(), "Verification email sent", Toast.LENGTH_SHORT).show();

                                                // Go to the next fragment
                                                VerificationEmailFragment verificationEmailFragment = new VerificationEmailFragment();
                                                Bundle bundle = new Bundle();
                                                bundle.putString("username", username);
                                                bundle.putString("email", email);
                                                verificationEmailFragment.setArguments(bundle);
                                                ((MainActivity) getActivity()).changeFragment(verificationEmailFragment);
                                            } else {
                                                Toast.makeText(getContext(), "Failed to send verification email", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        } else {
                            Toast.makeText(getContext(), "Failed to create user", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}