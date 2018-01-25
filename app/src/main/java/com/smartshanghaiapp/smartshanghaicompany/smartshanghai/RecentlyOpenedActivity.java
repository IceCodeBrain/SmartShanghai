package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.DatabaseDirectory.Database;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper.InterfaceCollectionResultAPI;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage.MainActivity;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelCollectionResult;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfCollectionResult;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class RecentlyOpenedActivity extends Activity implements Callback<ModelListOfCollectionResult> {

    private ArrayList<ModelCollectionResult> mCollectionsResults;
    private ListView mLvCollection;
    private ProgressBar mPg;
    private CollectionAdapter mAdapterCollection;

    private Database mDatabase;

    public final static String ID_VENUE_SELECTED_FROM_RECENTLY_OPENED = "id venue selectedfrom recently opened";
    public final static String ID_VENUE_SELECTED_FROM_RECENTLY_OPENED_TO_REQUEST = "id venue selectedfrom recently opened to request";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recently_opened);

        mPg = (ProgressBar) findViewById(R.id.load_collection_recently_opened_activity);
        mLvCollection = (ListView) findViewById(R.id.lv_recently_opened_activity_collection);
        mCollectionsResults = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://smartshanghai.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfaceCollectionResultAPI api = retrofit.create(InterfaceCollectionResultAPI.class);

        // 305 is the id of recently opened collection
        Call<ModelListOfCollectionResult> callListOfCollectionResult = api.getCollection("collection/" + 305 + "?key=abc123iphone");
        callListOfCollectionResult.enqueue(this);

        mDatabase = new Database(this);


        mLvCollection.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                boolean venueExist = mDatabase.checkIfVenueExist(mCollectionsResults.get(position).getId());
                if (venueExist){
                    Intent i = new Intent(RecentlyOpenedActivity.this, VenuePageActivity.class);
                    i.putExtra(ID_VENUE_SELECTED_FROM_RECENTLY_OPENED, mCollectionsResults.get(position).getId());
                    startActivity(i);
                } else{
                    Intent i = new Intent(RecentlyOpenedActivity.this, RequestUpdateActivity.class);
                    i.putExtra(ID_VENUE_SELECTED_FROM_RECENTLY_OPENED_TO_REQUEST, mCollectionsResults.get(position).getId());
                    startActivity(i);
                }

            }
        });

    }


    @Override
    public void onResponse(Response<ModelListOfCollectionResult> response, Retrofit retrofit) {

        response.body();

        mPg.setVisibility(View.GONE);

        if (response.body() != null) {
            for (int i = 0; i < response.body().data.size(); i++) {
                mCollectionsResults.add(new ModelCollectionResult(
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
        }

        mLvCollection.setVisibility(View.VISIBLE);
        mAdapterCollection = new CollectionAdapter(mCollectionsResults, this);
        mLvCollection.setAdapter(mAdapterCollection);

    }

    @Override
    public void onFailure(Throwable t) {
        mPg.setVisibility(View.GONE);
        Toast.makeText(this, "connection problem",
                Toast.LENGTH_SHORT).show();
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
