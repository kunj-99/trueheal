package com.infowave.trueheal;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class Login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Find the login button and set a click listener
        Button login = findViewById(R.id.login_button);
        TextView singus = findViewById(R.id.join_us);
        // On click, show the OTP BottomSheetDialog
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showOtpBottomSheet();
            }
        });

        singus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Login.this, SignUp.class);
                startActivity(in);
            }
        });

    }

    private void showOtpBottomSheet() {
        // Create the BottomSheetDialog
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(Login.this, R.style.BottomSheetDialog);

        // Inflate the bottom sheet layout
        View bottomSheetView = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.otp_bottom_sheet, null);

        // Set the bottom sheet view to the dialog
        bottomSheetDialog.setContentView(bottomSheetView);

        // Initialize the EditText fields for OTP input
        EditText digit1 = bottomSheetView.findViewById(R.id.digit1);
        EditText digit2 = bottomSheetView.findViewById(R.id.digit2);
        EditText digit3 = bottomSheetView.findViewById(R.id.digit3);
        EditText digit4 = bottomSheetView.findViewById(R.id.digit4);

        // Set up TextWatchers for OTP inputs, passing bottomSheetView
        setupTextWatchers(bottomSheetView, digit1, digit2, digit3, digit4, bottomSheetDialog);

        // Show the dialog
        bottomSheetDialog.show();
    }

    // Set TextWatchers for OTP fields
    private void setupTextWatchers(View bottomSheetView, EditText digit1, EditText digit2, EditText digit3, EditText digit4, BottomSheetDialog bottomSheetDialog) {
        digit1.addTextChangedListener(new OTPTextWatcher(digit1, digit2));
        digit2.addTextChangedListener(new OTPTextWatcher(digit2, digit3));
        digit3.addTextChangedListener(new OTPTextWatcher(digit3, digit4));
        digit4.addTextChangedListener(new OTPTextWatcher(digit4, null)); // null because there's no next field

        // Handle Continue button click inside the BottomSheetDialog
        Button continueButton = bottomSheetView.findViewById(R.id.continue_button);
        if (continueButton != null) {
            continueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(Login.this, MainActivity.class);
                    startActivity(in);
                    bottomSheetDialog.dismiss();  // Dismiss the dialog after Continue is clicked
                }
            });
        }
    }

    private static class OTPTextWatcher implements TextWatcher {

        private View currentView;
        private View nextView;

        OTPTextWatcher(View currentView, View nextView) {
            this.currentView = currentView;
            this.nextView = nextView;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // No action needed here
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 1 && nextView != null) {
                nextView.requestFocus(); // Move to the next EditText
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            // No action needed here
        }

    }

}