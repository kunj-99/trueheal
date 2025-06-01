package com.infowave.trueheal.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infowave.trueheal.R;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.ImageViewHolder> {

    private int[] imageList;

    // Constructor to receive the image array
    public SliderAdapter(int[] imageList) {
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate your item layout (e.g., item_slider.xml)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.imageView.setImageResource(imageList[position]);
    }

    @Override
    public int getItemCount() {
        return imageList.length;
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            // Use the ID from your item_slider.xml
            imageView = itemView.findViewById(R.id.sliderImage);
        }
    }
}
