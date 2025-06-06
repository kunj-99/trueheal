package com.infowave.trueheal.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.infowave.trueheal.DoctorSpecialist;
import com.infowave.trueheal.R;
import com.infowave.trueheal.adapter.SliderAdapter;
import com.infowave.trueheal.adapter.SpecialistAdapter;

public class Home extends Fragment {


    Button showmor;
    RecyclerView recyclerView, specialist;
    SliderAdapter adapter;

    // Example image resources for main slider
    private int[] imageList = {
            R.drawable.main1,
            R.drawable.main2,
            R.drawable.main3,
            R.drawable.main4,
            R.drawable.main5
    };

    // Example data for specialists (images and text)
    private int[] specialistImages = {
            R.drawable.main1,
            R.drawable.main2,
            R.drawable.main3,
            R.drawable.main4,
            R.drawable.main5,
            R.drawable.main5
    };

    private String[] specialistNames = {
            "Specialist 1", "Specialist 2", "Specialist 3", "Specialist 4", "Specialist 5", "Specialist 5"
    };

    private Handler handler;
    private Runnable runnable;
    private int scrollPosition = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment layout
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        showmor = rootView.findViewById(R.id.btn_show_more);

        // Initialize the main RecyclerView for image slider
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new SliderAdapter(imageList);
        recyclerView.setAdapter(adapter);

        // Auto-scroll setup
        handler = new Handler(Looper.getMainLooper());
        runnable = new Runnable() {
            @Override
            public void run() {
                scrollPosition = (scrollPosition + 1) % imageList.length;
                recyclerView.smoothScrollToPosition(scrollPosition);
                handler.postDelayed(this, 5000); // Adjust time interval as needed
            }
        };
        handler.postDelayed(runnable, 5000); // Start auto-scrolling

        // Set a GridLayoutManager with 3 columns for specialists
        specialist = rootView.findViewById(R.id.specialist);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        specialist.setLayoutManager(gridLayoutManager);
        SpecialistAdapter specialistAdapter = new SpecialistAdapter(specialistImages, specialistNames);
        specialist.setAdapter(specialistAdapter);

        //  Handle the show more button click event
        showmor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), DoctorSpecialist.class));
            }
        });

        return rootView;  // Return the root view
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable); // Stop auto-scrolling when fragment is destroyed
        }
    }
}
