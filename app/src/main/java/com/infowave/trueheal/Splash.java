package com.infowave.trueheal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        Handler n = new Handler();
        n.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent in = new Intent(Splash.this, SignUp.class);
                startActivity(in);
                finish();
            }
        },2500);

    }
}