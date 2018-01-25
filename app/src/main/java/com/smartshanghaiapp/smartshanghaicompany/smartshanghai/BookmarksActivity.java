package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper.InterfaceBookmarkedAPI;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage.FragmentFeed;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage.MainActivity;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelFeedItem;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfFeedItems;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class BookmarksActivity extends Activity implements Callback<ModelListOfFeedItems> {

    private ArrayList<ModelFeedItem> mFeedItems;
    private ListView mLvBookmarked;

    private SharedPreferences mSharedPreferences;
    private int mIdUser;

    private ListOfFeedItemsAdapter mAdapter;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);

        mContext = this;

        mFeedItems = new ArrayList<ModelFeedItem>();
        mLvBookmarked = (ListView) findViewById(R.id.lv_bookmark_activity_bookmarked);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mIdUser = mSharedPreferences.getInt("userID", 0);


        String key = getResources().getString(R.string.key_webservices_android);
        String userId = String.valueOf(mIdUser);
        String url = "?key=" + key + "&userId=" + userId;

        if (mIdUser > 0) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://smartshanghai.com/api/feed/bookmarks")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            InterfaceBookmarkedAPI api = retrofit.create(InterfaceBookmarkedAPI.class);


            Call<ModelListOfFeedItems> callListOfFeedItems = api.getBookmarked(url);
            callListOfFeedItems.enqueue(this);

        }
    }

    @Override
    public void onResponse(Response<ModelListOfFeedItems> response, Retrofit retrofit) {
        response.body();

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
                        true, // IS BOOKMARKED
                        response.body().data.get(i).getShare_url(),
                        response.body().data.get(i).getShare_title(),
                        response.body().data.get(i).getShare_phrase()

                        ));
            }
        }

        mAdapter = new ListOfFeedItemsAdapter(mFeedItems, mContext, mIdUser, (MainActivity) getParent());
        mLvBookmarked.setAdapter(mAdapter);

    }

    @Override
    public void onFailure(Throwable t) {

    }
}
