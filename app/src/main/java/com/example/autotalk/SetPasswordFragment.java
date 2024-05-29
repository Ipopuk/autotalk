package com.example.autotalk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SetPasswordFragment extends Fragment {

    private EditText passwordEditText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set_password, container, false);

        passwordEditText = view.findViewById(R.id.loginPassword);
        Button setPasswordButton = view.findViewById(R.id.button_set_password);
        setPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = passwordEditText.getText().toString();

                if (password.isEmpty()) {
                    Toast.makeText(getContext(), "Please enter a password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getContext(), "Password should be at least 6 characters long", Toast.LENGTH_SHORT).show();
                    return;
                }

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                user.updatePassword(password)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    // Password updated successful, update UI with the signed-in user's information
                                    Toast.makeText(getContext(), "Password updated successful", Toast.LENGTH_SHORT).show();

                                    // Go to the next fragment
                                    MainActivity mainActivity = (MainActivity) getActivity();
                                    mainActivity.changeFragment(new EndRegistrationFragment());
                                } else {
                                    // Password update failed, display a message and update the UI
                                    Toast.makeText(getContext(), "Password update failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        return view;
    }
}