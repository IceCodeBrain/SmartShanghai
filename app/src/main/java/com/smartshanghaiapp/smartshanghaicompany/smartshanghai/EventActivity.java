package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.DraweeView;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.DatabaseDirectory.Database;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper.InterfaceAdvertisementEventAPI;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelEvent;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelEventInfoFromDB;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfAd;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelAd;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class EventActivity extends Activity {

    private ModelEvent mEvent;

    private TextView mTvTitle;
    private TextView mTvdescription;
    private TextView mTvVenueName;
    private TextView mTvAddress;
    private TextView mTvDateAndHour;
    private TextView mTvPhoneNumber;

    private ImageView mIvEventImage;
    private ImageView mIvVenueNameImage;
    private ImageView mIvAddressImage;
    private ImageView mIvDateAndHourImage;
    private ImageView mIvBuyTucketsImage;
    private ImageView mIvPhoneNumberImage;
    private ImageView mIvBigSizeImageDialog;

    private DraweeView mDvAd;

    private Dialog dialog;

    private Uri mUriImage;
    private Uri mUriImageAd;


    private ModelEventInfoFromDB mDbInfos;

    private RelativeLayout mRlPhone;

    private RelativeLayout mRlShare;
    private RelativeLayout mRlReport;
    private RelativeLayout mRlFavorite;

    private ImageView mIvFavorite;

    private boolean mIsFavorite;

    public final static String KEY_BUNDLE_EVENT_TO_REPORT_MISTAKE_ID = "key bundle event to report mistake id";
    public final static String KEY_BUNDLE_EVENT_TO_REPORT_MISTAKE_EVENT_NAME = "key bundle event to report mistake event name";

    public final static String KEY_BUNDLE_WEB_ADDRESS_FROM_EVENT = "bundle_web_address_from_event_activity";

    public final static String KEY_BUNDLE_WEB_ADDRESS_AD_EVENT = "key bundle web address ad event";

    public final static String KEY_BUNDLE_ID_TO_VENUE = "key bundle id to venue";


    private Database mDatabase;

    private SharedPreferences mSharedPreferences;
    private boolean mDbIsUsable;

    private ModelAd mAd;
    private Intent mIntent;
    private String mTocall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);


        mDatabase = new Database(this);
        mIntent = getIntent();


        if (ListOfEvent.KEY_BUNDLE_EVENT != null) {
            mEvent = getIntent().getExtras().getParcelable(ListOfEvent.KEY_BUNDLE_EVENT);
        }
        if (FragmentListOfDayEvent.KEY_BUNDLE_DAY_EVENT != null) {
            mEvent = getIntent().getExtras().getParcelable(FragmentListOfDayEvent.KEY_BUNDLE_DAY_EVENT);
        }

        mTocall = mIntent.getStringExtra("key_bundle_event_cat");

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mDbIsUsable = mSharedPreferences.getBoolean("dbIsUsable", false);

        mTvTitle = (TextView) findViewById(R.id.tv_event_activity_title_event);
        mTvVenueName = (TextView) findViewById(R.id.tv_event_activity_venue_name);
        mTvAddress = (TextView) findViewById(R.id.tv_event_activity_address);
        mTvDateAndHour = (TextView) findViewById(R.id.tv_event_activity_date_and_hour);
        mTvPhoneNumber = (TextView) findViewById(R.id.tv_event_activity_phone);
        mTvdescription = (TextView) findViewById(R.id.tv_event_activity_description);

        mIvEventImage = (ImageView) findViewById(R.id.iv_event_activity_image);
        mIvAddressImage = (ImageView) findViewById(R.id.iv_event_activity_image_address);
        mIvDateAndHourImage = (ImageView) findViewById(R.id.iv_event_activity_image_date_and_hour);
        mIvBuyTucketsImage = (ImageView) findViewById(R.id.iv_event_activity_image_buy_tickets);
        mIvPhoneNumberImage = (ImageView) findViewById(R.id.iv_event_activity_image_phone);
        mDvAd = (DraweeView) findViewById(R.id.iv_event_activity_ad);
        mRlPhone = (RelativeLayout) findViewById(R.id.rl_event_activity_phone_number);
        mRlShare = (RelativeLayout) findViewById(R.id.rl_event_page_activity_share);
        mRlReport = (RelativeLayout) findViewById(R.id.rl_event_page_activity_report);

        setFavoriteBehavior(this);

        if (mEvent.getVenue_id() > 0) {
            if (mDbIsUsable)
                mDbInfos = mDatabase.getEventInfoDB(mEvent.getVenue_id());
            else {
                Toast.makeText(this, "You must download the database to get the venue address", Toast.LENGTH_LONG).show();
            }
        }
        if (mDbInfos != null) {
            mTvAddress.setText(mDbInfos.getAddress_en());
            mTvVenueName.setText("@ " + mDbInfos.getName());
            if (!mDbInfos.getPhone().equals(null) && !mDbInfos.getPhone().equals("")) {
                mTvPhoneNumber.setText(mDbInfos.getPhone());
                mRlPhone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", mDbInfos.getPhone(), null));
                        startActivity(intent);
                    }
                });
            } else {
                mRlPhone.setVisibility(View.GONE);
            }

            mTvVenueName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mDatabase.checkIfVenueExist(mEvent.getVenue_id())) {
                        Intent i = new Intent(EventActivity.this, VenuePageActivity.class);
                        i.putExtra(KEY_BUNDLE_ID_TO_VENUE, mEvent.getVenue_id());
                        startActivity(i);
                    }
                }
            });

        }

        mRlReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EventActivity.this, ReportMistakesActivity.class);
                i.putExtra("REPORT MISTAKE FROM", "eventpage");
                i.putExtra(KEY_BUNDLE_EVENT_TO_REPORT_MISTAKE_ID, mEvent.getId());
                i.putExtra(KEY_BUNDLE_EVENT_TO_REPORT_MISTAKE_EVENT_NAME, mEvent.getName());
                startActivity(i);
            }
        });

        if (mEvent.getSmartticket_link() != null) {
            mIvBuyTucketsImage.setVisibility(View.VISIBLE);
            mIvBuyTucketsImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(EventActivity.this, OnlineFrameActivity.class);
                    i.putExtra(KEY_BUNDLE_WEB_ADDRESS_FROM_EVENT, mEvent.getSmartticket_link());
                    startActivity(i);
                }
            });
        } else {
            mIvBuyTucketsImage.setVisibility(View.GONE);
        }


        if (mEvent.getPrevpix() != null && mEvent.getFlyer() != null) {
            mUriImage = Uri.parse("http://www.smartshanghai.com/uploads/events/flyer/" + mEvent.getFlyer());
            mIvEventImage.setImageURI(mUriImage);


            mIvEventImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog = createCustomDialogDisplayImage();
                    dialog.show();
                }
            });
        } else {
            mIvEventImage.setVisibility(View.GONE);
        }

        mTvTitle.setText(mEvent.getName());
        mTvVenueName.setText("@ " + mEvent.getVenue_name());
        mTvdescription.setText(mEvent.getDescription_short_nohtml());

        if (mEvent.getDate_human() != null) {
            mTvDateAndHour.setText(mEvent.getDate_human());
        }
        if (mEvent.getDate_featured() != null) {
            mTvDateAndHour.setText(mEvent.getDate_featured());
        }

        mRlShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toBeShared = mEvent.getName() + " is an event on SmartShanghai : " + "http://www.smartshanghai.com/event/" + mEvent.getId();

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, toBeShared);
                startActivity(Intent.createChooser(intent, "Share an event"));
            }
        });


        Retrofit retrofitAd = new Retrofit.Builder()
                .baseUrl("http://www.smartshanghai.com/api/ads")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfaceAdvertisementEventAPI apiAd = retrofitAd.create(InterfaceAdvertisementEventAPI.class);

        String campaignId;
        if (mTocall.equals("nightlife")) {
            campaignId = "21";
        } else if (mTocall.equals("brunchDeals") || mTocall.equals("lunchDeals")) {
            campaignId = "22";
        } else {
            campaignId = "23";
        } // HERE TO CHOOSE THE KIND OF ADS THAT WILL BE SHOWN


        Call<ModelListOfAd> callListAd = apiAd.getAds("?sectionId=" + campaignId + "&limit=1&key=" + getResources().getString(R.string.key_webservices_android));
        callListAd.enqueue(new Callback<ModelListOfAd>() {

                               @Override
                               public void onResponse(Response<ModelListOfAd> response, Retrofit retrofit) {
                                   response.body();
                                   if (response.body() != null && response.body().data != null && response.body().data.size() > 0 ) {
                                       mAd = new ModelAd(
                                               response.body().data.get(0).getBannerId()
                                       );
                                       mUriImageAd = Uri.parse("http://www.smartshanghai.com/mar/" + mAd.getBannerId() + "?source=2");
                                       mDvAd.setImageURI(mUriImageAd);
                                   }else{
                                       mDvAd.setVisibility(View.GONE);
                                   }


                               }

                               @Override
                               public void onFailure(Throwable t) {
                                   mDvAd.setVisibility(View.GONE);
                               }
                           }
        );


        mDvAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAd.getBannerId() > 0) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    // We may add "source=2" when the service is ready
                    i.setData(Uri.parse("http://www.smartshanghai.com/adclick.php?bannerId=" + mAd.getBannerId()));
                    startActivity(i);
                }
            }
        });
    }


    private Dialog createCustomDialogDisplayImage() {
        final Dialog dialog = new Dialog(EventActivity.this);
        dialog.setContentView(R.layout.custom_layout_display_image_event);
        mIvBigSizeImageDialog = (ImageView) dialog.findViewById(R.id.iv_custom_event_big_size_image_dialog);

        mIvBigSizeImageDialog.setImageURI(mUriImage);

        mIvBigSizeImageDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        return dialog;
    }


    private void setFavoriteBehavior(Context ctx) {
        mRlFavorite = (RelativeLayout) findViewById(R.id.rl_event_page_activity_add_favorites);
        mIvFavorite = (ImageView) findViewById(R.id.iv_event_page_activity_add_favorites);


        TinyDB tinyDB = new TinyDB(ctx);
        ArrayList<String> mfavoriteList = new ArrayList<String>();
        mfavoriteList = tinyDB.getListString("favoriteListIdEvents");

        if (mfavoriteList.contains(String.valueOf(mEvent.getId()))) {
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
                newFav = tinyDB.getListString("favoriteListIdEvents");
                if (mIsFavorite) {
                    newFav.remove(String.valueOf(mEvent.getId()));
                    tinyDB.putListString("favoriteListIdEvents", newFav);
                    mIvFavorite.setImageResource(R.drawable.ic_venue_not_fav);
                    mIsFavorite = false;
                } else {
                    newFav.add(String.valueOf(mEvent.getId()));
                    tinyDB.putListString("favoriteListIdEvents", newFav);
                    mIvFavorite.setImageResource(R.drawable.ic_venue_fav);
                    mIsFavorite = true;
                }
            }
        });

    }


}
