package com.infowave.trueheal.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infowave.trueheal.R;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.Inner> {

    private int[] img;
    private String[] names;
    private String[] specialties;
    private String[] experiences;
    private String[] availabilities;
    private String[] pendingPatients;

    // Constructor
    public WishListAdapter(int[] img, String[] names, String[] specialties, String[] experiences, String[] availabilities, String[] pendingPatients) {
        this.img = img;
        this.names = names;
        this.specialties = specialties;
        this.experiences = experiences;
        this.availabilities = availabilities;
        this.pendingPatients = pendingPatients;
    }

    @NonNull
    @Override
    public Inner onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wishlist, parent, false);
        return new Inner(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Inner holder, int position) {
        // Set data for each view in the item
        holder.doctorImage.setImageResource(img[position]);
        holder.doctorName.setText(names[position]);
        holder.doctorSpecialty.setText(specialties[position]);
        holder.doctorExperience.setText(experiences[position]);
        holder.doctorAvailability.setText(availabilities[position]);
        holder.pendingPatients.setText(pendingPatients[position]);
    }

    @Override
    public int getItemCount() {
        return names.length; // Use the length of names array
    }

    public class Inner extends RecyclerView.ViewHolder {

        ImageView doctorImage;
        TextView doctorName;
        TextView doctorSpecialty;
        TextView doctorExperience;
        TextView doctorAvailability;
        TextView pendingPatients;
        public Inner(@NonNull View itemView) {
            super(itemView);

            doctorImage = itemView.findViewById(R.id.doctor_image);
            doctorName = itemView.findViewById(R.id.doctor_name);
            doctorSpecialty = itemView.findViewById(R.id.doctor_specialty);
            doctorExperience = itemView.findViewById(R.id.doctor_experience);
            doctorAvailability = itemView.findViewById(R.id.doctor_availability);
            pendingPatients = itemView.findViewById(R.id.pending_patients);
        }
    }
}
