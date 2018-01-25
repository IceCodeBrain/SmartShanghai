package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

/**
 * Created by Pithou on 04/04/2016.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapterFirstRun extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public PagerAdapterFirstRun(FragmentManager fm) {
        super(fm);
        this.fragments = new ArrayList<Fragment>();
        fragments.add(new FragmentFirstRun1());
        fragments.add(new FragmentFirstRun2());
        fragments.add(new FragmentFirstRun3());
        fragments.add(new FragmentFirstRun4());


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
