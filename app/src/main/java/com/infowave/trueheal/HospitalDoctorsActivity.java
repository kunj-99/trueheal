package com.infowave.trueheal;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.infowave.trueheal.adapter.DoctorSearchAdapter;

public class HospitalDoctorsActivity extends AppCompatActivity {

    RecyclerView doctorRecyclerView;
    DoctorSearchAdapter adapter;
    TextView hospitalNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_doctors);

        // Example dummy data for doctors in this hospital
        int[] doctorImages = {R.drawable.main1, R.drawable.main2};
        String[] doctorNames = {"Dr. A", "Dr. B"};
        String[] doctorSpecialties = {"Cardiologist", "Dermatologist"};
        String[] doctorAvailabilities = {"Available", "Not Available"};
        String[] pendingPatients = {"2 Pending", "5 Pending"};
        float[] doctorRatings = {4.5f, 4.0f};
        String[] doctorLocations = {"Rajkot", "Rajkot"};
        String[] hospitalNames = {"City Hospital", "Sun Clinic"}; // example


        String hospitalName = getIntent().getStringExtra("hospitalName");

        hospitalNameText = findViewById(R.id.hospitalNameText);
        doctorRecyclerView = findViewById(R.id.doctorRecyclerView);

        if (hospitalName != null) {
            hospitalNameText.setText("Doctors at " + hospitalName);
        }

        adapter = new DoctorSearchAdapter(this,
                doctorImages,
                doctorNames,
                doctorSpecialties,
                doctorAvailabilities,
                pendingPatients,
                doctorRatings,
                doctorLocations,
                hospitalNames  // ✅ added here
        );
        ;

        doctorRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        doctorRecyclerView.setAdapter(adapter);
    }
}
