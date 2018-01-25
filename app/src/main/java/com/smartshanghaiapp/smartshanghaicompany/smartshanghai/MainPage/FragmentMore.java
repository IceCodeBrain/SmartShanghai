package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.BuyAndSellActivity;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.BuyAndSellAdapter;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.DirectoryListing;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.EssentialsActivity;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.JobsActivity;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MoreListActivity;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.FragmentLifeCycle;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.ListOfEvent;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.OnlineFrameActivity;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.R;


public class FragmentMore extends Fragment implements FragmentLifeCycle {

    private static final String TAG = FragmentMore.class.getSimpleName();
    public static Context mContext;

    private RelativeLayout mRlEssentials;
    private RelativeLayout mRlHousing;
    private RelativeLayout mRlJobs;
    private RelativeLayout mRlBuyAndSell;

    private RelativeLayout mRlEmergencies;
    private RelativeLayout mRlPublicHolidays;
    private RelativeLayout mRlConsulates;
    private RelativeLayout mRlTravelBasics;
    private RelativeLayout mRlBeginnerGuide;
    private RelativeLayout mRlHospitals;
    private RelativeLayout mRlVeterinarian;

    public final static String KEY_BUNDLE_WEB_ADDRESS_BEGINNER_GUIDE = "Key_bundle_web_address_beginner_guide";

    private SharedPreferences mSharedPreferences;

    private boolean mDbIsUsable;

    public final static String query_name = "quer name";
    public final static String query = "quer numb";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_more, container, false);

        mContext = FragmentMore.this.getActivity().getApplicationContext();

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mDbIsUsable = mSharedPreferences.getBoolean("dbIsUsable", false);


        mRlEssentials= (RelativeLayout) view.findViewById(R.id.rl_frag_more_essentials_essentials);
        mRlEssentials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), EssentialsActivity.class);
                startActivity(i);
            }
        });

        mRlHousing = (RelativeLayout) view.findViewById(R.id.rl_frag_more_housing);
        mRlHousing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(getActivity(), JobsActivity.class);
                //startActivity(i);
                Toast.makeText(getActivity(), "Available soon", Toast.LENGTH_SHORT).show();
            }
        });

        mRlJobs= (RelativeLayout) view.findViewById(R.id.rl_frag_more_jobs);
        mRlJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), JobsActivity.class);
                startActivity(i);
            }
        });

        mRlBuyAndSell= (RelativeLayout) view.findViewById(R.id.rl_frag_more_buyandsell);
        mRlBuyAndSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), BuyAndSellActivity.class);
                startActivity(i);
            }
        });

        mRlEmergencies = (RelativeLayout) view.findViewById(R.id.rl_frag_essentials_emergencies);
        mRlEmergencies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MoreListActivity.class);
                i.putExtra("essentials_id", 1);
                startActivity(i);
            }
        });

        mRlPublicHolidays= (RelativeLayout) view.findViewById(R.id.rl_frag_essentials_public_holidays);
        mRlPublicHolidays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ListOfEvent.class);
                i.putExtra("topText", "Public Holidays");
                i.putExtra("EVENTS_FROM", "public_holidays");
                i.putExtra("toCall", "1614"); // is the tag id
                startActivity(i);
            }
        });

        mRlConsulates = (RelativeLayout) view.findViewById(R.id.rl_frag_essentials_consulates);
        mRlConsulates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkDbIsAvailable()) {
                    Intent i = new Intent(getActivity(), DirectoryListing.class);
                    i.putExtra(query_name, "Consulates");
                    i.putExtra(query, 111);
                    i.putExtra("LISTING_FROM", "consulates");
                    startActivity(i);
                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.error_db), Toast.LENGTH_LONG).show();
                }
            }
        });

        mRlTravelBasics= (RelativeLayout) view.findViewById(R.id.rl_frag_essentials_travel_basics);
        mRlTravelBasics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MoreListActivity.class);
                i.putExtra("essentials_id", 6);
                startActivity(i);
            }
        });

        mRlBeginnerGuide = (RelativeLayout) view.findViewById(R.id.rl_frag_essentials_beginner_guide);
        mRlBeginnerGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), OnlineFrameActivity.class);
                i.putExtra(KEY_BUNDLE_WEB_ADDRESS_BEGINNER_GUIDE, "http://www.smartshanghai.com/articles/community/shanghai-beginners-guide-how-to-survive-and-prosper-in-the-city?source=android");
                startActivity(i);
            }
        });

        mRlHospitals= (RelativeLayout) view.findViewById(R.id.rl_frag_essentials_hospitals);
        mRlHospitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MoreListActivity.class);
                i.putExtra("essentials_id", 7);
                startActivity(i);
            }
        });


        mRlVeterinarian= (RelativeLayout) view.findViewById(R.id.rl_frag_essentials_veterinarian);
        mRlVeterinarian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MoreListActivity.class);
                i.putExtra("essentials_id", 8);
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
    public void onResume() {
        super.onResume();

    }

    private boolean checkDbIsAvailable() {
        mDbIsUsable = mSharedPreferences.getBoolean("dbIsUsable", false);
        return mDbIsUsable;
    }

}



