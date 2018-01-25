package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.google.android.gms.maps.model.LatLng;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.DatabaseDirectory.Database;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper.InterfaceAvatarAPI;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage.MainActivity;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelVenue;

import java.util.ArrayList;
import java.util.HashMap;

public class BaiduMapVenuesActivity extends Activity {

    private MapView mBaiduMapView;
    private BaiduMap mBaiduMap;
    private com.baidu.mapapi.model.LatLng mLatLngUserPosition;
    private MyLocationData mLocDataUser;
    private ArrayList<ModelVenue> mArrayListOfVenuesForMarkers;

    private RelativeLayout mButBackListing;

    private Database mDatabase;

    private Button mSwitchGoogleMap;

    private int mIdCatParent;
    private int mIdSubCat;

    private TextView mTvCategorie;
    private String categorie;

    private BitmapDescriptor mBitmapVenue;
    private BitmapDescriptor mBitmapUser;

    private HashMap<OverlayOptions, Integer> mMarkerMap = new HashMap<>();


    public final static String BUNDLE_FROM_BAIDU_ID_PARENT_CAT = "id_cat_parent_from_baidu";
    public final static String BUNDLE_FROM_BAIDU_ID_SUB_CAT = "id_sub_cat_from_baidu";

    public final static String ID_VENUE_SELECTED_FROM_BAIDU_MAP = "bundle_venue_from_baidu_map";

    private Double mXVenue;
    private Double mYVenue;

    private String mLetterSelected;

    private String mComeFrom;

    private int smshId;

    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_baidu_map_venues);

        mBaiduMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mBaiduMapView.getMap();

        mArrayListOfVenuesForMarkers = new ArrayList<ModelVenue>();

        mIntent = getIntent();
        categorie = mIntent.getStringExtra("name_cat");

        mTvCategorie = (TextView) findViewById(R.id.tv_baidu_map_name_categorie);
        mTvCategorie.setText(categorie);
        mButBackListing = (RelativeLayout) findViewById(R.id.but_baidu_map_back_listing);
        mButBackListing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mIdCatParent = getIntent().getExtras().getInt(DirectoryListing.ID_PARENT_CAT);
        mIdSubCat = getIntent().getExtras().getInt(DirectoryListing.ID_SUB_CAT);
        mComeFrom = getIntent().getExtras().getString(DirectoryListing.KEY_BUNDLE_BAIDU_FROM);

        if (mComeFrom.equals("nearby") || mComeFrom.equals("nearby_user")) {
            mXVenue = getIntent().getExtras().getDouble("venueX");
            mYVenue = getIntent().getExtras().getDouble("venueY");
            smshId = getIntent().getExtras().getInt("smshID");
        }
        if (mComeFrom.equals("name")) {
            mLetterSelected = getIntent().getExtras().getString("letter");
        }

        if (mIdCatParent == 0 && mIdSubCat == 0) {
            mIdCatParent = getIntent().getExtras().getInt(GoogleMapActivity.BUNDLE_FROM_GOOGLE_ID_PARENT_CAT);
            mIdSubCat = getIntent().getExtras().getInt(GoogleMapActivity.BUNDLE_FROM_GOOGLE_ID_SUB_CAT);
        }
        mSwitchGoogleMap.setVisibility(View.GONE);

        mDatabase = new Database(this);

        mLatLngUserPosition = new com.baidu.mapapi.model.LatLng(MainActivity.mXuser, MainActivity.mYuser);

        CoordinateConverter converterMyPos = new CoordinateConverter();
        converterMyPos.from(CoordinateConverter.CoordType.GPS);
        converterMyPos.coord(mLatLngUserPosition);
        mLatLngUserPosition = converterMyPos.convert();


        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(mLatLngUserPosition, 20);
        mBaiduMap.setMapStatus(mapStatusUpdate);

        mBitmapVenue = BitmapDescriptorFactory.fromResource(R.drawable.ic_location_venue);
        mBitmapUser = BitmapDescriptorFactory.fromResource(R.drawable.ic_location_user);


        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Bundle idBundle = marker.getExtraInfo();
                if (idBundle != null && idBundle.getInt("idToPass") != 0) {
                    int id = idBundle.getInt("idToPass");
                    Intent i = new Intent(BaiduMapVenuesActivity.this, VenuePageActivity.class);
                    i.putExtra(ID_VENUE_SELECTED_FROM_BAIDU_MAP, id);
                    startActivity(i);
                }
                return false;
            }
        });

        setLocationAndMarkers(mBaiduMap.getMapStatus());


        mBaiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {
                mBaiduMap.clear();
            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {

                setLocationAndMarkers(mapStatus);

            }
        });


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBaiduMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBaiduMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBaiduMapView.onPause();
    }


    private void setLocationAndMarkers(MapStatus mapStatus) {
        OverlayOptions optionUser = new MarkerOptions()
                .position(mLatLngUserPosition)
                .icon(mBitmapUser);
        mBaiduMap.addOverlay(optionUser);

        LatLngBounds curScreen = mapStatus.bound;

        Double xMax = curScreen.getCenter().latitude - curScreen.southwest.latitude;
        Double yMax = curScreen.getCenter().longitude - curScreen.southwest.longitude;

        LatLng targetScreen = new LatLng(
                curScreen.getCenter().latitude - 0.00614389,
                curScreen.getCenter().longitude - 0.0063158);

        if (mComeFrom.equals("name")) {
            mArrayListOfVenuesForMarkers = mDatabase.getModelVenuesFromLetterWithId(mLatLngUserPosition.latitude, mLatLngUserPosition.longitude, mLetterSelected);
        } else if (mComeFrom.equals("nearby") || mComeFrom.equals("nearby_user")) {
            mArrayListOfVenuesForMarkers = mDatabase.getModelVenuesCloseOfCoord(mXVenue, mYVenue, smshId);
        } else { // come from all others that needs idparent
            mArrayListOfVenuesForMarkers = mDatabase.getModelVenueCloseWithPositionMarkersBaiduMap(mIdCatParent, mIdSubCat, xMax, yMax, targetScreen.latitude, targetScreen.longitude);
        }
        for (int i = 0; i < mArrayListOfVenuesForMarkers.size(); i++) {
            com.baidu.mapapi.model.LatLng venueLatLon = new com.baidu.mapapi.model.LatLng(mArrayListOfVenuesForMarkers.get(i).getMap_x(), mArrayListOfVenuesForMarkers.get(i).getMap_y());

            CoordinateConverter converterVenue = new CoordinateConverter();
            converterVenue.from(CoordinateConverter.CoordType.COMMON);
            converterVenue.coord(venueLatLon);
            venueLatLon = converterVenue.convert();

            Bundle mIdToPass = new Bundle();
            mIdToPass.putInt("idToPass", mArrayListOfVenuesForMarkers.get(i).getId());
            OverlayOptions option = new MarkerOptions()
                    .position(venueLatLon)
                    .extraInfo(mIdToPass)
                    .icon(mBitmapVenue);
            mBaiduMap.addOverlay(option);
            mMarkerMap.put(option, mArrayListOfVenuesForMarkers.get(i).getId());


        }

    }

    public boolean isGoogleMapsInstalled() {
        try {
            ApplicationInfo info = getPackageManager().getApplicationInfo("com.google.android.apps.maps", 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }


}
