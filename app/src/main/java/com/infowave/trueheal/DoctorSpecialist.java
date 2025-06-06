package com.infowave.trueheal;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.infowave.trueheal.adapter.DoctorSpecialistAdapter;

public class DoctorSpecialist extends AppCompatActivity {

    private int[] specialistImages = {
            R.drawable.main1,
            R.drawable.main2,
            R.drawable.main3,
            R.drawable.main4,
            R.drawable.main5
    };

    private String[] specialistNames = {
            "Specialist 1", "Specialist 2", "Specialist 3", "Specialist 4", "Specialist 5"
    };

    RecyclerView specialist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_specialist);

        specialist = findViewById(R.id.specialistcategary);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        specialist.setLayoutManager(gridLayoutManager);

        // Here’s the FIX: Use the adapter, not the activity!
        DoctorSpecialistAdapter specialistAdapter =
                new DoctorSpecialistAdapter(specialistImages, specialistNames);
        specialist.setAdapter(specialistAdapter);
    }
}
