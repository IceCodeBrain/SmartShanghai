package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

/**
 * Created by Pithou on 04/04/2016.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelPictureOfGallery;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapterPicturesFullScreen extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private ArrayList<ModelPictureOfGallery> listOfPics;

    public PagerAdapterPicturesFullScreen(FragmentManager fm, ArrayList<ModelPictureOfGallery> listOfPics) {
        super(fm);
        this.listOfPics = listOfPics;
        this.fragments = new ArrayList<Fragment>();
        for (int i = 0 ; i < listOfPics.size() ; i++){
            fragments.add(new FragmentPictureOfGalleryFullScreen(listOfPics.get(i).getSmartchinaid()));
        }


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
