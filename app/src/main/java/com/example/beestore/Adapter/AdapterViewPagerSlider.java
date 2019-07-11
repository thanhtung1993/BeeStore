package com.example.beestore.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class AdapterViewPagerSlider extends FragmentPagerAdapter {
    List<Fragment> fragments;


    public AdapterViewPagerSlider(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        fragments=fragmentList;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
