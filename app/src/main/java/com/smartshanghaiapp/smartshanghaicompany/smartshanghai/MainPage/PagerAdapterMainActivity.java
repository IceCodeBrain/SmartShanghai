package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage;

/**
 * Created by Pithou on 04/04/2016.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import java.util.ArrayList;
import java.util.List;

public class PagerAdapterMainActivity extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public PagerAdapterMainActivity(FragmentManager fm) {
        super(fm);
        this.fragments = new ArrayList<Fragment>();
        fragments.add(new FragmentFeed());
        fragments.add(new FragmentDirectory());
        fragments.add(new FragmentEvents());
        fragments.add(new FragmentTickets());
        fragments.add(new FragmentMore());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


}
