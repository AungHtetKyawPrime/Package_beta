package com.example.package_beta.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.package_beta.DetailTab.Description;
import com.example.package_beta.DetailTab.DoDont;
import com.example.package_beta.DetailTab.Location;
import com.example.package_beta.DetailTab.Photos;

public class DetailTabAdapter extends FragmentStatePagerAdapter {
    int NumOfTabs;
    public DetailTabAdapter(FragmentManager fm,int NumOfTabs) {
        super(fm);
        this.NumOfTabs=NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Description tab1 = new Description();
                return tab1;
            case 1:
                Location tab2 = new Location();
                return tab2;
            case 2:
                Photos tab3 = new Photos();
                return tab3;
            case 3:
                DoDont tab4 = new DoDont();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NumOfTabs;
    }
}
