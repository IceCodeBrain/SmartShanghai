package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper.InterfacePostReviewAPI;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelResponsePostReview;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelReviewToPost;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ReviewingVenueActivity1 extends Activity implements Callback<ModelResponsePostReview> {

    private RatingBar mRbTotalRating;
    private TextView mTvRate;


    private Button mButSendReview;

    private EditText mEtRevQuote;


    private int mIdVenue;
    private String mUsername;
    private String mVenueName;

    private RatingBar mRbValue;
    private RatingBar mRbFood;
    private RatingBar mRbDrinks;
    private RatingBar mRbService;
    private RatingBar mRbAtmosphere;
    private RatingBar mRbForDate;
    private RatingBar mRbForFamily;
    private RatingBar mRbForBusiness;
    private RatingBar mRbForFriends;

    private TextView mTvUsername;
    private TextView mTvVenueName;

    private EditText mEtRevTxt;

    private RelativeLayout mRlLoading;

    private Button mButSend;

    private SharedPreferences mPreferencesUserInfos;

    private int mVenueID;
    private int mTotalRating;
    private String mRev_quote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviewing_venue);


        mIdVenue = getIntent().getExtras().getInt(VenuePageActivity.ID_VENUE_SELECTED_FOR_REVIEW);
        mVenueName = getIntent().getExtras().getString(VenuePageActivity.VENUE_SELECTED_FOR_REVIEW_NAME);

        mPreferencesUserInfos = PreferenceManager.getDefaultSharedPreferences(this);
        mUsername = mPreferencesUserInfos.getString("Username", "not connected");

        mRbTotalRating = (RatingBar) findViewById(R.id.rb_activity_review_venues_total_rating);
        mTvVenueName = (TextView) findViewById(R.id.tv_reviewing_activity_venue_name);
        mTvUsername = (TextView) findViewById(R.id.tv_reviewing_activity_username);
        mTvRate = (TextView) findViewById(R.id.tv_reviewing_activity_rate_total);
        mEtRevQuote = (EditText) findViewById(R.id.et_activity_review_review_quote);

        mRbValue = (RatingBar) findViewById(R.id.rb_reviewing_activity_2_value);
        mRbFood = (RatingBar) findViewById(R.id.rb_reviewing_activity_2_food);
        mRbDrinks = (RatingBar) findViewById(R.id.rb_reviewing_activity_2_drinks);
        mRbService = (RatingBar) findViewById(R.id.rb_reviewing_activity_2_service);
        mRbAtmosphere = (RatingBar) findViewById(R.id.rb_reviewing_activity_2_atmosphere);
        mRbForDate = (RatingBar) findViewById(R.id.rb_reviewing_activity_2_date);
        mRbForFamily = (RatingBar) findViewById(R.id.rb_reviewing_activity_2_family);
        mRbForBusiness = (RatingBar) findViewById(R.id.rb_reviewing_activity_2_business);
        mRbForFriends = (RatingBar) findViewById(R.id.rb_reviewing_activity_2_friends);

        mRlLoading = (RelativeLayout) findViewById(R.id.rl_reviewing_activity_loading);

        mEtRevTxt = (EditText) findViewById(R.id.et_activity_review_review_text_2);

        mTvUsername.setText(mUsername);
        mTvVenueName.setText("@ " + mVenueName);

        mRbTotalRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                mTvRate.setText(String.valueOf((int) rating));
            }
        });

        mButSend = (Button) findViewById(R.id.but_reviewing_venue_activity_next_2);
        mButSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mEtRevQuote.getText().toString().equals("")) {
                    Toast.makeText(getBaseContext(), "The review must have a title", Toast.LENGTH_SHORT).show();
                } else {

                    mRlLoading.setVisibility(View.VISIBLE);

                    String baseUrl = "http://www.smartshanghai.com/api/venue/" + String.valueOf(mIdVenue) + "/";

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(baseUrl)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();


                    InterfacePostReviewAPI api = retrofit.create(InterfacePostReviewAPI.class);

                    //requiered
                    ModelReviewToPost modelReviewToPost = new ModelReviewToPost();
                    modelReviewToPost.setKey(getResources().getString(R.string.key_webservices_android));
                    modelReviewToPost.setEmailAddress(mPreferencesUserInfos.getString("EmailAddress", "no data"));
                    modelReviewToPost.setPassword(mPreferencesUserInfos.getString("Password", "no data"));
                    modelReviewToPost.setUser_id(mPreferencesUserInfos.getInt("userID", 0));
                    modelReviewToPost.setRev_city(1);
                    modelReviewToPost.setRev_quote(mEtRevQuote.getText().toString());
                    modelReviewToPost.setRev_venue(mIdVenue);
                    modelReviewToPost.setRev_totalrating((int) mRbTotalRating.getRating());
                    modelReviewToPost.setSource_id(3);

                    //optionals
                    modelReviewToPost.setRev_food((int) mRbFood.getRating());
                    modelReviewToPost.setRev_service((int) mRbService.getRating());
                    modelReviewToPost.setRev_txt(mEtRevTxt.getText().toString());
                    modelReviewToPost.setRev_decor((int) mRbAtmosphere.getRating());
                    modelReviewToPost.setRev_value((int) mRbValue.getRating());
                    modelReviewToPost.setRev_drinks((int) mRbDrinks.getRating());
                    modelReviewToPost.setRev_goodforfriends((int) mRbForFriends.getRating());
                    modelReviewToPost.setRev_goodforbusiness((int) mRbForBusiness.getRating());
                    modelReviewToPost.setRev_goodforfamily((int) mRbForFamily.getRating());
                    modelReviewToPost.setRev_goodfordate((int) mRbForDate.getRating());

                    // JUST BELOW CREATE AN OBJECT RESPONSE
                    Call<ModelResponsePostReview> callToPostAReview = api.postReview(
                            modelReviewToPost.getKey(),
                            modelReviewToPost.getEmailAddress(),
                            modelReviewToPost.getPassword(),
                            modelReviewToPost.getUser_id(),
                            modelReviewToPost.getRev_city(),
                            modelReviewToPost.getRev_quote(),
                            modelReviewToPost.getRev_venue(),
                            modelReviewToPost.getRev_totalrating(),
                            modelReviewToPost.getSource_id(),
                            modelReviewToPost.getRev_food(),
                            modelReviewToPost.getRev_service(),
                            modelReviewToPost.getRev_txt(),
                            modelReviewToPost.getRev_decor(),
                            modelReviewToPost.getRev_value(),
                            modelReviewToPost.getRev_drinks(),
                            modelReviewToPost.getRev_goodforfriends(),
                            modelReviewToPost.getRev_goodforbusiness(),
                            modelReviewToPost.getRev_goodforfamily(),
                            modelReviewToPost.getRev_goodfordate());

                    callToPostAReview.enqueue(ReviewingVenueActivity1.this);


                }
            }

        });


    }


    @Override
    public void onResponse(Response<ModelResponsePostReview> response, Retrofit retrofit) {
        mRlLoading.setVisibility(View.GONE);
        if (response.body().getMessage().equals("ok")) {
            Toast.makeText(this, "Posted", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(ReviewingVenueActivity1.this, ReviewingVenueActivity3.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        mRlLoading.setVisibility(View.GONE);

        Toast.makeText(this, "Connection problem", Toast.LENGTH_SHORT).show();
    }
}
