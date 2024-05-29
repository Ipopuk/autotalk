package com.example.autotalk;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SetPhoneFragment extends Fragment {

    private String username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set_phone, container, false);

        // Get the user's username from the previous fragment
        Bundle bundle = getArguments();
        if (bundle!= null) {
            username = bundle.getString("username");
        }

        Button button = view.findViewById(R.id.button_set_phone);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextInputLayout phoneLayout = view.findViewById(R.id.registerPhone);
                EditText phoneEditText = phoneLayout.getEditText();
                String phone = phoneEditText.getText().toString();

                if (phone.isEmpty()) {
                    Toast.makeText(getContext(), "Please enter a phone number", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d("PhoneAuth", "Phone number: " + phone);
                // Send verification code to the user's phone number
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        phone, // Replace with your country code
                        60L, // Timeout duration
                        TimeUnit.SECONDS,
                        requireActivity(),
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(PhoneAuthCredential credential) {
                                // This callback will be invoked in two situations:
                                // 1 - Instant verification. In some cases the phone number can be instantly
                                //     verified without needing to send or enter a verification code.
                                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                                //     detect the incoming verification SMS and perform verification without
                                //     user action.
                                Toast.makeText(getContext(), "Verification completed", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onVerificationFailed(FirebaseException e) {
                                // This callback is invoked in an invalid request for verification is made,
                                // for instance if the the phone number format is not valid.
                                Toast.makeText(getContext(), "Verification failed: " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();                                // [END_EXCLUDE]
                            }

                            @Override
                            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {
                                // The SMS verification code has been sent to the provided phone number, we
                                // now need to ask the user to enter the code and then construct a credential
                                // for signInWithCredential.
                                Toast.makeText(getContext(), "Code sent", Toast.LENGTH_SHORT).show();

                                // Save the verification ID
                                Bundle bundle = new Bundle();
                                bundle.putString("verificationId", verificationId);
                                bundle.putString("username", username);
                                bundle.putString("phone", phone);

                                // Go to the next fragment
                                VerificationFragment verificationFragment = new VerificationFragment();
                                verificationFragment.setArguments(bundle);
                                ((MainActivity) getActivity()).changeFragment(verificationFragment);
                            }
                        }
                );
            }
        });

        return view;
    }
}