package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.EventTodayActivity;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.FragmentLifeCycle;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.ListOfEvent;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.R;


public class FragmentEvents extends Fragment implements FragmentLifeCycle {


    private static final String TAG = FragmentEvents.class.getSimpleName();

    private RelativeLayout mRlWhatToday;
    private RelativeLayout mRlNightlife;
    private RelativeLayout mRlBrunchDeal;
    private RelativeLayout mRlArtExhibition;
    private RelativeLayout mRlLunchDeal;
    private RelativeLayout mRlMarkets;
    private RelativeLayout mRlLiveMusicShow;
    private RelativeLayout mRlCommunity;
    private RelativeLayout mRlLadiesNight;
    private RelativeLayout mRlStage;
    private RelativeLayout mRlHappyHours;
    private RelativeLayout mRlDinnerDeals;
    private RelativeLayout mRlTravelsDeals;
    private RelativeLayout mRlSport;
    private RelativeLayout mRlArts;


    public final static String categorie = "events categorie";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);


        mRlWhatToday = (RelativeLayout) view.findViewById(R.id.rl_fragment_event_button_what_today);
        mRlWhatToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), EventTodayActivity.class);
                startActivity(i);
            }
        });


        mRlNightlife = (RelativeLayout) view.findViewById(R.id.rl_fragment_event_nightlife);
        mRlNightlife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ListOfEvent.class);
                i.putExtra("topText", "Nightlife");
                i.putExtra("EVENTS_FROM", "main_events");
                i.putExtra("toCall", "nightlife");
                startActivity(i);
            }
        });

        mRlArtExhibition = (RelativeLayout) view.findViewById(R.id.rl_fragment_event_button_art_exhibition);
        mRlArtExhibition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ListOfEvent.class);
                i.putExtra("toCall", "artExhibitions");
                i.putExtra("topText", "Art Exhibitions");
                i.putExtra("EVENTS_FROM", "art_events");
                startActivity(i);
            }
        });

        mRlHappyHours = (RelativeLayout) view.findViewById(R.id.rl_fragment_event_happy_hours);
        mRlHappyHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ListOfEvent.class);
                i.putExtra("toCall", "happyHours");
                i.putExtra("topText", "Happy Hours");
                i.putExtra("EVENTS_FROM", "main_events");
                startActivity(i);
            }
        });


        mRlLiveMusicShow = (RelativeLayout) view.findViewById(R.id.rl_fragment_event_button_live_music);
        mRlLiveMusicShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ListOfEvent.class);
                i.putExtra("toCall", "liveMusic");
                i.putExtra("topText", "Live Music");
                i.putExtra("EVENTS_FROM", "main_events");
                startActivity(i);
            }
        });

        mRlMarkets = (RelativeLayout) view.findViewById(R.id.rl_fragment_event_markets);
        mRlMarkets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ListOfEvent.class);
                i.putExtra("toCall", "markets");
                i.putExtra("topText", "Markets");
                i.putExtra("EVENTS_FROM", "main_events");
                startActivity(i);
            }
        });


        mRlCommunity = (RelativeLayout) view.findViewById(R.id.rl_fragment_event_button_community);
        mRlCommunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ListOfEvent.class);
                i.putExtra("topText", "Community");
                i.putExtra("toCall", "community");
                i.putExtra("EVENTS_FROM", "main_events");
                startActivity(i);
            }
        });

        mRlLadiesNight = (RelativeLayout) view.findViewById(R.id.rl_fragment_event_ladies_night);
        mRlLadiesNight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ListOfEvent.class);
                i.putExtra("topText", "Ladies' Nights");
                i.putExtra("EVENTS_FROM", "main_events");
                i.putExtra("toCall", "ladiesNights");
                startActivity(i);
            }
        });


        mRlBrunchDeal = (RelativeLayout) view.findViewById(R.id.rl_fragment_event_brunch_deals);
        mRlBrunchDeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ListOfEvent.class);
                i.putExtra("topText", "Brunch Deals");
                i.putExtra("toCall", "brunchDeals");
                i.putExtra("EVENTS_FROM", "main_events");
                startActivity(i);

            }
        });

        mRlLunchDeal = (RelativeLayout) view.findViewById(R.id.rl_fragment_lunch_deals);
        mRlLunchDeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ListOfEvent.class);
                i.putExtra("topText", "Lunch Deals");
                i.putExtra("toCall", "lunchDeals");
                i.putExtra("EVENTS_FROM", "main_events");
                startActivity(i);

            }
        });


        mRlLiveMusicShow = (RelativeLayout) view.findViewById(R.id.rl_fragment_event_button_live_music);
        mRlLiveMusicShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ListOfEvent.class);
                i.putExtra("topText", "Live Music");
                i.putExtra("toCall", "liveMusic");
                i.putExtra("EVENTS_FROM", "main_events");
                startActivity(i);
            }
        });


        mRlSport = (RelativeLayout) view.findViewById(R.id.rl_fragment_event_sport);
        mRlSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ListOfEvent.class);
                i.putExtra("topText", "Sports");
                i.putExtra("toCall", "sports");
                i.putExtra("EVENTS_FROM", "main_events");
                startActivity(i);
            }
        });


        mRlStage = (RelativeLayout) view.findViewById(R.id.rl_fragment_stage);
        mRlStage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ListOfEvent.class);
                i.putExtra("topText", "Stage");
                i.putExtra("toCall", "stage");
                i.putExtra("EVENTS_FROM", "main_events");
                startActivity(i);
            }
        });


        return view;


    }


    @Override
    public void onPauseFragment() {
        if (isAdded()) {


        }

    }


    @Override
    public void onResumeFragment() {
        if (isAdded()) {

        }

    }

    @Override
    public void onBackPressed() {

    }

}