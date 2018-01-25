package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

/**
 * Created by Pithou on 04/04/2016.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapterTodayEvents extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public PagerAdapterTodayEvents(FragmentManager fm) {
        super(fm);
        this.fragments = new ArrayList<Fragment>();
        fragments.add(new FragmentListOfDayEvent(0));
        fragments.add(new FragmentListOfDayEvent(1));
        fragments.add(new FragmentListOfDayEvent(2));
        fragments.add(new FragmentListOfDayEvent(3));
        fragments.add(new FragmentListOfDayEvent(4));
        fragments.add(new FragmentListOfDayEvent(5));
        fragments.add(new FragmentListOfDayEvent(6));

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
