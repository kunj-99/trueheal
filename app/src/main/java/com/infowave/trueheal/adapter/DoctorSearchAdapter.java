package com.infowave.trueheal.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infowave.trueheal.BookAppointmen;
import com.infowave.trueheal.Details;
import com.infowave.trueheal.InstantAppointment;
import com.infowave.trueheal.R;


public class DoctorSearchAdapter extends RecyclerView.Adapter<DoctorSearchAdapter.DoctorViewHolder> {

    private Context context;
    private int[] doctorImages;
    private String[] doctorNames, doctorSpecialties, doctorAvailabilities, pendingPatients, doctorLocations;
    private float[] doctorRatings;

    // Constructor
    public DoctorSearchAdapter(Context context, int[] doctorImages, String[] doctorNames, String[] doctorSpecialties,
                               String[] doctorAvailabilities, String[] pendingPatients, float[] doctorRatings, String[] doctorLocations) {
        this.context = context;
        this.doctorImages = doctorImages;
        this.doctorNames = doctorNames;
        this.doctorSpecialties = doctorSpecialties;
        this.doctorAvailabilities = doctorAvailabilities;
        this.pendingPatients = pendingPatients;
        this.doctorRatings = doctorRatings;
        this.doctorLocations = doctorLocations;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_doctor_search, parent, false);
        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        // Bind data
        holder.doctorImage.setImageResource(doctorImages[position]);
        holder.doctorName.setText(doctorNames[position]);
        holder.doctorSpecialty.setText(doctorSpecialties[position]);
        holder.doctorAvailability.setText(doctorAvailabilities[position]);
        holder.pendingPatients.setText(pendingPatients[position]);
        holder.doctorRating.setRating(doctorRatings[position]);

        // Change text color based on availability
        String availability = doctorAvailabilities[position];
        if ("Not Available".equalsIgnoreCase(availability)) {
            holder.doctorAvailability.setTextColor(Color.RED);
        } else {
            holder.doctorAvailability.setTextColor(Color.parseColor("#0EBE7F"));
        }

        // Open details page on item click
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, Details.class);
            intent.putExtra("doctorName", doctorNames[position]);
            intent.putExtra("doctorSpecialty", doctorSpecialties[position]);
            intent.putExtra("doctorImage", doctorImages[position]);
            intent.putExtra("doctorRating", doctorRatings[position]);
            intent.putExtra("doctorLocation", doctorLocations[position]);
            context.startActivity(intent);
        });

        // Book button
        holder.scheduleButton.setOnClickListener(view -> {
            Intent intent = new Intent(context, BookAppointmen.class);
            intent.putExtra("doctorName", doctorNames[position]);
            intent.putExtra("doctorSpecialty", doctorSpecialties[position]);
            intent.putExtra("doctorImage", doctorImages[position]);
            intent.putExtra("doctorRating", doctorRatings[position]);
            intent.putExtra("doctorLocation", doctorLocations[position]);
            context.startActivity(intent);
        });

        // Instant button
        holder.instantButton.setOnClickListener(view -> {
            Intent intent = new Intent(context, InstantAppointment.class);
            intent.putExtra("doctorName", doctorNames[position]);
            intent.putExtra("doctorSpecialty", doctorSpecialties[position]);
            intent.putExtra("doctorImage", doctorImages[position]);
            intent.putExtra("doctorRating", doctorRatings[position]);
            intent.putExtra("doctorLocation", doctorLocations[position]);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return doctorNames.length;
    }

    public static class DoctorViewHolder extends RecyclerView.ViewHolder {

        ImageView doctorImage;
        TextView doctorName, doctorSpecialty, doctorAvailability, pendingPatients;
        RatingBar doctorRating;
        Button scheduleButton, instantButton;

        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorImage = itemView.findViewById(R.id.doctor_image);
            doctorName = itemView.findViewById(R.id.doctor_name);
            doctorSpecialty = itemView.findViewById(R.id.doctor_specialty);
            doctorAvailability = itemView.findViewById(R.id.doctor_availability);
            pendingPatients = itemView.findViewById(R.id.pending_patients);
            doctorRating = itemView.findViewById(R.id.doctor_rating);
            scheduleButton = itemView.findViewById(R.id.schedule_button);
            instantButton = itemView.findViewById(R.id.instant_button);
        }
    }
}
