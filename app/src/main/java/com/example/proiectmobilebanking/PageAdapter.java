package com.example.proiectmobilebanking;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {

    private int noTabs;
    public PageAdapter(FragmentManager fm,int numTabs) {
        super(fm);
        this.noTabs=numTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FeedbackFragment();
            case 1:
                return new AboutFragment();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return noTabs;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
