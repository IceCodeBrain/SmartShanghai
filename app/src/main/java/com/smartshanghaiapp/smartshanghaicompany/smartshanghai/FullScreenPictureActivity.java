package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelPictureOfGallery;

import java.util.ArrayList;

public class FullScreenPictureActivity extends FragmentActivity {


    private PagerAdapterPicturesFullScreen mPageAdapterPicturesFullScreen;

    public static int currentPosition = -1; // maybe change here to arrive on the right postition

    private ArrayList<ModelPictureOfGallery> mArrayListOfPicGallery;

    private String currentPositionString;
    private int mIdPictureSelectedInt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_picture_view_pager);

        mArrayListOfPicGallery = new ArrayList<ModelPictureOfGallery>();


        mArrayListOfPicGallery = getIntent().getExtras().getParcelableArrayList(GalleryActivity.KEY_BUNDLE_PICTURE_LIST_IMAGES);
        mIdPictureSelectedInt =  getIntent().getExtras().getInt(GalleryActivity.KEY_BUNDLE_PICTURE_SELECTED_POSITION);

        mPageAdapterPicturesFullScreen = new PagerAdapterPicturesFullScreen(getSupportFragmentManager(), mArrayListOfPicGallery);
        final ViewPager pagerFullScreenActivity = (ViewPager) findViewById(R.id.vp_activity_full_screen_picture);
        pagerFullScreenActivity.setAdapter(mPageAdapterPicturesFullScreen);

        currentPosition = mIdPictureSelectedInt;

        pagerFullScreenActivity.setCurrentItem(currentPosition);




    }


    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {


        @Override
        public void onPageSelected(int newPosition) {


            FragmentLifeCycle fragmentToHide = (FragmentLifeCycle) mPageAdapterPicturesFullScreen.getItem(currentPosition);
            fragmentToHide.onPauseFragment();

            FragmentLifeCycle fragmentToShow = (FragmentLifeCycle) mPageAdapterPicturesFullScreen.getItem(newPosition);
            fragmentToShow.onResumeFragment();

            currentPosition = newPosition;
        }


        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        public void onPageScrollStateChanged(int arg0) {
        }
    };


}
