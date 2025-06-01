package com.infowave.trueheal.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infowave.trueheal.R;

public class HospitalSearchAdapter extends RecyclerView.Adapter<HospitalSearchAdapter.ViewHolder> {

    private int[] hospitalImages;
    private String[] hospitalNames;

    // Constructor
    public HospitalSearchAdapter(int[] images, String[] names) {
        this.hospitalImages = images;
        this.hospitalNames = names;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Use your item layout, e.g., item_hospital_search.xml
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hospital_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.hospitalImage.setImageResource(hospitalImages[position]);
        holder.hospitalName.setText(hospitalNames[position]);
    }

    @Override
    public int getItemCount() {
        return hospitalImages.length;
    }

    // ViewHolder for a hospital card/item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView hospitalImage;
        TextView hospitalName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hospitalImage = itemView.findViewById(R.id.img);     // image view ID
            hospitalName = itemView.findViewById(R.id.txt1);     // text view ID
        }
    }
}
