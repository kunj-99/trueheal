package com.infowave.trueheal.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infowave.trueheal.R;
import com.infowave.trueheal.adapter.DoctorSearchAdapter;
import com.infowave.trueheal.adapter.HospitalSearchAdapter;

public class Search extends Fragment {

    private RecyclerView recyclerView, hospitalRecyclerView;
    private DoctorSearchAdapter doctorAdapter;
    private HospitalSearchAdapter hospitalAdapter;

    // Sample data for doctors
    private int[] doctorImages = {R.drawable.main1, R.drawable.main2, R.drawable.main3};
    private String[] doctorNames = {"Dr. Tranquilli", "Dr. Bonebrake", "Dr. Johnson"};
    private String[] doctorSpecialties = {"Specialist Medicine", "Specialist Dentist", "Dermatology"};
    private String[] doctorAvailabilities = {"Available", "Not Available", "Available"};
    private String[] pendingPatients = {"10 patients pending", "5 patients pending", "12 patients pending"};
    private float[] doctorRatings = {4.5f, 3.5f, 4.0f};

    // New doctor locations array with full addresses
    private String[] doctorLocations = {
            "\"Plot No.251, Police Station, 150 Feet Ring Rd,\\nopp. Gandhigram, Dharam Nagar, Rajkot, Gujarat 360007",
            "\"Plot No.251, Police Station, 150 Feet Ring Rd,\\nopp. Gandhigram, Dharam Nagar, Rajkot, Gujarat 360007",
            "\"Plot No.251, Police Station, 150 Feet Ring Rd,\\nopp. Gandhigram, Dharam Nagar, Rajkot, Gujarat 360007"
    };

    // Sample data for hospitals
    private int[] hospitalImages = {R.drawable.main1, R.drawable.main5, R.drawable.main4};
    private String[] hospitalNames = {"City Hospital", "Green Valley Clinic", "HealthCare Center"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // Initialize RecyclerView for doctors
        recyclerView = view.findViewById(R.id.doctor_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Set up doctor adapter, passing doctor locations
        doctorAdapter = new DoctorSearchAdapter(getContext(), doctorImages, doctorNames, doctorSpecialties, doctorAvailabilities, pendingPatients, doctorRatings, doctorLocations);
        recyclerView.setAdapter(doctorAdapter);

        // Initialize RecyclerView for hospitals
        hospitalRecyclerView = view.findViewById(R.id.hospital);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        hospitalRecyclerView.setLayoutManager(gridLayoutManager);
        hospitalAdapter = new HospitalSearchAdapter(hospitalImages, hospitalNames);
        hospitalRecyclerView.setAdapter(hospitalAdapter);

        return view;
    }
}