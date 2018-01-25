package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.DatabaseDirectory.Database;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage.FragmentDirectory;

import java.io.IOException;
import java.util.ArrayList;

public class FirstRunActivity extends FragmentActivity {

    private SharedPreferences mPreferences;

    private Database mDatabase;

    private PagerAdapterFirstRun pageAdapter;

    private ArrayList<ImageView> mIndicators;
    private ImageView mIndicator1;
    private ImageView mIndicator2;
    private ImageView mIndicator3;
    private ImageView mIndicator4;

    ViewPager mPager = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_run);


        pageAdapter = new PagerAdapterFirstRun(getSupportFragmentManager());
        mPager = (ViewPager) findViewById(R.id.myViewPagerFirstRun);
        mPager.setAdapter(pageAdapter);

        mPager.setOnPageChangeListener(pageChangeListener);

        mIndicator1 = (ImageView) findViewById(R.id.iv_first_run_indacator1);
        mIndicator2 = (ImageView) findViewById(R.id.iv_first_run_indacator2);
        mIndicator3 = (ImageView) findViewById(R.id.iv_first_run_indacator3);
        mIndicator4 = (ImageView) findViewById(R.id.iv_first_run_indacator4);
        mIndicators = new ArrayList<ImageView>();
        mIndicators.add(mIndicator1);
        mIndicators.add(mIndicator2);
        mIndicators.add(mIndicator3);
        mIndicators.add(mIndicator4);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean("firstrun", false);
        editor.commit();

        checkDatabase(this);

    }

    private void checkDatabase(Context ctx) {
        mDatabase = new Database(ctx);
        try {
            mDatabase.copyDataBaseFromAsset(ctx);
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putBoolean("dbIsUsable", true);
            editor.apply();
        } catch (IOException e) {
            e.printStackTrace();
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putBoolean("dbIsUsable", false);
            editor.apply();
        }
    }


    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {

        int currentPosition = 0;


        @Override
        public void onPageSelected(int newPosition) {


            FragmentLifeCycle fragmentToHide = (FragmentLifeCycle) pageAdapter.getItem(currentPosition);
            fragmentToHide.onPauseFragment();

            FragmentLifeCycle fragmentToShow = (FragmentLifeCycle) pageAdapter.getItem(newPosition);
            fragmentToShow.onResumeFragment();

            currentPosition = newPosition;

            mIndicator1.setImageResource(R.drawable.ic_dots_white);
            mIndicator2.setImageResource(R.drawable.ic_dots_white);
            mIndicator3.setImageResource(R.drawable.ic_dots_white);
            mIndicator4.setImageResource(R.drawable.ic_dots_white);
            mIndicators.get(currentPosition).setImageResource(R.drawable.ic_dots_blue);

        }


        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        public void onPageScrollStateChanged(int arg0) {
        }
    };



}
