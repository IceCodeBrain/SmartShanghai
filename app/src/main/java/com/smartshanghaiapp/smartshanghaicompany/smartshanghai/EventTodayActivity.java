package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper.InterfaceTodayEventsAPI;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfEvents;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class EventTodayActivity extends FragmentActivity {

    public static int currentPosition = 0;


    private PagerAdapterTodayEvents mPageAdapterTodayEvents;

    private Button mButDay1;
    private Button mButDay2;
    private Button mButDay3;
    private Button mButDay4;
    private Button mButDay5;
    private Button mButDay6;
    private Button mButDay7;

    private TextView mTvDay1;
    private TextView mTvDay2;
    private TextView mTvDay3;
    private TextView mTvDay4;
    private TextView mTvDay5;
    private TextView mTvDay6;
    private TextView mTvDay7;

    private ArrayList<String> mArrayListOfStringDays;

    private TextView mTvDayNumber1;
    private TextView mTvDayNumber2;
    private TextView mTvDayNumber3;
    private TextView mTvDayNumber4;
    private TextView mTvDayNumber5;
    private TextView mTvDayNumber6;
    private TextView mTvDayNumber7;

    private ArrayList<TextView> mArrayListOfTextViewDays;

    private RelativeLayout mRlDay1;
    private RelativeLayout mRlDay2;
    private RelativeLayout mRlDay3;
    private RelativeLayout mRlDay4;
    private RelativeLayout mRlDay5;
    private RelativeLayout mRlDay6;
    private RelativeLayout mRlDay7;

    private ArrayList<RelativeLayout> mArrayListOfRelativeLayoutsDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_event);


        mPageAdapterTodayEvents = new PagerAdapterTodayEvents(getSupportFragmentManager());
        final ViewPager pagerTodayEvents = (ViewPager) findViewById(R.id.myViewPager_events_today);
        pagerTodayEvents.setAdapter(mPageAdapterTodayEvents);


        mArrayListOfRelativeLayoutsDays = new ArrayList<RelativeLayout>();
        mArrayListOfRelativeLayoutsDays.add(mRlDay1 = (RelativeLayout) findViewById(R.id.rl_event_today_day1_number));
        mArrayListOfRelativeLayoutsDays.add(mRlDay2 = (RelativeLayout) findViewById(R.id.rl_event_today_day2_number));
        mArrayListOfRelativeLayoutsDays.add(mRlDay3 = (RelativeLayout) findViewById(R.id.rl_event_today_day3_number));
        mArrayListOfRelativeLayoutsDays.add(mRlDay4 = (RelativeLayout) findViewById(R.id.rl_event_today_day4_number));
        mArrayListOfRelativeLayoutsDays.add(mRlDay5 = (RelativeLayout) findViewById(R.id.rl_event_today_day5_number));
        mArrayListOfRelativeLayoutsDays.add(mRlDay6 = (RelativeLayout) findViewById(R.id.rl_event_today_day6_number));
        mArrayListOfRelativeLayoutsDays.add(mRlDay7 = (RelativeLayout) findViewById(R.id.rl_event_today_day7_number));


        mArrayListOfTextViewDays = new ArrayList<TextView>();
        mArrayListOfTextViewDays.add(mTvDayNumber1 = (TextView) findViewById(R.id.tv_event_today_day1_number));
        mArrayListOfTextViewDays.add(mTvDayNumber2 = (TextView) findViewById(R.id.tv_event_today_day2_number));
        mArrayListOfTextViewDays.add(mTvDayNumber3 = (TextView) findViewById(R.id.tv_event_today_day3_number));
        mArrayListOfTextViewDays.add(mTvDayNumber4 = (TextView) findViewById(R.id.tv_event_today_day4_number));
        mArrayListOfTextViewDays.add(mTvDayNumber5 = (TextView) findViewById(R.id.tv_event_today_day5_number));
        mArrayListOfTextViewDays.add(mTvDayNumber6 = (TextView) findViewById(R.id.tv_event_today_day6_number));
        mArrayListOfTextViewDays.add(mTvDayNumber7 = (TextView) findViewById(R.id.tv_event_today_day7_number));
        setBackGroundColorOfDays(0);



        mButDay1 = (Button) findViewById(R.id.but_events_today_1);
        mButDay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagerTodayEvents.setCurrentItem(0);
                setBackGroundColorOfDays(0);
            }
        });

        mButDay2 = (Button) findViewById(R.id.but_events_today_2);
        mButDay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagerTodayEvents.setCurrentItem(1);
                setBackGroundColorOfDays(1);

            }
        });

        mButDay3 = (Button) findViewById(R.id.but_events_today_3);
        mButDay3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagerTodayEvents.setCurrentItem(2);
                setBackGroundColorOfDays(2);

            }
        });


        mButDay4 = (Button) findViewById(R.id.but_events_today_4);
        mButDay4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagerTodayEvents.setCurrentItem(3);
                setBackGroundColorOfDays(3);

            }
        });

        mButDay5 = (Button) findViewById(R.id.but_events_today_5);
        mButDay5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagerTodayEvents.setCurrentItem(4);
                setBackGroundColorOfDays(4);

            }
        });

        mButDay6 = (Button) findViewById(R.id.but_events_today_6);
        mButDay6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagerTodayEvents.setCurrentItem(5);
                setBackGroundColorOfDays(5);

            }
        });


        mButDay7 = (Button) findViewById(R.id.but_events_today_7);
        mButDay7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagerTodayEvents.setCurrentItem(6);
                setBackGroundColorOfDays(6);

            }
        });

        declareAndSetTextViewsWithDate(); // declare all the textviews for the dates and set MON /TUE ...


        pagerTodayEvents.setOnPageChangeListener(pageChangeListener);


    }


    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {


        @Override
        public void onPageSelected(int newPosition) {

            setBackGroundColorOfDays(newPosition);

            FragmentLifeCycle fragmentToHide = (FragmentLifeCycle) mPageAdapterTodayEvents.getItem(currentPosition);
            fragmentToHide.onPauseFragment();

            FragmentLifeCycle fragmentToShow = (FragmentLifeCycle) mPageAdapterTodayEvents.getItem(newPosition);
            fragmentToShow.onResumeFragment();

            currentPosition = newPosition;
        }


        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        public void onPageScrollStateChanged(int arg0) {
        }
    };

    public void declareAndSetTextViewsWithDate() {
        Calendar calendar = Calendar.getInstance();
        mArrayListOfStringDays = new ArrayList<String>();


        Date day1 = calendar.getTime();
        mTvDay1 = (TextView) findViewById(R.id.tv_event_today_day1);
        mTvDay1.setText(day1.toString().substring(0, 3));
        mTvDayNumber1.setText(day1.toString().substring(8, 10));

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date day2 = calendar.getTime();
        mTvDay2 = (TextView) findViewById(R.id.tv_event_today_day2);
        mTvDay2.setText(day2.toString().substring(0, 3));
        mTvDayNumber2.setText(day2.toString().substring(8, 10));


        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date day3 = calendar.getTime();
        mTvDay3 = (TextView) findViewById(R.id.tv_event_today_day3);
        mTvDay3.setText(day3.toString().substring(0, 3));

        mTvDayNumber3.setText(day3.toString().substring(8, 10));


        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date day4 = calendar.getTime();
        mTvDay4 = (TextView) findViewById(R.id.tv_event_today_day4);
        mTvDay4.setText(day4.toString().substring(0, 3));

        mTvDayNumber4.setText(day4.toString().substring(8, 10));


        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date day5 = calendar.getTime();
        mTvDay5 = (TextView) findViewById(R.id.tv_event_today_day5);
        mTvDay5.setText(day5.toString().substring(0, 3));

        mTvDayNumber5.setText(day5.toString().substring(8, 10));


        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date day6 = calendar.getTime();
        mTvDay6 = (TextView) findViewById(R.id.tv_event_today_day6);
        mTvDay6.setText(day6.toString().substring(0, 3));

        mTvDayNumber6.setText(day6.toString().substring(8, 10));


        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date day7 = calendar.getTime();
        mTvDay7 = (TextView) findViewById(R.id.tv_event_today_day7);
        mTvDay7.setText(day7.toString().substring(0, 3));

        mTvDayNumber7.setText(day7.toString().substring(8, 10));

    }

    public void setBackGroundColorOfDays(int numberOfTheDay) {

        for (int i = 0; i < mArrayListOfRelativeLayoutsDays.size(); i++) {
            if (i == numberOfTheDay) {
                mArrayListOfRelativeLayoutsDays.get(i).setBackgroundResource(R.drawable.tempbackgroundnumberday);
                mArrayListOfTextViewDays.get(i).setTextColor(getResources().getColor(R.color.colorSmartShanghaiBlue));
            } else {
                mArrayListOfRelativeLayoutsDays.get(i).setBackgroundResource(R.color.colorTransparant);
                mArrayListOfTextViewDays.get(i).setTextColor(getResources().getColor(R.color.colorWhite));
            }
        }
    }

}

