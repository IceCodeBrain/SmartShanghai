package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.Toast;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper.InterfaceReviewsAPI;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfReviewsVenue;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelReviewVenue;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ReviewsActivity extends Activity implements Callback<ModelListOfReviewsVenue> {

    private RecyclerView mLvReviews;
    private ArrayList mReviews;

    private Intent mIntent;

    private String mFirstRev;

    private ReviewsVenueRecyclerViewAdapter mAdapterRecyclerViewReviews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        mIntent = getIntent();
        mFirstRev = mIntent.getStringExtra("key_bundle_first_review");

        mLvReviews = (RecyclerView) findViewById(R.id.lv_reviews_activity_reviews);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.smartshanghai.com/api/reviews")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfaceReviewsAPI apiReview = retrofit.create(InterfaceReviewsAPI.class);
        String url;
        if (mFirstRev != null && !mFirstRev.equals("0") && !mFirstRev.equals("")) {
            url = "?key=" + getResources().getString(R.string.key_webservices_android) + "&reviewId=" + mFirstRev;
        } else {
            url = "?key=" + getResources().getString(R.string.key_webservices_android);
        }
        Call<ModelListOfReviewsVenue> cellReviews = apiReview.getReviews(url);
        cellReviews.enqueue(this);



    }

    @Override
    public void onResponse(Response<ModelListOfReviewsVenue> response, Retrofit retrofit) {

        response.body();

        mReviews = new ArrayList<ModelReviewVenue>();
        if (response.body().data != null && response.body().data.size() != 0) {
            for (int i = 0; i < response.body().data.size(); i++) {
                mReviews.add(new ModelReviewVenue(
                        response.body().data.get(i).getRev_id(),
                        response.body().data.get(i).getRev_user(),
                        response.body().data.get(i).getRev_time(),
                        response.body().data.get(i).getRev_venue(),
                        response.body().data.get(i).getRev_quote(),
                        response.body().data.get(i).getRev_txt(),
                        response.body().data.get(i).getRev_totalrating(),
                        response.body().data.get(i).getRev_food(),
                        response.body().data.get(i).getRev_drinks(),
                        response.body().data.get(i).getRev_value(),
                        response.body().data.get(i).getRev_service(),
                        response.body().data.get(i).getRev_decor(),
                        response.body().data.get(i).getRev_goodfordate(),
                        response.body().data.get(i).getRev_goodforbusiness(),
                        response.body().data.get(i).getRev_goodforfriends(),
                        response.body().data.get(i).getRev_goodforfamily(),
                        response.body().data.get(i).getRev_useful(),
                        response.body().data.get(i).getApproved(),
                        response.body().data.get(i).getThumb(),
                        response.body().data.get(i).getName_en(),
                        response.body().data.get(i).getUsername(),
                        response.body().data.get(i).getAvatar()
                ));
            }

            mAdapterRecyclerViewReviews = new ReviewsVenueRecyclerViewAdapter(mReviews);

            LinearLayoutManager llm = new LinearLayoutManager(getBaseContext());
            llm.setOrientation(LinearLayoutManager.VERTICAL);

            mLvReviews.setLayoutManager(llm);
            mLvReviews.setNestedScrollingEnabled(false);

            mLvReviews.setAdapter(mAdapterRecyclerViewReviews);
        }

    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(this, "connection problem",
                Toast.LENGTH_SHORT).show();
    }
}
