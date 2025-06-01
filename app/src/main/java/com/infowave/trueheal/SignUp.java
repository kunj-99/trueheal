package com.infowave.trueheal;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {


    TextView tvLogin;
    Button btnSignUp;
    AutoCompleteTextView etCity;
    EditText etDob, etMobileNumber, etFirstName, etLastName;
    RadioGroup rgGender;
    CheckBox cbTerms;
    String url = "https://helthline.in/signup.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize UI elements
        tvLogin = findViewById(R.id.tv_login);
        btnSignUp = findViewById(R.id.btn_sign_up);
        etCity = findViewById(R.id.et_city);
        etDob = findViewById(R.id.et_dob);
        etMobileNumber = findViewById(R.id.et_mobile_number);
        etFirstName = findViewById(R.id.et_first_name);
        etLastName = findViewById(R.id.et_last_name);
        rgGender = findViewById(R.id.radio_group_gender);
        cbTerms = findViewById(R.id.cb_terms);

        // Set an OnClickListener on the "Login" TextView
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener on "Sign Up" Button to validate and open OTP BottomSheet
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateFields()) {
                    showOtpBottomSheet();
                }
                if (validateFields()) {
                    sendUserData();
                }
            }
        });

        // Set OnClickListener on Date of Birth field to show DatePicker
        etDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        // Populate cities in AutoCompleteTextView
        String[] cities = {"New York", "Los Angeles", "Chicago", "Houston", "Phoenix", "Philadelphia", "San Antonio", "San Diego", "Dallas", "San Jose"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, cities);
        etCity.setAdapter(adapter);
    }

    // Validate all the input fields before proceeding
    private boolean validateFields() {
        // Get input values from the fields
        String mobileNumber = etMobileNumber.getText().toString().trim();
        String firstName = etFirstName.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();
        String city = etCity.getText().toString().trim();
        String dob = etDob.getText().toString().trim();
        int selectedGenderId = rgGender.getCheckedRadioButtonId();

        // Initialize gender variable
        String gender = null;
        boolean isValid = true;

        // Validate mobile number
        if (mobileNumber.isEmpty() || mobileNumber.length() != 10) {
            etMobileNumber.setError("Enter a valid 10-digit mobile number");
            isValid = false;
        } else {
            etMobileNumber.setError(null); // Clear previous error
        }

        // Validate first name
        if (firstName.isEmpty()) {
            etFirstName.setError("First name is required");
            isValid = false;
        } else {
            etFirstName.setError(null); // Clear previous error
        }

        // Validate last name
        if (lastName.isEmpty()) {
            etLastName.setError("Last name is required");
            isValid = false;
        } else {
            etLastName.setError(null); // Clear previous error
        }

        // Validate city
        if (city.isEmpty()) {
            etCity.setError("City is required");
            isValid = false;
        } else {
            etCity.setError(null); // Clear previous error
        }

        // Validate date of birth
        if (dob.isEmpty()) {
            etDob.setError("Date of birth is required");
            isValid = false;
        } else {
            etDob.setError(null); // Clear previous error
        }

        // Validate gender selection
        if (selectedGenderId == -1) {
            Toast.makeText(this, "Please select a gender", Toast.LENGTH_SHORT).show();
            isValid = false;
        } else {
            RadioButton selectedGender = findViewById(selectedGenderId);
            gender = selectedGender.getText().toString();
        }

        // Validate terms checkbox
        if (!cbTerms.isChecked()) {
            cbTerms.setError("You must agree to the Terms of Service and Privacy Policy");
            isValid = false;
        } else {
            cbTerms.setError(null); // Clear previous error
        }

        // If validation is successful, return the values


        return isValid;
    }

    private void sendUserData() {
        // Create a StringRequest to send data to your API
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle the success response
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            String message = jsonResponse.getString("message");
                            Toast.makeText(SignUp.this, message, Toast.LENGTH_SHORT).show();

                            if (success) {
                                showOtpBottomSheet(); // Show OTP bottom sheet if signup is successful
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error response
                        Toast.makeText(SignUp.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("firstname", etFirstName.getText().toString());
                params.put("lastname", etLastName.getText().toString());
                params.put("phonenumber", etMobileNumber.getText().toString());
                params.put("dob", etDob.getText().toString());
                params.put("city", etCity.getText().toString());
                params.put("gender", ((RadioButton) findViewById(rgGender.getCheckedRadioButtonId())).getText().toString());
                return params;
            }
        };

        // Add the request to the RequestQueue
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    private void showDatePicker() {
        // Get the current date
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create and show DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                SignUp.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                        String dob = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        etDob.setText("Date of Birth: " + dob);
                        int age = calculateAge(selectedYear, selectedMonth, selectedDay);
                        etDob.setText(age + " years");
                    }
                },
                year, month, day);
        datePickerDialog.show();
    }

    private int calculateAge(int year, int month, int day) {
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        dob.set(year, month, day);
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        return age;
    }

    private void showOtpBottomSheet() {
        // Create the BottomSheetDialog
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(SignUp.this, R.style.BottomSheetDialog);

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

        // Set up TextWatchers for OTP inputs
        setupTextWatchers(digit1, digit2, digit3, digit4);

        // Handle Continue button click inside the BottomSheetDialog
        Button continueButton = bottomSheetView.findViewById(R.id.continue_button);
        if (continueButton != null) {
            continueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(SignUp.this, MainActivity.class);
                    startActivity(in);
                    bottomSheetDialog.dismiss();  // Dismiss the dialog after Continue is clicked
                }
            });
        }

        // Show the dialog
        bottomSheetDialog.show();
    }

    // Set TextWatchers for OTP fields
    private void setupTextWatchers(EditText digit1, EditText digit2, EditText digit3, EditText digit4) {
        digit1.addTextChangedListener(new OTPTextWatcher(digit1, digit2));
        digit2.addTextChangedListener(new OTPTextWatcher(digit2, digit3));
        digit3.addTextChangedListener(new OTPTextWatcher(digit3, digit4));
        digit4.addTextChangedListener(new OTPTextWatcher(digit4, null)); // null because there's no next field
    }

    // Inner class to handle moving focus to the next input field
    private class OTPTextWatcher implements TextWatcher {

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