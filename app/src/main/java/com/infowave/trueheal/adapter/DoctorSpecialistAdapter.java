package com.infowave.trueheal.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infowave.trueheal.R;

public class DoctorSpecialistAdapter extends RecyclerView.Adapter<DoctorSpecialistAdapter.SpecialistViewHolder> {

    private int[] specialistImages;
    private String[] specialistNames;

    public DoctorSpecialistAdapter(int[] specialistImages, String[] specialistNames) {
        this.specialistImages = specialistImages;
        this.specialistNames = specialistNames;
    }

    @NonNull
    @Override
    public SpecialistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doctorspecialist, parent, false);
        return new SpecialistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialistViewHolder holder, int position) {
        holder.specialistImage.setImageResource(specialistImages[position]);
        holder.specialistName.setText(specialistNames[position]);
    }

    @Override
    public int getItemCount() {
        return specialistNames.length;
    }

    public static class SpecialistViewHolder extends RecyclerView.ViewHolder {
        ImageView specialistImage;
        TextView specialistName;

        public SpecialistViewHolder(@NonNull View itemView) {
            super(itemView);
            specialistImage = itemView.findViewById(R.id.specialist_image);
            specialistName = itemView.findViewById(R.id.specialist_name);
        }
    }
}
