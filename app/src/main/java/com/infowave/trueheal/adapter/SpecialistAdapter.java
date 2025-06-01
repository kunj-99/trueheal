package com.infowave.trueheal.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infowave.trueheal.R;

/**
 * Adapter for displaying a grid of specialists with image and name.
 */
public class SpecialistAdapter extends RecyclerView.Adapter<SpecialistAdapter.ViewHolder> {

    private int[] specialistImages;
    private String[] specialistNames;

    // Adapter constructor
    public SpecialistAdapter(int[] images, String[] names) {
        this.specialistImages = images;
        this.specialistNames = names;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Use your specialist item layout, e.g., item_specialist.xml
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_specialist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.specialistImage.setImageResource(specialistImages[position]);
        holder.specialistName.setText(specialistNames[position]);
    }

    @Override
    public int getItemCount() {
        return specialistImages.length;
    }

    // ViewHolder for a specialist card/item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView specialistImage;
        TextView specialistName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            specialistImage = itemView.findViewById(R.id.img);
            specialistName = itemView.findViewById(R.id.txt1);
        }
    }
}
