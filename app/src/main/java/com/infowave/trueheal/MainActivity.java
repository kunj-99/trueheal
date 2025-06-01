package com.infowave.trueheal;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.infowave.trueheal.adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    ViewPager vp;
    BottomNavigationView btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vp = findViewById(R.id.vp);
        btn = findViewById(R.id.bottem);

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
