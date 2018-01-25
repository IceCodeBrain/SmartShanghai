package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper.InterfaceTodayEventsAPI;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelEvent;
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


public class FragmentListOfDayEvent extends Fragment implements FragmentLifeCycle, Callback<ModelListOfEvents> {


    private ArrayList<ModelEvent> mEvents;
    private ModelListOfEvents mListOfEvents;
    private ListView mEventsListview;
    private String mDateSelected;
    private int mPosition;
    private ArrayList<String> mArrayListOfDays;

    private ListOfEventsAdapter mAdapterGeneral;

    private LinearLayout llNoEventToday;

    private ProgressBar mProgressBarListOfEvents;

    public final static String KEY_BUNDLE_DAY_EVENT = "no event";


    public final static String KEY_BUNDLE_WEB_ADDRESS = "Key_bundle_web_address";
    public final static String KEY_BUNDLE_EVENT_CAT_TODAY = "key_bundle_event_cat";



    public FragmentListOfDayEvent() {
        mPosition = 0;
    }


    public FragmentListOfDayEvent(int position) {
        mPosition = position;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceStaet) {


        View view = inflater.inflate(R.layout.fragment_list_of_day_event, container, false);

        mProgressBarListOfEvents = (ProgressBar) view.findViewById(R.id.prog_bar_today_event);
        llNoEventToday = (LinearLayout) view.findViewById(R.id.ll_list_of_event_per_day_error_no_event);
        mProgressBarListOfEvents.setVisibility(View.VISIBLE);
        mEvents = new ArrayList<ModelEvent>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        mArrayListOfDays = new ArrayList<String>();

        Calendar calendar = Calendar.getInstance();
        Date day0 = calendar.getTime();
        mArrayListOfDays.add(sdf.format(day0));
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date day1 = calendar.getTime();
        mArrayListOfDays.add(sdf.format(day1));
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date day2 = calendar.getTime();
        mArrayListOfDays.add(sdf.format(day2));
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date day3 = calendar.getTime();
        mArrayListOfDays.add(sdf.format(day3));
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date day4 = calendar.getTime();
        mArrayListOfDays.add(sdf.format(day4));
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date day5 = calendar.getTime();
        mArrayListOfDays.add(sdf.format(day5));
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date day6 = calendar.getTime();
        mArrayListOfDays.add(sdf.format(day6));


        mDateSelected = mArrayListOfDays.get(mPosition);


        if (mDateSelected != null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(" http://www.smartshanghai.com/api/events")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            InterfaceTodayEventsAPI api = retrofit.create(InterfaceTodayEventsAPI.class);

            Call<ModelListOfEvents> callListOfEvents = api.getEventsListToday("?date=" + getFormatedDate(mDateSelected) + "&key=abc123iphone");
            callListOfEvents.enqueue(this);
        }

        mEventsListview = (ListView) view.findViewById(R.id.lv_fragment_today_event);

        mEventsListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(getActivity(), EventActivity.class);
                i.putExtra(KEY_BUNDLE_DAY_EVENT, mEvents.get(position));
                i.putExtra(KEY_BUNDLE_EVENT_CAT_TODAY, "event_today");
                startActivity(i);

            }
        });

        return view;
    }


    @Override
    public void onPauseFragment() {

    }


    @Override
    public void onResumeFragment() {

    }

    @Override
    public void onBackPressed() {

    }


    @Override
    public void onResponse(Response<ModelListOfEvents> response, Retrofit retrofit) {
        mProgressBarListOfEvents.setVisibility(View.GONE);
        response.body();

        if (response.body().data != null) {
            for (int i = 0; i < response.body().data.size(); i++) {
                mEvents.add(new ModelEvent(
                        response.body().data.get(i).name,
                        response.body().data.get(i).date_start,
                        response.body().data.get(i).date_end,
                        response.body().data.get(i).description_short_nohtml,
                        response.body().data.get(i).prevpix,
                        response.body().data.get(i).venue_id,
                        response.body().data.get(i).venue_name,
                        response.body().data.get(i).smartticket_link,
                        response.body().data.get(i).date_featured,
                        response.body().data.get(i).date_human,
                        response.body().data.get(i).map_x,
                        response.body().data.get(i).map_y,
                        response.body().data.get(i).getFlyer(),
                        response.body().data.get(i).getId()
                ));
            }
            if (getActivity() != null) {
                mAdapterGeneral = new ListOfEventsAdapter(mEvents, getActivity(), false, "", "", false);
                mEventsListview.setAdapter(mAdapterGeneral);
            }

        }else if ( response.body().data == null){
            llNoEventToday.setVisibility(View.VISIBLE);
            mEventsListview.setVisibility(View.GONE);
        }
    }

    @Override
    public void onFailure(Throwable t) {

        mProgressBarListOfEvents.setVisibility(View.GONE);


    }


    public String getFormatedDate(String dateNonFormated) {

        String year, month, day;
        String returnDate;
        year = dateNonFormated.substring(0, 4);
        month = dateNonFormated.substring(4, 6);
        day = dateNonFormated.substring(6, 8);
        returnDate = year + "-" + month + "-" + day;
        return returnDate;

    }


}


