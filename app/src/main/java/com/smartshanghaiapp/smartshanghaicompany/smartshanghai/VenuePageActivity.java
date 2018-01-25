package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.DraweeView;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.DatabaseDirectory.Database;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper.InterfaceReviewVenuesAPI;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper.InterfaceVenuePicturesAPI;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage.FragmentDirectory;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage.FragmentFeed;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfReviewsVenue;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfVenueImage;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelReviewVenue;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelVenue;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelVenueImage;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class VenuePageActivity extends Activity implements Callback<ModelListOfReviewsVenue> {

    private ModelVenue mVenue;
    private Database mDatabase;
    private int mId;

    private TextView mVenueName;
    private TextView mVenueAddressEn;
    private TextView mVenueAddressCn;
    private TextView mVenuePhone;
    private TextView mVenueHours;
    private TextView mVenueMetro;
    private TextView mVenueCards;
    private TextView mVenueWebsite;
    private TextView mVenueDescription;
//    private TextView mVenueNumberReviews;
    private DraweeView mDvImageVenue;

    private TextView mVenuePrice1;
    private TextView mVenuePrice2;
    private TextView mVenuePrice3;
    private TextView mVenuePrice4;
    private TextView mVenuePrice5;

    private RelativeLayout rlPrice;
    private RelativeLayout rlWebsite;
    private RelativeLayout rlCards;
    private RelativeLayout rlPhone;
    private RelativeLayout rlHours;
    private RelativeLayout rlAddressEn;
    private RelativeLayout rlAddressCn;
    private RelativeLayout rlMetro;
    private RelativeLayout rlChope;

    private RelativeLayout mRlNearby;

    private RelativeLayout rlFavoriteButton;
    private RelativeLayout rlShareButton;

    private CarouselView carouselView;
    private ArrayList<ModelVenueImage> mVenuePictures;
    private ProgressBar mProgressBarCarousel;
    private DraweeView mDvImageCarousel;

    private ImageView mIvFavorite;

    private ImageView mIvBigSizeImageDialog;

    private SharedPreferences mPreferences;

    private Dialog dialog;
    private Uri mUriImage;

//    private ArrayList<ModelReviewVenue> mReviewsVenue;

//    private RecyclerView mRvReviews;
//    private ReviewsVenueRecyclerViewAdapter mAdapterRecyclerViewReviews;

    private TextView mButRateVenue;

    private RelativeLayout mRlReportMistake;
    private RelativeLayout mRlFavorite;

    private boolean mIsFavorite;

    private RelativeLayout mRlClosed;

    private TextView mTvFavorite;

    public final static String queryNearby = "nearby venues";
    public final static String queryVenueX = "query venue x";
    public final static String queryVenueY = "query venue y";
    public final static String queryIdVenue = "query id  venue";
    public final static String queryVenueFrom = "query venue from";


    public final static String ID_VENUE_SELECTED_FOR_REVIEW = "no id selected for review";
    public final static String VENUE_SELECTED_FOR_REVIEW_NAME = "venue selected for review name";


    public final static String KEY_BUNDLE_VENUE_TO_REPORT_MISTAKE_ID = "key bundle venue to report mistake id";
    public final static String KEY_BUNDLE_VENUE_TO_REPORT_MISTAKE_VENUE_NAME = "key bundle venue to report mistake venue name";

    public final static String KEY_BUNDLE_WEB_ADDRESS_FROM_VENUE = "Key_bundle_web_address_from_venue";

    public final static String KEY_BUNDLE_CHINESE_ADDRESS_FROM_VENUE_ACT = "Key_bundle_chinese_address_from_venue_act";
    public final static String KEY_BUNDLE_ENGLISH_ADDRESS_FROM_VENUE_ACT = "Key_bundle_english_address_from_venue_act";

    public final static String KEY_BUNDLE_ID_VENUE_FROM_VENUE_ACT = "KEY_BUNDLE_ID_VENUE_FROM_VENUE_ACT";

    public final static String KEY_BUNDLE_WEB_ADDRESS_VENUE_CHOPE = "key bundle web address venue chope";

    public final static String KEY_BUNDLE_ID_VENUE_FROM_VENUE_ACT_TV = "key bundle id venue from veneue act tv";




    private TextView mTvDescTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_page);


        mDatabase = new Database(this);

        mId = getIntent().getExtras().getInt(DirectoryListing.ID_ITEM_SELECTED);
        if (DirectoryListing.ID_ITEM_SELECTED != null) {
            mId = getIntent().getExtras().getInt(DirectoryListing.ID_ITEM_SELECTED);
        }
        if (mId == 0) {
            mId = getIntent().getExtras().getInt(GoogleMapActivity.ID_VENUE_SELECTED_FROM_MAP);
        }
        if (mId == 0) {
            mId = getIntent().getExtras().getInt(FragmentDirectory.KEY_BUNDLE_ID_ITEM_SEARCHED_SELECTED);
        }
        if (mId == 0) {
            mId = getIntent().getExtras().getInt(FragmentFeed.KEY_BUNDLE_VENUE_ACTIVITY);
        }
        if (mId == 0) {
            mId = getIntent().getExtras().getInt(CollectionActivity.KEY_BUNDLE_VENUE_ACTIVITY_FROM_COLLECTION_ACTIVITY);
        }
        if (mId == 0) {
            mId = getIntent().getExtras().getInt(BaiduMapVenuesActivity.ID_VENUE_SELECTED_FROM_BAIDU_MAP);
        }
        if (mId == 0) {
            mId = getIntent().getExtras().getInt(RecentlyOpenedActivity.ID_VENUE_SELECTED_FROM_RECENTLY_OPENED);
        }
        if (mId == 0) {
            mId = getIntent().getExtras().getInt(EventActivity.KEY_BUNDLE_ID_TO_VENUE);
        }
        if (mId == 0) {
            mId = getIntent().getExtras().getInt(MoreListActivity.KEY_BUNDLE_ID_TO_VENUE_FROM_ESSENTIALS);
        }

        if (mDatabase.checkDataBase() == true && mId != 0) {
            mVenue = mDatabase.getAModelVenueFromId(mId);
        }
        if (mVenue != null) {

            mVenueName = (TextView) findViewById(R.id.tv_venue_activity_name);
            mVenueDescription = (TextView) findViewById(R.id.tv_venue_activity_description);
            mVenueAddressEn = (TextView) findViewById(R.id.tv_activity_venue_address_en);
            mVenueAddressCn = (TextView) findViewById(R.id.tv_activity_venue_address_cn);
            mVenuePhone = (TextView) findViewById(R.id.tv_activity_venue_phone_number);
            mVenueHours = (TextView) findViewById(R.id.tv_activity_venue_opening_hours);
            mVenueMetro = (TextView) findViewById(R.id.tv_activity_venue_metro);
            mVenueCards = (TextView) findViewById(R.id.tv_activity_venue_blue_card);
            mVenueWebsite = (TextView) findViewById(R.id.tv_activity_venue_website);
//            mVenueNumberReviews = (TextView) findViewById(R.id.tv_activity_venue_number_of_reviews);
            rlShareButton = (RelativeLayout) findViewById(R.id.rl_venue_page_activity_share);
            rlWebsite = (RelativeLayout) findViewById(R.id.rl_activity_venue_website);
            rlAddressCn = (RelativeLayout) findViewById(R.id.rl_activity_venue_location_cn);
            rlAddressEn = (RelativeLayout) findViewById(R.id.rl_activity_venue_location_en);
            rlPhone = (RelativeLayout) findViewById(R.id.rl_activity_venue_phone_number);
            mTvFavorite = (TextView) findViewById(R.id.tv_venue_page_activity_favorites);
            mTvDescTop = (TextView) findViewById(R.id.tv_venue_activity_description_top);
            mRlNearby = (RelativeLayout) findViewById(R.id.rl_activity_venue_nearby);
            rlChope = (RelativeLayout) findViewById(R.id.rl_activity_venue_chope);
            setFavoriteBehavior(this);


            if (mVenue.getClosed() > 0) {
                mRlClosed = (RelativeLayout) findViewById(R.id.rl_venue_activity_closed);
                mRlClosed.setVisibility(View.VISIBLE);
                TextView tvClosed = (TextView) findViewById(R.id.tv_venue_activity_closed);
                if (mVenue.getClosed() == 1) {
                    tvClosed.setText("This venue has closed");
                } else if (mVenue.getClosed() == 2) {
                    tvClosed.setText("This venue has temporarly closed");
                }
            }

            rlAddressEn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(VenuePageActivity.this, BaiduMapOneVenueActivity.class);
                    i.putExtra(KEY_BUNDLE_ID_VENUE_FROM_VENUE_ACT, mId);
                    startActivity(i);
                }
            });

            mRlNearby.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nearby = "Nearby location";
                    Intent i = new Intent(VenuePageActivity.this, DirectoryListing.class);
                    i.putExtra("LISTING_FROM", "nearby");
                    i.putExtra(queryNearby, "nearby locations");
                    i.putExtra(queryVenueX, mVenue.getMap_x());
                    i.putExtra(queryVenueY, mVenue.getMap_y());
                    i.putExtra(queryIdVenue, mId);
                    i.putExtra(queryVenueFrom, "nearbyLocation");
                    startActivity(i);
                }
            });

            rlAddressCn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(VenuePageActivity.this, TaxiPrintoutActivity.class);
                    i.putExtra(KEY_BUNDLE_CHINESE_ADDRESS_FROM_VENUE_ACT, mVenue.getAddress_cn());
                    i.putExtra(KEY_BUNDLE_ENGLISH_ADDRESS_FROM_VENUE_ACT, mVenue.getAddress_en());
                    startActivity(i);
                }
            });




            mRlReportMistake = (RelativeLayout) findViewById(R.id.rl_venue_page_activity_report);
            mRlReportMistake.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(VenuePageActivity.this, ReportMistakesActivity.class);
                    i.putExtra("REPORT MISTAKE FROM", "venuePage");
                    i.putExtra(KEY_BUNDLE_VENUE_TO_REPORT_MISTAKE_ID, mId);
                    i.putExtra(KEY_BUNDLE_VENUE_TO_REPORT_MISTAKE_VENUE_NAME, mVenue.getName());
                    startActivity(i);
                }
            });

            rlShareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String toBeShared = mVenue.getName() + " is a venue on SmartShanghai : " + "http://www.smartshanghai.com/venue/" + mId;
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, toBeShared);
                    startActivity(Intent.createChooser(intent, "Share a Venue"));
                }
            });


