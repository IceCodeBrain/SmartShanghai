package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.CollectionActivity;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.FragmentLifeCycle;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.GalleryActivity;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper.InterfaceBookmarkedAPI;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper.InterfaceFeedAPI;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.ListOfEvent;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.ListOfFeedItemsAdapter;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelCollection;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelFeedItem;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfFeedItems;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.OnlineFrameActivity;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.R;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.ReviewsActivity;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.VenuePageActivity;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class FragmentFeed extends Fragment implements FragmentLifeCycle {
    private static final String TAG = FragmentFeed.class.getSimpleName();

    private ListView mLvFeed;
    private ArrayList<ModelFeedItem> mFeedItems;
    private ProgressBar mProgressBarFeed;

    private ListOfFeedItemsAdapter mAdapterFeed;

    private RelativeLayout mRlLoading;

    public final static String KEY_BUNDLE_WEB_ADDRESS_FOR_ADVERTISEMENT = "Key_bundle_web_address_advertisement";
    public final static String KEY_BUNDLE_VENUE_ACTIVITY = "Key_bundle_venue_activity";
    public final static String KEY_BUNDLE_GALLERY_SELECTED_ID = "Key_bundle_galery_selected_id";
    public final static String KEY_BUNDLE_COLLECTION_SELECTED = "Key_bundle_collection_selected";
    public final static String KEY_BUNDLE_WEB_ADDRESS_FROM_FEED = "Key_bundle_web_address_from_feeed";
    public final static String KEY_BUNDLE_WEB_ADDRESS_FROM_FEED_WIRE = "Key_bundle_web_address_from_feeed_wire";
    public final static String KEY_BUNDLE_WEB_ADDRESS_FROM_FEED_TICKET = "Key_bundle_web_address_from_feeed_ticket";

    public final static String KEY_BUNDLE_ONLINE_ACT_SHARE_URL = "key_bundle_online_act_share_url";
    public final static String KEY_BUNDLE_ONLINE_ACT_SHARE_TITLE = "key_bundle_online_act_share_title";
    public final static String KEY_BUNDLE_ONLINE_ACT_SHARE_PHRASE = "key_bundle_online_act_share_phrase";



    private SharedPreferences mSharedPreferences;
    private boolean mDbIsUsable;
    private int mUserId;

    private boolean mLoading;

    private Parcelable state;


    private boolean mEndOfFeed;

    private boolean mLvInit;

    private int mPage;

    private ArrayList<Integer> mBookmarked;

    private int mTotalItems;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        mRlLoading = (RelativeLayout) view.findViewById(R.id.rl_frag_feed_loading);


        mLvFeed = (ListView) view.findViewById(R.id.lv_frag_feed_feedlist);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mDbIsUsable = mSharedPreferences.getBoolean("dbIsUsable", false);
        mUserId = mSharedPreferences.getInt("userID", 0);



        mProgressBarFeed = (ProgressBar) view.findViewById(R.id.load_frag_feed);

        mLvFeed.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mFeedItems.size() > 0 && position < mFeedItems.size()) {
                    if (mFeedItems.get(position).getType_id() == 1) {
                        mRlLoading.setVisibility(View.VISIBLE);
                        if (mFeedItems.get(position).getMeta3() != null) {
                            Intent i = new Intent(getActivity(), OnlineFrameActivity.class);
                            i.putExtra(KEY_BUNDLE_WEB_ADDRESS_FROM_FEED_WIRE, mFeedItems.get(position).getMeta3() + "?source=android");
                            i.putExtra(KEY_BUNDLE_ONLINE_ACT_SHARE_URL, mFeedItems.get(position).getShare_url());
                            i.putExtra(KEY_BUNDLE_ONLINE_ACT_SHARE_TITLE, mFeedItems.get(position).getShare_title());
                            i.putExtra(KEY_BUNDLE_ONLINE_ACT_SHARE_PHRASE, mFeedItems.get(position).getShare_phrase());

                            startActivity(i);
                        }

                    } else if (mFeedItems.get(position).getType_id() == 2) {
                        if (mFeedItems.get(position).getMeta4() != null) {
                            mRlLoading.setVisibility(View.VISIBLE);
                            Intent i = new Intent(getActivity(), OnlineFrameActivity.class);
                            i.putExtra(KEY_BUNDLE_WEB_ADDRESS_FROM_FEED_TICKET, mFeedItems.get(position).getMeta4() + "?source=android");
                            startActivity(i);
                        }
                    } else if (mFeedItems.get(position).getType_id() == 3) {
                        Intent i = new Intent(getActivity(), ReviewsActivity.class);
                        startActivity(i);


                    } else if (mFeedItems.get(position).getType_id() == 4) {

                        Intent i = new Intent(getActivity(), GalleryActivity.class);
                        i.putExtra(KEY_BUNDLE_GALLERY_SELECTED_ID, mFeedItems.get(position).getEntry_id());
                        startActivity(i);

                    } else if (mFeedItems.get(position).getType_id() == 5) {
                        ModelCollection modelCollection = new ModelCollection(
                                mFeedItems.get(position).getEntry_id(), //tag id
                                mFeedItems.get(position).getTitle(), //title
                                mFeedItems.get(position).getMeta1(), //thumb
                                "", // teaser
                                mFeedItems.get(position).getMeta2(), //description
                                mFeedItems.get(position).getLast_edited() //description
                        );

                        Intent i = new Intent(getActivity(), CollectionActivity.class);
                        i.putExtra(KEY_BUNDLE_COLLECTION_SELECTED, modelCollection);
                        startActivity(i);


                    } else if (mFeedItems.get(position).getType_id() == 6) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse("http://www.smartshanghai.com/adclick.php?bannerId=" + mFeedItems.get(position).getEntry_id()));
                        startActivity(i);

                    } else if (mFeedItems.get(position).getType_id() == 7) {
                        Intent i = new Intent(getActivity(), ListOfEvent.class);
                        i.putExtra(KEY_BUNDLE_GALLERY_SELECTED_ID, mFeedItems.get(position).getEntry_id());
                        i.putExtra("topText", "Collection");
                        i.putExtra("toCall", String.valueOf(mFeedItems.get(position).getEntry_id()));
                        i.putExtra("imageFeedEvent", mFeedItems.get(position).getThumb());
                        i.putExtra("titleFeedEvent", mFeedItems.get(position).getTitle());
                        i.putExtra("EVENTS_FROM", "feed_events");
                        startActivity(i);
                    }
                }
            }
        });

        mLvFeed.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {


                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0 && !mEndOfFeed) {
                    if (!mLoading && !mEndOfFeed) {
                        mLoading = true;
                        loadFeedItems();
                    }

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


    public static ArrayList<ModelFeedItem> setBookmarks(ArrayList<ModelFeedItem> mFeedItems, ArrayList<Integer> mBookmarked) {
        for (int i = 0; i < mFeedItems.size(); i++) {
            for (int j = 0; j < mBookmarked.size(); j++) {
                if (mFeedItems.get(i).getId() == mBookmarked.get(j)) {
                    mFeedItems.get(i).setIsBookmarked(true);
                }
            }
        }
        return mFeedItems;
    }


    @Override
    public void onResume() {
        super.onResume();

        if (state == null){
            mPage = 1;
            mFeedItems = new ArrayList<ModelFeedItem>();
        }
        loadFeedItems();

        mRlLoading.setVisibility(View.GONE);

        if (mLvInit && mAdapterFeed != null)
            mAdapterFeed.notifyDataSetChanged();
        mLvFeed.setAdapter(mAdapterFeed);
        if (state != null) {
            mLvFeed.onRestoreInstanceState(state);
        }


    }


    @Override
    public void onPause() {
        // Save ListView state @ onPause
        state = mLvFeed.onSaveInstanceState();


        super.onPause();
    }


    private void loadFeedItems() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.smartshanghai.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfaceFeedAPI api = retrofit.create(InterfaceFeedAPI.class);

        String url = "feed?key=abc123iphone&page=" + mPage;
        mPage++;

        Call<ModelListOfFeedItems> callListOfFeedItems = api.getFeedListItems(url);
        if (!mEndOfFeed)
            callListOfFeedItems.enqueue(new Callback<ModelListOfFeedItems>() {
                @Override
                public void onResponse(Response<ModelListOfFeedItems> response, Retrofit retrofit) {
                    response.body();

                    mProgressBarFeed.setVisibility(View.GONE);

                    if (response.body().data != null && response.body().data.size() != 0) {
                        for (int i = 0; i < response.body().data.size(); i++) {
                            mFeedItems.add(new ModelFeedItem(
                                    response.body().data.get(i).getId(),
                                    response.body().data.get(i).getType_id(),
                                    response.body().data.get(i).getEntry_id(),
                                    response.body().data.get(i).getTitle(),
                                    response.body().data.get(i).getThumb(),
                                    response.body().data.get(i).getMeta1(),
                                    response.body().data.get(i).getMeta2(),
                                    response.body().data.get(i).getMeta3(),
                                    response.body().data.get(i).getMeta4(),
                                    response.body().data.get(i).getMeta5(),
                                    response.body().data.get(i).getMeta6(),
                                    response.body().data.get(i).getMeta7(),
                                    response.body().data.get(i).getMeta8(),
                                    response.body().data.get(i).getMeta9(),
                                    response.body().data.get(i).getMeta10(),
                                    response.body().data.get(i).getMeta11(),
                                    response.body().data.get(i).getMeta12(),
                                    response.body().data.get(i).getMeta13(),
                                    response.body().data.get(i).getMeta14(),
                                    response.body().data.get(i).getMeta15(),
                                    response.body().data.get(i).getMeta16(),
                                    response.body().data.get(i).getDate_published(),
                                    response.body().data.get(i).getLast_edited(),
                                    response.body().data.get(i).getDate_timeago(),
                                    response.body().data.get(i).getShare_url(),
                                    response.body().data.get(i).getShare_title(),
                                    response.body().data.get(i).getShare_phrase()
                            ));
                        }
                    }



                    if (mUserId > 0 && isAdded()) {
                        mBookmarked = new ArrayList<Integer>();

                        String key = getResources().getString(R.string.key_webservices_android);
                        String userId = String.valueOf(mUserId);
                        String url = "?key=" + key + "&userId=" + userId;

                        Retrofit retrofitBooked = new Retrofit.Builder()
                                .baseUrl("http://smartshanghai.com/api/feed/bookmarks")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        InterfaceBookmarkedAPI apiBooked = retrofitBooked.create(InterfaceBookmarkedAPI.class);


                        Call<ModelListOfFeedItems> callListOfFeedItemsBookmarked = apiBooked.getBookmarked(url);
                        callListOfFeedItemsBookmarked.enqueue(new Callback<ModelListOfFeedItems>() {
                            @Override
                            public void onResponse(Response<ModelListOfFeedItems> response, Retrofit retrofit) {
                                response.body();

                                if (response.body().data != null && response.body().data.size() != 0) {
                                    for (int i = 0; i < response.body().data.size(); i++) {
                                        mBookmarked.add(
                                                response.body().data.get(i).getId());
                                    }
                                }

                                mFeedItems = setBookmarks(mFeedItems, mBookmarked);


                            }

                            @Override
                            public void onFailure(Throwable t) {

                            }
                        });


                    }

                    MainActivity mainActivity = (MainActivity) getActivity();

                    mLvInit = true;
                    if (mAdapterFeed == null && getActivity() != null) { // SHOULD FIX THE PROBLEM OF LINE 59 IN LISTOFFEEDITEMADAPTER
                        mAdapterFeed = new ListOfFeedItemsAdapter(mFeedItems, getActivity(), mUserId, mainActivity);
                        mLvFeed.setAdapter(mAdapterFeed);
                    }

                    if (response.body().data != null && mAdapterFeed != null) {
                        mAdapterFeed.notifyDataSetChanged();
                    }

                    mLoading = false;
                    if (response.body().data == null) {
                        mEndOfFeed = true;
                    }

                }

                @Override
                public void onFailure(Throwable t) {

                    mProgressBarFeed.setVisibility(View.GONE);
                }
            });
    }
}
