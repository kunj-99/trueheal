package com.infowave.trueheal;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.infowave.trueheal.adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    ImageButton heartIcon;
    TextView toolbarTitle;
    Button logoutButton;
    ViewPager vp;
    BottomNavigationView btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.tb);
        navigationView = findViewById(R.id.nev_view);
        heartIcon = findViewById(R.id.heart_icon);
        toolbarTitle = findViewById(R.id.toolbar_title);
        logoutButton = navigationView.findViewById(R.id.logout_button);
        vp = findViewById(R.id.vp);
        btn = findViewById(R.id.bottem);

        // Set up the toolbar
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        // Drawer Toggle (Hamburger icon)
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Navigation item selection listener
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // TODO: Handle item selection here as needed
                drawerLayout.closeDrawers();
                return true;
            }
        });

        // Heart icon click
        heartIcon.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, WishList.class));
            Toast.makeText(MainActivity.this, "Heart Icon Clicked", Toast.LENGTH_SHORT).show();
        });

        // Logout button click
        logoutButton.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Logout successful", Toast.LENGTH_SHORT).show();
            // TODO: Implement actual logout logic
        });

        // Set up the ViewPager with the Adapter
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(adapter);

        // Set BottomNavigationView item selection listener
        btn.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.page_1) {
                    vp.setCurrentItem(0);
                    return true;
                } else if (id == R.id.page_2) {
                    vp.setCurrentItem(1);
                    return true;
                } else if (id == R.id.page_3) {
                    vp.setCurrentItem(2);
                    return true;
                } else if (id == R.id.page_4) {
                    vp.setCurrentItem(3);
                    return true;
                }
                return false;
            }
        });

        // Add PageChangeListener to update BottomNavigationView when page is swiped
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        btn.setSelectedItemId(R.id.page_1);
                        break;
                    case 1:
                        btn.setSelectedItemId(R.id.page_2);
                        break;
                    case 2:
                        btn.setSelectedItemId(R.id.page_3);
                        break;
                    case 3:
                        btn.setSelectedItemId(R.id.page_4);
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }
}