//            mButRateVenue = (TextView) findViewById(R.id.tv_activity_venue_rate_the_venue);
//            mButRateVenue.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
//                    boolean isConnected = mPreferences.getBoolean("isConnected", false);
//                    if (isConnected) {
//                        Intent i = new Intent(VenuePageActivity.this, ReviewingVenueActivity1.class);
//                        i.putExtra(ID_VENUE_SELECTED_FOR_REVIEW, mId);
//                        i.putExtra(VENUE_SELECTED_FOR_REVIEW_NAME, mVenue.getName());
//                        startActivity(i);
//                    }else{
//                        Intent i = new Intent(VenuePageActivity.this, AskSignInActivity.class);
//                        startActivity(i);
//                    }
//                }
//            });


            if (mVenue.getChope_widget_url() != null && mVenue.getChope_widget_url().equals("") == false){
                rlChope.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(mVenue.getChope_widget_url()));
                        startActivity(i);
                    }
                });
            }else{
                rlChope.setVisibility(View.GONE);
            }

            if (mVenue.getPrice() != 0) {
                setThePriceOfVenue(mVenue.getPrice());
            } else {
                rlPrice = (RelativeLayout) findViewById(R.id.rl_activity_venue_price);
                rlPrice.setVisibility(View.GONE);
            }
            mVenueName.setText(mVenue.getName());

            if (mVenue.getDescription() != null && mVenue.getDescription().equals("") == false) {
                mVenueDescription.setText(mVenue.getDescription());
            } else {
                mVenueDescription.setVisibility(View.GONE);
                mTvDescTop.setVisibility(View.GONE);
            }
            if (mVenue.getAddress_en() != null && mVenue.getAddress_en().equals("") == false) {
                mVenueAddressEn.setText(mVenue.getAddress_en());
                mVenueAddressEn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(VenuePageActivity.this, BaiduMapOneVenueActivity.class);
                        i.putExtra(KEY_BUNDLE_ID_VENUE_FROM_VENUE_ACT_TV, mId);
                        startActivity(i);
                    }
                });
            } else {
                rlAddressEn.setVisibility(View.GONE);
            }
            if (mVenue.getAddress_cn() != null && mVenue.getAddress_cn().equals("") == false) {
                mVenueAddressCn.setText(mVenue.getAddress_cn());
            } else {
                rlAddressCn.setVisibility(View.GONE);
            }
            if (mVenue.getHours() != null && mVenue.getHours().equals("") == false) {
                mVenueHours.setText(mVenue.getHours());
            } else {
                rlHours = (RelativeLayout) findViewById(R.id.rl_activity_venue_opening_hours);
                rlHours.setVisibility(View.GONE);
            }
            if (mVenue.getPhone() != null && mVenue.getPhone().equals("") == false) {
                mVenuePhone.setText(mVenue.getPhone());
                rlPhone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", mVenue.getPhone(), null));
                        startActivity(intent);
                    }
                });
            } else {
                rlPhone.setVisibility(View.GONE);
            }
            if (mVenue.getMetro_station_id() != 0 && mVenue.getMetro_station_id() > 0) {
                String metroStr = mDatabase.getNameOfMetroStationFromIdVenue(mVenue.getMetro_station_id());
                mVenueMetro.setText(metroStr);
                if (mVenue.getMetro_distance_time() != 0 && metroStr != null) {
                    String metroAndDistanceStr;
                    metroAndDistanceStr = String.valueOf(mVenue.getMetro_distance_time() + " mins walk from " + metroStr);
                    mVenueMetro.setText(metroAndDistanceStr);
                } else {
                    rlMetro = (RelativeLayout) findViewById(R.id.rl_activity_venue_metro);
                    rlMetro.setVisibility(View.GONE);
                }
            } else {
                rlMetro = (RelativeLayout) findViewById(R.id.rl_activity_venue_metro);
                rlMetro.setVisibility(View.GONE);
            }
            if (mVenue.getCard() != null && mVenue.getCard().equals("") == false) {
                mVenueCards.setText(mVenue.getCard());
            } else {
                rlCards = (RelativeLayout) findViewById(R.id.rl_activity_venue_blue_card);
                rlCards.setVisibility(View.GONE);
            }
            if (mVenue.getWebsite() != null && mVenue.getWebsite().equals("") == false) {
                mVenueWebsite.setText(mVenue.getWebsite());
                rlWebsite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = mVenue.getWebsite();
                        Intent i = new Intent(VenuePageActivity.this, OnlineFrameActivity.class);
                        i.putExtra(KEY_BUNDLE_WEB_ADDRESS_FROM_VENUE, url);
                        startActivity(i);
                    }
                });
            } else {
                rlWebsite.setVisibility(View.GONE);
            }
