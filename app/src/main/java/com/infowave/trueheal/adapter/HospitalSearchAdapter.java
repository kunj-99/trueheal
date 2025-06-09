package com.infowave.trueheal.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infowave.trueheal.HospitalDoctorsActivity;
import com.infowave.trueheal.R;

public class HospitalSearchAdapter extends RecyclerView.Adapter<HospitalSearchAdapter.ViewHolder> {

    private Context context;
    private int[] hospitalImages;
    private String[] hospitalNames;

    public HospitalSearchAdapter(Context context, int[] images, String[] names) {
        this.context = context;
        this.hospitalImages = images;
        this.hospitalNames = names;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hospital_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.hospitalImage.setImageResource(hospitalImages[position]);
        holder.hospitalName.setText(hospitalNames[position]);

        // Click listener to open doctors in hospital
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, HospitalDoctorsActivity.class);
            intent.putExtra("hospital_name", hospitalNames[position]);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return hospitalImages.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView hospitalImage;
        TextView hospitalName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hospitalImage = itemView.findViewById(R.id.img);
            hospitalName = itemView.findViewById(R.id.txt1);
        }
    }
}
