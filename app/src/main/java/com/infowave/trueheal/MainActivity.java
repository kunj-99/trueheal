package com.infowave.trueheal;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.WindowCompat;
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
        WindowCompat.setDecorFitsSystemWindows(getWindow(), true);
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
        android.view.View headerView = navigationView.getHeaderView(0);
        LinearLayout profilePage = headerView.findViewById(R.id.profile_Page);

        profilePage.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Profile.class); // Change ProfileActivity.class to your activity!
            startActivity(intent);
        });


        // Navigation item selection listener
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                // TODO: Handle item selection here as needed

                int id = item.getItemId();


                if (id == R.id.payments1) {
                    // Handle "Payments" click
                    Intent intent = new Intent(MainActivity.this, Payment.class);
                    startActivity(intent);
                }

                 else if (id == R.id.records1) {
                    // Handle "Medical Records" click
                    Intent intent = new Intent(MainActivity.this, MedicalRecord.class);
                    startActivity(intent);
                }

                 else if (id == R.id.policy) {
                    // Handle "Privacy Policy" click
                    Intent intent = new Intent(MainActivity.this , Policy.class);
                    startActivity(intent);
                }


                else if (id == R.id.support1) {
                    // Handle "Support" click
                    Intent intent = new Intent(MainActivity.this, Support.class);
                    startActivity(intent);
                }

                else if (id == R.id.shareapp1) {
                    // Handle "Share App" click
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    String shareBody = "Check out this awesome app: " + getPackageName();
                    String shareSubject = "Try this amazing app!";
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);

                    startActivity(Intent.createChooser(shareIntent, "Share via"));
                }



                else if (id == R.id.aboutus1) {
                    // Handle "About Us" click
                    Intent intent = new Intent(MainActivity.this, AboutUs.class);
                    startActivity(intent);
                }

                else if (id == R.id.settings1) {
                    // Handle "Settings" click
                    Intent intent = new Intent(MainActivity.this, Settings.class);
                    startActivity(intent);
                }


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

            @SuppressLint("SetTextI18n")
            @Override
            public void onPageSelected(int position) {
                // Change the toolbar title as you swipe pages
                switch (position) {
                    case 0:
                        toolbarTitle.setText("Home");
                        break;
                    case 1:
                        toolbarTitle.setText("Search");
                        break;
                    case 2:
                        toolbarTitle.setText("Appointments");
                        break;
                    case 3:
                        toolbarTitle.setText("History");
                        break;
                }
                // Update BottomNavigationView as before
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
