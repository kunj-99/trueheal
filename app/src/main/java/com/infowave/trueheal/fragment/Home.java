package com.infowave.trueheal.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.infowave.trueheal.DoctorSpecialist;
import com.infowave.trueheal.R;
import com.infowave.trueheal.WishList;
import com.infowave.trueheal.adapter.SliderAdapter;
import com.infowave.trueheal.adapter.SpecialistAdapter;

public class Home extends Fragment {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    ImageButton heartIcon;
    TextView toolbarTitle, showmor;
    Button logoutButton;
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

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment layout
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize the drawer layout and toolbar
        drawerLayout = rootView.findViewById(R.id.drawer_layout);
        showmor = rootView.findViewById(R.id.showmor);
        toolbar = rootView.findViewById(R.id.tb);
        navigationView = rootView.findViewById(R.id.nev_view);
        heartIcon = rootView.findViewById(R.id.heart_icon);
        toolbarTitle = rootView.findViewById(R.id.toolbar_title);
        logoutButton = rootView.findViewById(R.id.logout_button);

        //Set up the toolbar
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        // Set up the drawer toggle
        toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Set up navigation item selection listener
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

//                if (id == R.id.payments1) {
//                    // Handle "Payments" click
//                    Intent intent = new Intent(getActivity(), payments.class);
//                    startActivity(intent);
//                } else if (id == R.id.records1) {
//                    // Handle "Medical Records" click
//                    Intent intent = new Intent(getActivity(), medicalrecords.class);
//                    startActivity(intent);
//                } else if (id == R.id.policy) {
//                    // Handle "Privacy Policy" click
//                    Intent intent = new Intent(getActivity(), privacypolicy.class);
//                    startActivity(intent);
//                } else if (id == R.id.notification) {
//                    // Handle "Notification" click
//                    Intent intent = new Intent(getActivity(), notification.class);
//                    startActivity(intent);
//                } else if (id == R.id.support1) {
//                    // Handle "Support" click
//                    Intent intent = new Intent(getActivity(), support.class);
//                    startActivity(intent);
//                } else if (id == R.id.shareapp1) {
//                    // Handle "Share App" click
//                    Intent intent = new Intent(getActivity(), shareapp.class);
//                    startActivity(intent);
//                } else if (id == R.id.aboutus1) {
//                    // Handle "About Us" click
//                    Intent intent = new Intent(getActivity(), aboutus.class);
//                    startActivity(intent);
//                } else if (id == R.id.settings1) {
//                    // Handle "Settings" click
//                    Intent intent = new Intent(getActivity(), settings.class);
//                    startActivity(intent);
//                }

                drawerLayout.closeDrawers();
                return true;
            }
        });

        // Handle the heart icon click event
        heartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), WishList.class);
                startActivity(in);
                Toast.makeText(getActivity(), "Heart Icon Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // Handle the logout button click event
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Logout successful", Toast.LENGTH_SHORT).show();
                // Implement logout logic here
            }
        });

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
                // Replace `YourTargetActivity.class` with the actual target activity
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