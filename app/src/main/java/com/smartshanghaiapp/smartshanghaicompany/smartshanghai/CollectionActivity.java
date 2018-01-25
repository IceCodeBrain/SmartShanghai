package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper.InterfaceCollectionResultAPI;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage.FragmentDirectory;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage.FragmentFeed;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage.MainActivity;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelCollection;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelCollectionResult;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfCollectionResult;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class CollectionActivity extends Activity implements Callback<ModelListOfCollectionResult> {

    private ListView mLvCollection;
    private int mIdCollection;
    private ProgressBar mPgCollection;
    private ArrayList<ModelCollectionResult> mCollection;
    private double mXuser;
    private double mYuser;
    private String mDistanceToVenue;
    private CollectionAdapter mAdapterCollection;
    private SharedPreferences mSharedPreferences;

    private ArrayList<ModelCollectionResult> mFinalList;

    private ModelCollection modelCollection;

    private boolean mDbIsUsable;


    public final static String KEY_BUNDLE_VENUE_ACTIVITY_FROM_COLLECTION_ACTIVITY = "Key_bundle_venue_activity_from_collection_activity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mDbIsUsable = mSharedPreferences.getBoolean("dbIsUsable", false);

        mLvCollection = (ListView) findViewById(R.id.lv_collection_activity_collection);

        modelCollection = null;
        modelCollection = getIntent().getExtras().getParcelable(ListOfCollections.KEY_BUNDLE_COLLECTION_FROM_COLLECTIONS);
        if (modelCollection == null) {
            modelCollection = getIntent().getExtras().getParcelable(FragmentDirectory.KEY_BUNDLE_COLLECTION_FROM_DIRECTORY);
        }
        if (modelCollection == null) {
            modelCollection = getIntent().getExtras().getParcelable(FragmentFeed.KEY_BUNDLE_COLLECTION_SELECTED);
        }


        mPgCollection = (ProgressBar) findViewById(R.id.load_collection_activity);
        mCollection = new ArrayList<ModelCollectionResult>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://smartshanghai.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfaceCollectionResultAPI api = retrofit.create(InterfaceCollectionResultAPI.class);


        Call<ModelListOfCollectionResult> callListOfCollectionResult = api.getCollection("collection/" + modelCollection.getTag_id() + "?key=abc123iphone");
        callListOfCollectionResult.enqueue(this);


        mLvCollection.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    //nothing , this isthe title
                } else if (position > 0) {
                    if (mDbIsUsable) {
                        Intent i = new Intent(CollectionActivity.this, VenuePageActivity.class);
                        i.putExtra(KEY_BUNDLE_VENUE_ACTIVITY_FROM_COLLECTION_ACTIVITY, mCollection.get(position - 1).getId());
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplication(), "You must download the database", Toast.LENGTH_LONG).show();

                    }


                }
            }
        });


    }

    @Override
    public void onResponse(Response<ModelListOfCollectionResult> response, Retrofit retrofit) {
        response.body();
        mPgCollection.setVisibility(View.GONE);
        if (response.body() != null) {
            for (int i = 0; i < response.body().data.size(); i++) {
                mCollection.add(new ModelCollectionResult(
                        response.body().data.get(i).getId(),
                        response.body().data.get(i).getVenueName(),
                        response.body().data.get(i).getAddressEnglish(),
                        response.body().data.get(i).getAddressChinese(),
                        response.body().data.get(i).getVenueTagDescription(),
                        response.body().data.get(i).getVenueTagThumb(),
                        distanceToVenueAnd(response.body().data.get(i).getMap_x(), response.body().data.get(i).getMap_y(), MainActivity.mXGooglePosition, MainActivity.mYGooglePosition),
                        0 // means it is in the middle of the page
                ));
            }

            mFinalList = new ArrayList<ModelCollectionResult>();
            mFinalList.add(new ModelCollectionResult( // THIS IS THE TOP OF THE PAGE
                    0,
                    modelCollection.getTitle(), // name
                    modelCollection.getLast_edited(), // address en
                    "", // address cn
                    modelCollection.getDescription(), //description
                    modelCollection.getThumb(), //thumb
                    "", // distance
                    1 // means top of the page
            ));

            mFinalList.addAll(mCollection);

            //here should add the foot page

            mLvCollection.setVisibility(View.VISIBLE);
            mAdapterCollection = new CollectionAdapter(mFinalList, this);
            mLvCollection.setAdapter(mAdapterCollection);


        }


    }

    @Override
    public void onFailure(Throwable t) {
        mPgCollection.setVisibility(View.GONE);
        Toast.makeText(this, "connection problem", Toast.LENGTH_SHORT).show();

    }

    private String distanceToVenueAnd(double venX, double venY, double userX, double userY) {
        Location locVen = new Location("");
        locVen.setLatitude(venX);
        locVen.setLongitude(venY);
        Location locUser = new Location("");
        locUser.setLatitude(userX);
        locUser.setLongitude(userY);
        double distance = locUser.distanceTo(locVen);
        double distanceKm = distance / 1000;
        if (distanceKm > 1000) {
            return "no data";
        }
        double distanceFormat = (int) Math.round(distanceKm * 100) / (double) 100;
        String distanceStr = Double.toString(distanceFormat);
        return distanceStr;
    }

}
