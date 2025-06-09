package com.infowave.trueheal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Details extends AppCompatActivity {

    private Button viewMapBtn;
    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        viewMapBtn = findViewById(R.id.btn_view_map);
        backBtn = findViewById(R.id.btn_back);

        viewMapBtn.setOnClickListener(v -> {
            String mapQuery = "geo:0,0?q=True Heal Hospital Rajkot";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mapQuery));
            intent.setPackage("com.google.android.apps.maps");
            startActivity(intent);
        });

        backBtn.setOnClickListener(v -> finish());
    }
}
