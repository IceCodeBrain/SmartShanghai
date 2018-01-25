package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.DraweeView;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper.InterfaceEventsAPI;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelEvent;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfEvents;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ListOfEvent extends Activity implements Callback<ModelListOfEvents> {

    public final static String KEY_BUNDLE_EVENT = "no event";
    public final static String KEY_BUNDLE_EVENT_CAT = "key_bundle_event_cat";


    private ArrayList<ModelEvent> mEvents;
    private ModelListOfEvents modelListOfEvents;
    private ListView mEventsListview;
    private ListOfEventsAdapter mAdapterGeneral;
    private ListOfEventsArtExhibitionAdapter mAdapterArtExhibition;
    private String cat;
    private TextView mTvCategorieEvent;
    private ProgressBar mProgressBarListOfEvents;

    private DraweeView mDvImageListOfEvent;

    private LinearLayout mRlTopBarCatGenerale;
    private RelativeLayout mRlTopBarCatArtExhibition;

    private LinearLayout mLlNoEvents;

    private Intent mIntent;
    private String mComeFrom;
    private String toCall;

    private boolean fromFeed;
    private String urlEventFeed;
    private String mTitleEventFeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_event);


        mTvCategorieEvent = (TextView) findViewById(R.id.tv_list_of_events_cat);

        mComeFrom = "nothing selected";
        mIntent = getIntent();

        mComeFrom = mIntent.getStringExtra("EVENTS_FROM");
        cat = mIntent.getStringExtra("topText");
        toCall = mIntent.getStringExtra("toCall");


        mRlTopBarCatGenerale = (LinearLayout) findViewById(R.id.rl_list_of_event_top_bar_cat_generale);
        mRlTopBarCatArtExhibition = (RelativeLayout) findViewById(R.id.rl_list_of_event_top_bar_cat_art_exhibition);
        mLlNoEvents = (LinearLayout) findViewById(R.id.ll_list_of_event_error_no_event);
        mProgressBarListOfEvents = (ProgressBar) findViewById(R.id.load_activity_list_of_events);

        if (mComeFrom.equals("feed_events")) {
            fromFeed = true;
            urlEventFeed = mIntent.getStringExtra("imageFeedEvent");
            mTitleEventFeed = mIntent.getStringExtra("titleFeedEvent");
        }

        if (mComeFrom != null && !mComeFrom.equals("")) {
            callTheEvents(toCall);
            if (!toCall.equals("artExhibitions")) {
                mRlTopBarCatGenerale.setVisibility(View.VISIBLE);
                mRlTopBarCatArtExhibition.setVisibility(View.GONE);
                mTvCategorieEvent.setText(cat);
            } else {
                mRlTopBarCatArtExhibition.setVisibility(View.VISIBLE);
                mRlTopBarCatGenerale.setVisibility(View.GONE);
            }
        }


        mEventsListview = (ListView) findViewById(R.id.lv_list_of_events);

        mEventsListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (fromFeed) {
                    if (position == 0) {

                    } else {
                        ModelEvent eventSelected = mEvents.get(position - 1);
                        Intent i = new Intent(ListOfEvent.this, EventActivity.class);
                        i.putExtra(KEY_BUNDLE_EVENT, eventSelected);
                        i.putExtra(KEY_BUNDLE_EVENT_CAT, toCall);
                        startActivity(i);
                    }

                } else {
                    ModelEvent eventSelected = mEvents.get(position);
                    Intent i = new Intent(ListOfEvent.this, EventActivity.class);
                    i.putExtra(KEY_BUNDLE_EVENT, eventSelected);
                    i.putExtra(KEY_BUNDLE_EVENT_CAT, toCall);
                    startActivity(i);
                }
            }
        });


    }


    @Override
    public void onResponse(Response<ModelListOfEvents> response, Retrofit retrofit) {

        response.body();


        mEvents = new ArrayList<ModelEvent>();
        if (response.body().data != null) {
            mProgressBarListOfEvents.setVisibility(View.GONE);

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
                        response.body().data.get(i).flyer,
                        response.body().data.get(i).id
                ));

            }


            if (cat.equals("Art Exhibitions")) {
                mAdapterArtExhibition = new ListOfEventsArtExhibitionAdapter(mEvents, getApplicationContext());
                mEventsListview.setAdapter(mAdapterArtExhibition);
            } else {
                if (mComeFrom.equals("feed_events")) {
                    ArrayList<ModelEvent> mEventsFromFeed = new ArrayList<>();
                    mEventsFromFeed.add(new ModelEvent());
                    mEventsFromFeed.addAll(mEvents);
                    mAdapterGeneral = new ListOfEventsAdapter(mEventsFromFeed, getApplicationContext(), true, urlEventFeed, mTitleEventFeed, true);
                    mEventsListview.setAdapter(mAdapterGeneral);
                } else {
                    mAdapterGeneral = new ListOfEventsAdapter(mEvents, getApplicationContext(), false, "", "", true);
                    mEventsListview.setAdapter(mAdapterGeneral);
                }
            }


        } else if (response.body().data == null) {
            mLlNoEvents.setVisibility(View.VISIBLE);
            mProgressBarListOfEvents.setVisibility(View.GONE);

        }
    }

    @Override
    public void onFailure(Throwable t) {

        mProgressBarListOfEvents.setVisibility(View.GONE);

    }


    public void callTheEvents(String toCall) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.smartshanghai.com/api/events")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfaceEventsAPI api = retrofit.create(InterfaceEventsAPI.class);

        String url;
        if (mComeFrom.equals("feed_events") || mComeFrom.equals("public_holidays")) {
            url = "?tagId=" + toCall + "&key=abc123iphone";
        } else {
            url = "?tagName=" + toCall + "&key=abc123iphone";
        }

        Call<ModelListOfEvents> callListOfEvents = api.getEventList(url);
        callListOfEvents.enqueue(this);


    }


}
