package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.DatabaseDirectory.Database;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage.FragmentDirectory;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage.FragmentMore;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage.MainActivity;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelAd;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelTagIdName;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelVenue;

import java.util.ArrayList;
import java.util.Collections;

public class DirectoryListing extends Activity {
    private DirectoryListingVenuesAdapter mAdapter;
    private ArrayList<ModelVenue> mVenuesArrayList;
    private ListView mVenuesListView;

    private Database mDatabase;

    private double mXuser;
    private double mYuser;

    private ArrayList<String> mArrayListStringSpinner;
    private ArrayList<ModelTagIdName> mArrayListTagId;

    private LinearLayout llfavEmpty;


    private int mIdParent;
    private int mIdSubCat;

    private String mLetterSelected;

    public final static String ID_ITEM_SELECTED = "no id";

    public final static String ID_PARENT_CAT = "no id parent cat";
    public final static String ID_SUB_CAT = "no id sub cat";

    public final static String KEY_BUNDLE_BAIDU_FROM = "from ";


    private TextView mTvNameCat;

    private Double xVenue;
    private Double yVenue;
    private int idVenue;

    private RelativeLayout mRlSpinner;


    private Context mContext;
    private LocationManager lm;
    private Location mLocation;

    private boolean mDbIsUsable;

    private boolean mLocAuth;
    private SharedPreferences mSharedPreferences;

    private String mComeFrom;

    private RelativeLayout butMapBaidu;


    private boolean mIsRestaurant;

    private ArrayList<ModelAd> mListStringsAds;

    private boolean mIsFromNearby;

