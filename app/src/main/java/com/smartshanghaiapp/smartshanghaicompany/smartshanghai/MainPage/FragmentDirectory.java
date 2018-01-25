package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.DraweeView;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.AreaActivity;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.ChainsActivity;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.CollectionActivity;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.CuisinineActivity;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.DatabaseDirectory.Database;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.DirectoryListing;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.DirectoryListingVenuesAdapter;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.FragmentLifeCycle;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper.InterfaceCollectionsAPI;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.ListOfCollections;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelCollection;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfCollections;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelVenue;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.NameActivity;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.OnlineFrameActivity;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.R;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.RecentlyOpenedActivity;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.VenuePageActivity;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class FragmentDirectory extends Fragment implements FragmentLifeCycle, Callback<ModelListOfCollections> {
    private static final String TAG = FragmentDirectory.class.getSimpleName();

    private RelativeLayout mRlNearby0;
    private RelativeLayout mRlRestaurant1;
    private RelativeLayout mRlNightlife2;
    private RelativeLayout mRlArtAndStage3;
    private RelativeLayout mRlEducation4;
    private RelativeLayout mRlHotel5;
    private RelativeLayout mRlServices6;
    private RelativeLayout mRlTravel7;
    private RelativeLayout mRlShopping8;
    private RelativeLayout mRlSportAndRecreation9;
    private RelativeLayout mRlWellbeing10;

    private RelativeLayout mRlArea;
    private RelativeLayout mRlRecentlyOpened;
    private RelativeLayout mRlChains;
    private RelativeLayout mRlName;
    private RelativeLayout mRlCuisine;


    private int query_restaurant = 20;
    private int query_nightlife = 14;
    private int query_artAndStage = 1448;
    private int query_education = 18;
    private int query_hotel = 13;
    private int query_services = 25;
    private int query_travel = 24;
    private int query_shopping = 21;
    private int query_sportAndRecreation = 23;
    private int query_wellbeing = 22;

    private int query_area = 11;
    private int query_chain = 12;
    private int query_recently_opened = 76;


    private ArrayList<ModelCollection> mCollections;

    public final static String query_come_from_main = "query_come_from_main";
    public final static String query_name = "no category";
    public final static String query = "no query";
    public final static String KEY_BUNDLE_LIST_OF_COLLECTION = "query list of collection";

    public final static String KEY_BUNDLE_ID_ITEM_SEARCHED_SELECTED = "query item searched selected";

    public final static String KEY_BUNDLE_WEB_ADDRESS_EDUCATION = "Key_bundle_web_address_education";


    private DraweeView mDvImageCarousel;
    private TextView mTvTitleCarousel;
    private TextView mTvSubTitleCarousel;

    private TextView mButCarouselMore;

    private SharedPreferences mSharedPreferences;
    private boolean mDbIsUsable;

    public static CarouselView carouselView;

    private RelativeLayout mRlLoading;

    private SearchView mSearchBarDirectory;
    private LinearLayout mLlDirectoryDefault;
    private ListView mLviDirectorySearch;

    private Database mDatabase;

    private ArrayList<ModelVenue> mArrayListVenuesSearch;

    private DirectoryListingVenuesAdapter mAdapter;

    private Double mXuser;
    private Double mYuser;

    private boolean mLocAuth;

    public final static String KEY_BUNDLE_COLLECTION_FROM_DIRECTORY = "Key_bundle_collection_from_directory";

    private RelativeLayout mRlCarousel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_directory, container, false);

        Fresco.initialize(getActivity());

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mDbIsUsable = mSharedPreferences.getBoolean("dbIsUsable", false);
        mLocAuth = mSharedPreferences.getBoolean("LocationAuth", false);

        mCollections = new ArrayList<ModelCollection>();
        mArrayListVenuesSearch = new ArrayList<ModelVenue>();
        mDatabase = new Database(getActivity());

        mXuser = MainActivity.mXuser;
        mYuser = MainActivity.mYuser;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.smartshanghai.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfaceCollectionsAPI api = retrofit.create(InterfaceCollectionsAPI.class);


        Call<ModelListOfCollections> callListOfTickets = api.getCollectionsList();
        callListOfTickets.enqueue(this);

        mLlDirectoryDefault = (LinearLayout) view.findViewById(R.id.ll_frag_directory_linear_default);
        mLviDirectorySearch = (ListView) view.findViewById(R.id.lv_frag_directory_listview_search);

        mLviDirectorySearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(getActivity(), VenuePageActivity.class);
                i.putExtra(KEY_BUNDLE_ID_ITEM_SEARCHED_SELECTED, mArrayListVenuesSearch.get(position).getId());
                startActivity(i);
            }
        });


        setSearchBarBehavior(view);

        carouselView = (CarouselView) view.findViewById(R.id.carouselView_frag_directory);
        mRlCarousel = (RelativeLayout) view.findViewById(R.id.rl_carousel_directory_fragment);
        if (isNetworkAvailable()){
            mRlCarousel.setVisibility(View.VISIBLE);
        }else{
            mRlCarousel.setVisibility(View.GONE);
        }


        mButCarouselMore = (TextView) view.findViewById(R.id.but_frag_directory_more_articles);
        mButCarouselMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ListOfCollections.class);
                ArrayList<ModelCollection> dataModelCollections = new ArrayList<ModelCollection>();
                ModelListOfCollections modelListOfCollections = new ModelListOfCollections(dataModelCollections);
                if (mCollections.size() != 0) {
                    for (int i2 = 0; i2 < mCollections.size(); i2++) {
                        modelListOfCollections.getData().add(mCollections.get(i2));
                    }
                }
                i.putExtra(KEY_BUNDLE_LIST_OF_COLLECTION, modelListOfCollections);
                startActivity(i);
            }
        });

        mRlNearby0 = (RelativeLayout) view.findViewById(R.id.rl_frag_directory_nearby);
        mRlRestaurant1 = (RelativeLayout) view.findViewById(R.id.rl_frag_directory_restaurant);
        mRlNightlife2 = (RelativeLayout) view.findViewById(R.id.rl_frag_directory_nightlife);
        mRlArtAndStage3 = (RelativeLayout) view.findViewById(R.id.rl_frag_directory_art_and_stage);
        mRlEducation4 = (RelativeLayout) view.findViewById(R.id.rl_frag_directory_education);
        mRlHotel5 = (RelativeLayout) view.findViewById(R.id.rl_frag_directory_hotels);
        mRlServices6 = (RelativeLayout) view.findViewById(R.id.rl_frag_directory_services);
        mRlTravel7 = (RelativeLayout) view.findViewById(R.id.rl_frag_directory_travel);
        mRlShopping8 = (RelativeLayout) view.findViewById(R.id.rl_frag_directory_shopping);
        mRlSportAndRecreation9 = (RelativeLayout) view.findViewById(R.id.rl_frag_directory_sport_and_recreation);
        mRlWellbeing10 = (RelativeLayout) view.findViewById(R.id.rl_frag_directory_wellbeing);

        mRlArea = (RelativeLayout) view.findViewById(R.id.rl_frag_directory_bot_area);
        mRlRecentlyOpened = (RelativeLayout) view.findViewById(R.id.rl_frag_directory_recently_opened);
        mRlChains = (RelativeLayout) view.findViewById(R.id.rl_frag_directory_bot_chains);
        mRlName = (RelativeLayout) view.findViewById(R.id.rl_frag_directory_bot_name);
        mRlCuisine = (RelativeLayout) view.findViewById(R.id.rl_frag_directory_bot_cuisine);
        mRlLoading = (RelativeLayout) view.findViewById(R.id.rl_fragment_directory_loading);



        mRlNearby0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkDbIsAvailable()) {
                    mLocAuth = mSharedPreferences.getBoolean("LocationAuth", false);
                    if (mLocAuth) {
                        mRlLoading.setVisibility(View.VISIBLE);
                        Intent i = new Intent(getActivity(), DirectoryListing.class);
                        i.putExtra(query_name, "Restaurant");
                        i.putExtra(query, query_restaurant);
                        i.putExtra("LISTING_FROM", "nearby_user");
                        startActivity(i);
                    }else{
                        Toast.makeText(getActivity(),"You need to authorize SmSh to access your location if you want to see nearby venues", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.error_db), Toast.LENGTH_LONG).show();
                }
            }
        });

        mRlRecentlyOpened.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkDbIsAvailable()) {
                    Intent i = new Intent(getActivity(), RecentlyOpenedActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.error_db), Toast.LENGTH_LONG).show();
                }
            }
        });

        mRlRestaurant1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkDbIsAvailable()) {
                    mRlLoading.setVisibility(View.VISIBLE);
                    Intent i = new Intent(getActivity(), DirectoryListing.class);
                    i.putExtra(query_name, "Restaurant");
                    i.putExtra(query, query_restaurant);
                    i.putExtra("LISTING_FROM", "main_cat");
                    startActivity(i);
                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.error_db), Toast.LENGTH_LONG).show();
                }
            }
        });

        mRlNightlife2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkDbIsAvailable()) {
                    mRlLoading.setVisibility(View.VISIBLE);
                    Intent i = new Intent(getActivity(), DirectoryListing.class);
                    i.putExtra(query_name, "Nightlife");
                    i.putExtra(query, query_nightlife);
                    i.putExtra("LISTING_FROM", "main_cat");
                    startActivity(i);
                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.error_db), Toast.LENGTH_LONG).show();
                }

            }
        });

        mRlArtAndStage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkDbIsAvailable()) {
                    mRlLoading.setVisibility(View.VISIBLE);
                    Intent i = new Intent(getActivity(), DirectoryListing.class);
                    i.putExtra(query_name, "Arts and Stage");
                    i.putExtra(query, query_artAndStage);
                    i.putExtra("LISTING_FROM", "main_cat");
                    startActivity(i);
                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.error_db), Toast.LENGTH_LONG).show();
                }
            }
        });


        mRlEducation4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkDbIsAvailable()) {
                    mRlLoading.setVisibility(View.VISIBLE);
                    String url = "http://www.smartshanghai.com/education/"; // smsmh education info page
                    Intent i = new Intent(getActivity(), OnlineFrameActivity.class);
                    i.putExtra(KEY_BUNDLE_WEB_ADDRESS_EDUCATION, url);
                    startActivity(i);
                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.error_db), Toast.LENGTH_LONG).show();
                }
            }
        });


        mRlHotel5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkDbIsAvailable()) {
                    mRlLoading.setVisibility(View.VISIBLE);
                    Intent i = new Intent(getActivity(), DirectoryListing.class);
                    i.putExtra(query_name, "Hotels");
                    i.putExtra(query, query_hotel);
                    i.putExtra("LISTING_FROM", "main_cat");
                    startActivity(i);
                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.error_db), Toast.LENGTH_LONG).show();
                }
            }
        });


        mRlServices6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkDbIsAvailable()) {
                    mRlLoading.setVisibility(View.VISIBLE);
                    Intent i = new Intent(getActivity(), DirectoryListing.class);
                    i.putExtra(query_name, "Services");
                    i.putExtra(query, query_services);
                    i.putExtra("LISTING_FROM", "main_cat");
                    startActivity(i);
                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.error_db), Toast.LENGTH_LONG).show();
                }
            }
        });


        mRlTravel7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkDbIsAvailable()) {
                    mRlLoading.setVisibility(View.VISIBLE);
                    Intent i = new Intent(getActivity(), DirectoryListing.class);
                    i.putExtra(query_name, "Travel");
                    i.putExtra(query, query_travel);
                    i.putExtra("LISTING_FROM", "main_cat");
                    startActivity(i);
                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.error_db), Toast.LENGTH_LONG).show();
                }
            }
        });


        mRlShopping8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkDbIsAvailable()) {
                    mRlLoading.setVisibility(View.VISIBLE);
                    Intent i = new Intent(getActivity(), DirectoryListing.class);
                    i.putExtra(query_name, "Shopping");
                    i.putExtra(query, query_shopping);
                    i.putExtra("LISTING_FROM", "main_cat");
                    startActivity(i);

                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.error_db), Toast.LENGTH_LONG).show();
                }
            }
        });


        mRlSportAndRecreation9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkDbIsAvailable()) {
                    mRlLoading.setVisibility(View.VISIBLE);
                    Intent i = new Intent(getActivity(), DirectoryListing.class);
                    i.putExtra(query_name, "Sports and Recreation");
                    i.putExtra("LISTING_FROM", "main_cat");

                    i.putExtra(query, query_sportAndRecreation);
                    startActivity(i);

                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.error_db), Toast.LENGTH_LONG).show();
                }
            }
        });


        mRlWellbeing10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkDbIsAvailable()) {
                    mRlLoading.setVisibility(View.VISIBLE);
                    Intent i = new Intent(getActivity(), DirectoryListing.class);
                    i.putExtra(query_name, "Wellbeing");
                    i.putExtra("LISTING_FROM", "main_cat");

                    i.putExtra(query, query_wellbeing);
                    startActivity(i);
                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.error_db), Toast.LENGTH_LONG).show();
                }
            }
        });


        mRlArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkDbIsAvailable()) {
                    Intent i = new Intent(getActivity(), AreaActivity.class);
                    i.putExtra(query_name, "Area");
                    i.putExtra(query, query_area);
                    i.putExtra("LISTING_FROM", "main_cat");
                    startActivity(i);
                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.error_db), Toast.LENGTH_LONG).show();
                }
            }
        });


        mRlChains.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkDbIsAvailable()) {
                    Intent i = new Intent(getActivity(), ChainsActivity.class);
                    i.putExtra(query_name, "Chains");
                    i.putExtra(query, query_chain);
                    i.putExtra("LISTING_FROM", "chain");
                    startActivity(i);
                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.error_db), Toast.LENGTH_LONG).show();
                }
            }
        });

        mRlName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkDbIsAvailable()) {
                    Intent i = new Intent(getActivity(), NameActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.error_db), Toast.LENGTH_LONG).show();
                }
            }
        });

        mRlCuisine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkDbIsAvailable()) {
                    Intent i = new Intent(getActivity(), CuisinineActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.error_db), Toast.LENGTH_LONG).show();
                }
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

    @Override
    public void onResume() {
        super.onResume();
        mDbIsUsable = mSharedPreferences.getBoolean("dbIsUsable", false);
        mRlLoading.setVisibility(View.GONE);
    }

    ViewListener viewListener = new ViewListener() {
        @Override
        public View setViewForPosition(final int position) {
            final View customView = getActivity().getLayoutInflater().inflate(R.layout.custom_view_carousel_item, null);
            mTvTitleCarousel = (TextView) customView.findViewById(R.id.tv_custom_view_carousel_title);
            mTvSubTitleCarousel = (TextView) customView.findViewById(R.id.tv_custom_view_carousel_subtitle);
            mDvImageCarousel = (DraweeView) customView.findViewById(R.id.dv_custom_view_carousel);
            if (mCollections.size() != 0 && mCollections.size() > 2) {
                String str = mCollections.get(position).getThumb();
                mTvTitleCarousel.setText(mCollections.get(position).getTitle());
                mTvSubTitleCarousel.setText(mCollections.get(position).getTeaser());
                Uri imageUri = Uri.parse(mCollections.get(position).getThumb());
                mDvImageCarousel.setImageURI(imageUri);
                mDvImageCarousel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getActivity(), CollectionActivity.class);
                        i.putExtra(KEY_BUNDLE_COLLECTION_FROM_DIRECTORY, mCollections.get(position));
                        startActivity(i);
                    }
                });
            }

            return customView;
        }


    };


    @Override
    public void onResponse(Response<ModelListOfCollections> response, Retrofit retrofit) {
        response.body();

        if (response.body().data != null) {

        }
        for (int i = 0; i < response.body().data.size(); i++) {
            mCollections.add(new ModelCollection(
                    response.body().data.get(i).getTag_id(),
                    response.body().data.get(i).getTitle(),
                    response.body().data.get(i).getThumb(),
                    response.body().data.get(i).getTeaser(),
                    response.body().data.get(i).getDescription(),
                    response.body().data.get(i).getLast_edited()
            ));

        }

        carouselView.setViewListener(viewListener);
        carouselView.setPageCount(3);
        carouselView.setIndicatorGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
    }

    @Override
    public void onFailure(Throwable t) {

    }


    private void setSearchBarBehavior(View view) {
        mSearchBarDirectory = (SearchView) view.findViewById(R.id.sv_frag_directory_searchbar);
        mSearchBarDirectory.setFocusable(false);
        mSearchBarDirectory.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkDbIsAvailable()) {
                    Toast.makeText(getActivity(), "You must download the database", Toast.LENGTH_LONG).show();
                }
            }
        });

        mSearchBarDirectory.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    mLlDirectoryDefault.setVisibility(View.VISIBLE);
                    MainActivity.mLlBottomBar.setVisibility(View.VISIBLE);
                    MainActivity.mLlTopBar.setVisibility(View.VISIBLE);
                    mLviDirectorySearch.setVisibility(View.GONE);
                } else {
                    mLlDirectoryDefault.setVisibility(View.GONE);
                    MainActivity.mLlBottomBar.setVisibility(View.GONE);
                    MainActivity.mLlTopBar.setVisibility(View.GONE);
                    mLviDirectorySearch.setVisibility(View.VISIBLE);
                }
            }
        });

        mSearchBarDirectory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchBarDirectory.onActionViewExpanded();
            }
        });
        mSearchBarDirectory.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (checkDbIsAvailable()) {
                    Toast.makeText(getActivity(), "You must download the database", Toast.LENGTH_LONG).show();
                }
                return false;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                if (checkDbIsAvailable()) {
                    if (mSearchBarDirectory.getQuery().length() > 1) {
                        mArrayListVenuesSearch = mDatabase.searchDatabaseForVenues(mSearchBarDirectory.getQuery().toString(), mXuser, mYuser);
                    }

                    mAdapter = new DirectoryListingVenuesAdapter(mArrayListVenuesSearch, getActivity(), mLocAuth, null, false);
                    mLviDirectorySearch.setAdapter(mAdapter);
                    return false;

                }
                return false;
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }


    private boolean checkDbIsAvailable(){
        mDbIsUsable = mSharedPreferences.getBoolean("dbIsUsable", false);
        return mDbIsUsable;
    }


}