//            mVenueNumberReviews.setText(String.valueOf(mVenue.getReviews_count()) + " Reviews");

            carouselView = (CarouselView) findViewById(R.id.carouselView_venue_activity);
            if (mVenue.getThumb() != null && mVenue.getThumb().equals("") == false && isNetworkAvailable()) {
                setCarouselOfPictures();
            } else {
                carouselView.setBackgroundResource(R.drawable.noimagesplaceholder);
            }



//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl("http://www.smartshanghai.com/api/venue/")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//
//            InterfaceReviewVenuesAPI api = retrofit.create(InterfaceReviewVenuesAPI.class);
//
//            String url = mId + "/reviews?key=" + getResources().getString(R.string.key_webservices_android);
//
//            Call<ModelListOfReviewsVenue> callListReviews = api.getReviewsVenue(url);
//            callListReviews.enqueue(this);

        }


    }


    @Override
    public void onResponse(Response<ModelListOfReviewsVenue> response, Retrofit retrofit) {
        response.body();

//        Disable Reviews
//        mReviewsVenue = new ArrayList<ModelReviewVenue>();
//        if (response.body() != null) {
//            for (int i = 0; i < response.body().data.size(); i++) {
//                mReviewsVenue.add(new ModelReviewVenue(
//                        response.body().data.get(i).getRev_id(),
//                        response.body().data.get(i).getRev_user(),
//                        response.body().data.get(i).getRev_time(),
//                        response.body().data.get(i).getRev_venue(),
//                        response.body().data.get(i).getRev_quote(),
//                        response.body().data.get(i).getRev_txt(),
//                        response.body().data.get(i).getRev_totalrating(),
//                        response.body().data.get(i).getRev_food(),
//                        response.body().data.get(i).getRev_drinks(),
//                        response.body().data.get(i).getRev_value(),
//                        response.body().data.get(i).getRev_service(),
//                        response.body().data.get(i).getRev_decor(),
//                        response.body().data.get(i).getRev_goodfordate(),
//                        response.body().data.get(i).getRev_goodforbusiness(),
//                        response.body().data.get(i).getRev_goodforfriends(),
//                        response.body().data.get(i).getRev_goodforfamily(),
//                        response.body().data.get(i).getRev_useful(),
//                        response.body().data.get(i).getApproved(),
//                        response.body().data.get(i).getThumb(),
//                        response.body().data.get(i).getName_en(),
//                        response.body().data.get(i).getUsername(),
//                        response.body().data.get(i).getAvatar()
//                ));
//            }
//        }

//        Disable Reviews
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                mAdapterRecyclerViewReviews = new ReviewsVenueRecyclerViewAdapter(mReviewsVenue);
//                ArrayList<ModelReviewVenue> mReviewsVenuesTest = new ArrayList<ModelReviewVenue>();
//                ReviewsVenueRecyclerViewAdapter mAdapterRecyclerViewReviewss = new ReviewsVenueRecyclerViewAdapter(mReviewsVenuesTest);
//
//                mRvReviews = (RecyclerView) findViewById(R.id.lv_venue_page_activity_user_review);
//                LinearLayoutManager llm = new LinearLayoutManager(getBaseContext());
//                llm.setOrientation(LinearLayoutManager.VERTICAL);
//
//                mRvReviews.setLayoutManager(llm);
//                mRvReviews.setNestedScrollingEnabled(false);
//
//                mRvReviews.setAdapter(mAdapterRecyclerViewReviews);
//
//
//            }
//        });
    }


    @Override
    public void onFailure(Throwable t) {

    }

    private void setThePriceOfVenue(int price) {
        mVenuePrice1 = (TextView) findViewById(R.id.tv_activity_venue_price1);
        mVenuePrice2 = (TextView) findViewById(R.id.tv_activity_venue_price2);
        mVenuePrice3 = (TextView) findViewById(R.id.tv_activity_venue_price3);
        mVenuePrice4 = (TextView) findViewById(R.id.tv_activity_venue_price4);
        mVenuePrice5 = (TextView) findViewById(R.id.tv_activity_venue_price5);

        if (price > 0) {
            mVenuePrice1.setTextColor(getResources().getColor(R.color.colorBlackPrice));
            if (price > 1) {
                mVenuePrice2.setTextColor(getResources().getColor(R.color.colorBlackPrice));
                if (price > 2) {
                    mVenuePrice3.setTextColor(getResources().getColor(R.color.colorBlackPrice));
                    if (price > 3) {
                        mVenuePrice4.setTextColor(getResources().getColor(R.color.colorBlackPrice));
                        if (price > 4) {
                            mVenuePrice5.setTextColor(getResources().getColor(R.color.colorBlackPrice));
                        }
                    }
                }
            }
        }
    }

    private void setCarouselOfPictures() {


        Retrofit retrofitVenueImages = new Retrofit.Builder()
                .baseUrl("http://www.smartshanghai.com/api/venue/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfaceVenuePicturesAPI apiPictures = retrofitVenueImages.create(InterfaceVenuePicturesAPI.class);


        Call<ModelListOfVenueImage> callListofPictures = apiPictures.getVenueImages(mId + "/pictures?key=abc123iphone");
        callListofPictures.enqueue(new Callback<ModelListOfVenueImage>() {
            @Override
            public void onResponse(Response<ModelListOfVenueImage> response, Retrofit retrofit) {
                response.body();

                mVenuePictures = new ArrayList<ModelVenueImage>();
                if (response.body() != null) {
                    for (int i = 0; i < response.body().data.size(); i++) {
                        mVenuePictures.add(new ModelVenueImage(
                                response.body().data.get(i).getId(),
                                response.body().data.get(i).getVenue_id(),
                                response.body().data.get(i).getLarge_picture(),
                                response.body().data.get(i).getThumb170x170()
                        ));
                    }

                    ViewListener viewListener = new ViewListener() {
                        @Override
                        public View setViewForPosition(final int position) {
                            final View customView = getLayoutInflater().inflate(R.layout.custom_layout_carousel_venue_activity, null);
                            mDvImageCarousel = (DraweeView) customView.findViewById(R.id.dv_venue_activity_venue_images);
                            if (mVenuePictures.size() != 0) {
                                String str = mVenuePictures.get(position).getLarge_picture();
                                Uri imageUri = Uri.parse("http://www.smartshanghai.com/uploads/venues/images/" + str);
                                mDvImageCarousel.setImageURI(imageUri);

                            }
                            return customView;
                        }


                    };

                    carouselView.setViewListener(viewListener);
                    carouselView.setPageCount(mVenuePictures.size());
                    carouselView.setIndicatorGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);


                } else {
                    carouselView.setBackgroundResource(R.drawable.noimagesplaceholder);
                }


            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "connection problem",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void setFavoriteBehavior(Context ctx) {
        mRlFavorite = (RelativeLayout) findViewById(R.id.rl_venue_page_activity_add_favorites);
        mIvFavorite = (ImageView) findViewById(R.id.iv_venue_page_activity_favorites);


        TinyDB tinyDB = new TinyDB(ctx);
        ArrayList<String> mfavoriteList = new ArrayList<String>();
        mfavoriteList = tinyDB.getListString("favoriteListIdVenues");

        if (mfavoriteList.contains(String.valueOf(mId))) {
            mIvFavorite.setImageResource(R.drawable.ic_venue_fav);
            mIsFavorite = true;
        } else {
            mIvFavorite.setImageResource(R.drawable.ic_venue_not_fav);
            mIsFavorite = false;
        }
        mRlFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TinyDB tinyDB = new TinyDB(getBaseContext());
                ArrayList<String> newFav = new ArrayList<>();
                newFav = tinyDB.getListString("favoriteListIdVenues");
                if (mIsFavorite) {
                    newFav.remove(String.valueOf(mId));
                    tinyDB.putListString("favoriteListIdVenues", newFav);
                    mIvFavorite.setImageResource(R.drawable.ic_venue_not_fav);
                    mIsFavorite = false;
                } else {
                    newFav.add(String.valueOf(mId));
                    tinyDB.putListString("favoriteListIdVenues", newFav);
                    mIvFavorite.setImageResource(R.drawable.ic_venue_fav);
                    mIsFavorite = true;
                }
            }
        });

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

}