    private Intent mIntent;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory_listing);

        deClaraAllTheViewItems();
        idVenue = 0;
        mComeFrom = "nothing selected";
        mIntent = getIntent();
        mComeFrom = mIntent.getStringExtra("LISTING_FROM");
        butMapBaidu = (RelativeLayout) findViewById(R.id.but_directory_listing_open_map_baidu);

        mIsFromNearby = false;
        mIsRestaurant = false;
        // come from the main categories of the directory

        if (mComeFrom.equals("main_cat")) { // come from frag directory activity
            mIdParent = getIntent().getExtras().getInt(FragmentDirectory.query);
            String nameFrom = getIntent().getExtras().getString(FragmentDirectory.query_name);
            mTvNameCat.setText(nameFrom);
        } else if (mComeFrom.equals("area")) { // come from chain activity
            mIdParent = getIntent().getExtras().getInt(AreaActivity.queryArea);
            String nameFrom = getIntent().getExtras().getString(AreaActivity.queryName);
            mTvNameCat.setText(nameFrom);
        } else if (mComeFrom.equals("chain")) { // come from chain activity
            mIdParent = getIntent().getExtras().getInt(ChainsActivity.queryChainId);
            String nameFrom = getIntent().getExtras().getString(ChainsActivity.queryChainName);
            mTvNameCat.setText(nameFrom);
        } else if (mComeFrom.equals("cuisine")) { // come from cuisine activity
            mIdParent = getIntent().getExtras().getInt(CuisinineActivity.queryCuisineId);
            String nameFrom = getIntent().getExtras().getString(CuisinineActivity.queryCuisineName);
            mIsRestaurant = true;
            mTvNameCat.setText(nameFrom);
        } else if (mComeFrom.equals("name")) { // come from name activity
            mIdParent = getIntent().getExtras().getInt(NameActivity.queryNameId);
            mLetterSelected = getIntent().getExtras().getString(NameActivity.queryLetterSelected);
            String nameFrom = getIntent().getExtras().getString(NameActivity.queryLetterSelected);
            mTvNameCat.setText(nameFrom);
        } else if (mComeFrom.equals("nearby_user")) { // come from nearby_user
            mIdParent = -1;
            mTvNameCat.setText("Nearby");
        } else if (mComeFrom.equals("nearby")) { // come from venue activity (nearby venues)
            mIdParent = -1;
            xVenue = getIntent().getExtras().getDouble(VenuePageActivity.queryVenueX);
            yVenue = getIntent().getExtras().getDouble(VenuePageActivity.queryVenueY);
            idVenue = getIntent().getExtras().getInt(VenuePageActivity.queryIdVenue);
            mTvNameCat.setText(mComeFrom);
        } else if (mComeFrom.equals("favorite")) { // come from name favorites
            mIdParent = -1;
            mTvNameCat.setText("Favorites");
        } else if (mComeFrom.equals("consulates")) { // come from name consulates
            mIdParent = getIntent().getExtras().getInt(FragmentMore.query);
            mTvNameCat.setText("Consulates");
        }

        int campaignId;
        if (mIdParent == 14) {// NIGHTLIFE
            campaignId = 21;
        } else if (mIdParent == 20 || mIsRestaurant) {
            campaignId = 22;
        } else {
            campaignId = 23;
        }

        /*  ADS ARE NOT ACTIVATED ON THE DIRECTORY LISTING FOR NOW

        mListStringsAds = new ArrayList<ModelAd>();
        final Retrofit retrofitAd = new Retrofit.Builder()
                .baseUrl("http://www.smartshanghai.com/api/ads")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        InterfaceAdvertisementEventAPI apiAd = retrofitAd.create(InterfaceAdvertisementEventAPI.class);

        Call<ModelListOfAd> callListAd = apiAd.getAds("?key=abc123iphone&sectionId=" + campaignId + "&limit=3");
        callListAd.enqueue(new Callback<ModelListOfAd>() {

                               @Override
                               public void onResponse(Response<ModelListOfAd> response, Retrofit retrofit) {
                                   response.body();
                                   if (response.body() != null) {
                                       for (int i = 0; i < response.body().data.size(); i++) {
                                           mListStringsAds.add(new ModelAd(
                                                   response.body().data.get(i).getCampaign_id(),
                                                   response.body().data.get(i).getAdfilename(),
                                                   response.body().data.get(i).getAdlink()
                                           ));
                                       }
                                   }
                                   Collections.sort(mVenuesArrayList);
                                   mAdapter = new DirectoryListingVenuesAdapter(mVenuesArrayList, getBaseContext(), mLocAuth, mListStringsAds,mIsFromNearby);
                                   mVenuesListView.setAdapter(mAdapter);
                                   mAdapter.notifyDataSetChanged();
                               }

                               @Override
                               public void onFailure(Throwable t) {

                               }
                           }
        );

        */

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
        mLocAuth = mSharedPreferences.getBoolean("LocationAuth", false);
        mDbIsUsable = mSharedPreferences.getBoolean("dbIsUsable", false);

        if (!mLocAuth) {
            Toast.makeText(getBaseContext(), "Please give SmartShanghai the permission to use your location in order to find the venues around you !",
                    Toast.LENGTH_LONG).show();
        }

        mXuser = MainActivity.mXGooglePosition;
        mYuser = MainActivity.mYGooglePosition;

        mVenuesArrayList = new ArrayList<ModelVenue>();
        mArrayListTagId = new ArrayList<ModelTagIdName>();
        mArrayListTagId.add(new ModelTagIdName(0, "All"));

        if (mDatabase.checkDataBase() == true && mDbIsUsable) {
            mArrayListTagId.addAll(mDatabase.getSubCategoriesId(mIdParent));
        }

        mArrayListStringSpinner = new ArrayList<String>();
        for (int i = 0; i < mArrayListTagId.size(); i++) {
            mArrayListStringSpinner.add(mArrayListTagId.get(i).getTag_name());
        }

        //Set the spinner
        final Spinner mySpinner = (Spinner) findViewById(R.id.spin_directory_listing_activity);
        mRlSpinner = (RelativeLayout) findViewById(R.id.rl_spin_directory_listing_activity);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.custom_item_spinner_directory_listing,
                R.id.tv_custom_spinner_directory_listing,
                mArrayListStringSpinner);
        mySpinner.setAdapter(adapter);


        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (mArrayListTagId.get(position).getTag_id() == 0) { // IF ALL IS SELECTED
                    if (mDatabase.checkDataBase() == true && mDbIsUsable) {
                        if (mComeFrom.equals("main_cat")) {// Comes from the main categories or chain
                            mVenuesArrayList = mDatabase.getModelVenuesCloseWithId(mXuser, mYuser, mIdParent);
                        } else if (mComeFrom.equals("area")) {//come from the area
                            mVenuesArrayList = mDatabase.getModelVenuesFromAreaWithId(mXuser, mYuser, mIdParent);
                            mRlSpinner.setVisibility(View.GONE);
                        } else if (mComeFrom.equals("chain")) { // comes from chain
                            mVenuesArrayList = mDatabase.getModelVenuesFromChainWithId(mXuser, mYuser, mIdParent);
                            mRlSpinner.setVisibility(View.GONE);
                        } else if (mComeFrom.equals("name")) { // comes from name
                            mVenuesArrayList = mDatabase.getModelVenuesFromLetterWithId(mXuser, mYuser, mLetterSelected);
                            mRlSpinner.setVisibility(View.GONE);
                        } else if (mComeFrom.equals("consulates")) { // comes from consulates
                            mVenuesArrayList = mDatabase.getModelVenuesCloseWithId(mXuser, mYuser, mIdParent);
                            mRlSpinner.setVisibility(View.GONE);
                        } else if (mComeFrom.equals("recently opened")) { // comes from recently opened
                            mVenuesArrayList = mDatabase.getModelVenuesRecentlyOpened(mXuser, mYuser, mIdParent);
                            mRlSpinner.setVisibility(View.GONE);
                        } else if (mComeFrom.equals("cuisine")) { // comes from cuisine
                            mVenuesArrayList = mDatabase.getModelVenuesFromCuisineWithId(mXuser, mYuser, mIdParent);
                            mRlSpinner.setVisibility(View.GONE);
                        } else if (mComeFrom.equals("favorite")) { // comes from fav
                            llfavEmpty = (LinearLayout) findViewById(R.id.ll_directory_listing_empty_fav);
                            TinyDB tinyDB = new TinyDB(getBaseContext());
                            ArrayList<String> ids = new ArrayList<String>();
                            ids = tinyDB.getListString("favoriteListIdVenues");
                            if (ids != null && ids.size() != 0) {
                                mVenuesArrayList = mDatabase.getModelVenuesFavorites(mXuser, mYuser, ids);
                                butMapBaidu.setVisibility(View.GONE);
                            } else if (ids.size() == 0) {
                                llfavEmpty.setVisibility(View.VISIBLE);
                            }

                            mRlSpinner.setVisibility(View.GONE);
                        } else if (mComeFrom.equals("nearby")) { // comes from cuisine
                            mVenuesArrayList = mDatabase.getModelVenuesCloseOfCoord(xVenue, yVenue, idVenue);
                            mRlSpinner.setVisibility(View.GONE);
                            mIsFromNearby = true;
                        } else if (mComeFrom.equals("nearby_user")) { // comes nearby _ user
                            mVenuesArrayList = mDatabase.getModelVenuesCloseOfCoord(mXuser, mYuser, 0);
                            mRlSpinner.setVisibility(View.GONE);
                        }


                        Collections.sort(mVenuesArrayList);
                        mAdapter = new DirectoryListingVenuesAdapter(mVenuesArrayList, getBaseContext(), mLocAuth, mListStringsAds, mIsFromNearby);
                        mVenuesListView.setAdapter(mAdapter);


                    }
                } else if (mArrayListTagId.get(position).getTag_id() != 0 && mArrayListTagId.get(position) != null) { //IF A SUBCATEGORIE IS SELECTED
                    if (mDatabase.checkDataBase() == true) {
                        mVenuesArrayList = mDatabase.getModelVenuesCloseWithSubId(mXuser, mYuser, mIdParent, mArrayListTagId.get(position).getTag_id());
                        Collections.sort(mVenuesArrayList);
                        mIdSubCat = mArrayListTagId.get(position).getTag_id();
                        mAdapter = new DirectoryListingVenuesAdapter(mVenuesArrayList, getBaseContext(), mLocAuth, mListStringsAds, mIsFromNearby);
                        mVenuesListView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }

                } else {
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Nothing can't be selected
            }
        });


        butMapBaidu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLocAuth) {
                    Intent i = new Intent(DirectoryListing.this, BaiduMapVenuesActivity.class);
                    i.putExtra(ID_PARENT_CAT, mIdParent);
                    i.putExtra(ID_SUB_CAT, mIdSubCat);
                    i.putExtra(KEY_BUNDLE_BAIDU_FROM, mComeFrom);
                    if (mComeFrom.equals("nearby")) {
                        i.putExtra("venueX", xVenue);
                        i.putExtra("venueY", yVenue);
                        i.putExtra("smshID", idVenue);
                    }
                    if (mComeFrom.equals("nearby_user")) {
                        i.putExtra("venueX", mXuser);
                        i.putExtra("venueY", mYuser);
                        i.putExtra("smshID", 0);
                    }
                    if (mComeFrom.equals("name")) {
                        i.putExtra("letter", mLetterSelected);
                    }
                    if (mTvNameCat.getText().toString() != null){
                        i.putExtra("name_cat", mTvNameCat.getText().toString());
                    }
                    startActivity(i);
                }else{
                    Toast.makeText(getBaseContext(), "You need to give SmartShanghai the permission to use your location to see all the venues around you on the map!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        if (mDatabase.checkDataBase() == true) {
            mVenuesArrayList = mDatabase.getModelVenuesCloseWithId(mXuser, mYuser, mIdParent);
            Collections.sort(mVenuesArrayList);
            mAdapter = new DirectoryListingVenuesAdapter(mVenuesArrayList, this, mLocAuth, mListStringsAds,mIsFromNearby);
            mVenuesListView.setAdapter(mAdapter);
        }

        mVenuesListView.setOnItemClickListener(new AdapterView.OnItemClickListener()

                                               {
                                                   @Override
                                                   public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                                       Intent i = new Intent(DirectoryListing.this, VenuePageActivity.class);
                                                       i.putExtra(ID_ITEM_SELECTED, mVenuesArrayList.get(position).getId());
                                                       startActivity(i);

                                                   }
                                               }

        );


    }


    private void deClaraAllTheViewItems() {

        mTvNameCat = (TextView) findViewById(R.id.tv_directory_listing_name_categorie);
        mVenuesListView = (ListView) findViewById(R.id.lv_directory_listing_list);
        mDatabase = new Database(this);


    }

    @Override
    protected void onResume() {
        super.onResume();

        mXuser = MainActivity.mXGooglePosition;
        mYuser = MainActivity.mYGooglePosition;
    }
}
