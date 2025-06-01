package com.infowave.trueheal.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

// Import your fragments here:
import com.infowave.trueheal.fragment.Home;
import com.infowave.trueheal.fragment.Appointment;
import com.infowave.trueheal.fragment.Profile;
import com.infowave.trueheal.fragment.Search;;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Home();
            case 1:
                return new Search();
            case 2:
                return new Appointment();
            default:
                return new Profile();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Home";
            case 1:
                return "Search";
            case 2:
                return "Appointment";
            default:
                return "Profile";
        }
    }
}
