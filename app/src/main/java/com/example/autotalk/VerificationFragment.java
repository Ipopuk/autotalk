package com.example.autotalk;

import android.os.Bundle;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class VerificationFragment extends Fragment {

    private String verificationId;
    private String username;
    private String phone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_verification, container, false);

        // Get the verification ID, username, and phone number from the previous fragment
        Bundle bundle = getArguments();
        if (bundle != null) {
            verificationId = bundle.getString("verificationId");
            username = bundle.getString("username");
            phone = bundle.getString("phone");
        }

        Button checkButton = view.findViewById(R.id.button_check);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputCode1 = view.findViewById(R.id.inputCode1);
                EditText inputCode2 = view.findViewById(R.id.inputCode2);
                EditText inputCode3 = view.findViewById(R.id.inputCode3);
                EditText inputCode4 = view.findViewById(R.id.inputCode4);
                EditText inputCode5 = view.findViewById(R.id.inputCode5);
                EditText inputCode6 = view.findViewById(R.id.inputCode6);

                String code = inputCode1.getText().toString() + inputCode2.getText().toString() +
                              inputCode3.getText().toString() + inputCode4.getText().toString() +
                              inputCode5.getText().toString() + inputCode6.getText().toString();

                if (code.isEmpty()) {
                    Toast.makeText(getContext(), "Please enter the verification code", Toast.LENGTH_SHORT).show();
                    return;
                }

                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
                signInWithPhoneAuthCredential(credential);
            }
        });

        return view;
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getContext(), "Verification successful", Toast.LENGTH_SHORT).show();

                            // Go to the next fragment
                            SetPasswordFragment setPasswordFragment = new SetPasswordFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("username", username);
                            bundle.putString("phone", phone);
                            setPasswordFragment.setArguments(bundle);
                            ((MainActivity) getActivity()).changeFragment(setPasswordFragment);
                        } else {
                            // Sign in failed, display a message and update the UI
                            Toast.makeText(getContext(), "Verification failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}