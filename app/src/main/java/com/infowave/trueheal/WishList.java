package com.infowave.trueheal;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.infowave.trueheal.adapter.WishListAdapter;

public class WishList extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);

        // Find the RecyclerView
        recyclerView = findViewById(R.id.doctor);  // Corrected this line

        // Data for RecyclerView
        int[] images = {R.drawable.main1, R.drawable.main2, R.drawable.main3}; // Update with your image resources
        String[] names = {"Dr. Tranquilli", "Dr. Smith", "Dr. Johnson"};
        String[] specialties = {"Specialist Medicine", "Cardiology", "Dermatology"};
        String[] experiences = {"6 Years Experience", "10 Years Experience", "8 Years Experience"};
        String[] availabilities = {"Available", "Not Available", "Available"};
        String[] pendingPatients = {"10 patients pending", "5 patients pending", "12 patients pending"};

        // Set up the adapter
        WishListAdapter adapter = new WishListAdapter(images, names, specialties, experiences, availabilities, pendingPatients);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Use 'this' as context
        recyclerView.setAdapter(adapter);
    }
}