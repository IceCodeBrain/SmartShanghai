package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.DatabaseDirectory.Database;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage.MainActivity;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelVenue;

import java.io.File;
import java.util.HashMap;

public class BaiduMapOneVenueActivity extends Activity {

    private MapView mBaiduMapView;
    private BaiduMap mBaiduMap;
    private com.baidu.mapapi.model.LatLng mLatLngUserPosition;
    private MyLocationData mLocDataUser;
    private ModelVenue mModelVenue;

    private RelativeLayout rlTopBar;

    private Database mDatabase;

    private Button mNavigateButton;

    private BitmapDescriptor mBitmapVenue;
    private BitmapDescriptor mBitmapUser;

    private HashMap<OverlayOptions, Integer> mMarkerMap = new HashMap<>();


    public final static String BUNDLE_FROM_BAIDU_ID_VENUE = "id_id_venue_from_baidu";

    private int mIdVenueSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_baidu_map_venues);

        mBaiduMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mBaiduMapView.getMap();

        rlTopBar = (RelativeLayout) findViewById(R.id.rl_baidu_map_top_bar);
        rlTopBar.setVisibility(View.GONE);


        mIdVenueSelected = getIntent().getExtras().getInt(VenuePageActivity.KEY_BUNDLE_ID_VENUE_FROM_VENUE_ACT);
        if (mIdVenueSelected == 0){
            mIdVenueSelected = getIntent().getExtras().getInt(VenuePageActivity.KEY_BUNDLE_ID_VENUE_FROM_VENUE_ACT_TV);
        }

        mNavigateButton = (Button) findViewById(R.id.but_activity_baidu_map_navigate);
        if (isPackageInstalled("com.baidu.BaiduMap")) {
            mNavigateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent naviIntent = null;
                    naviIntent = new Intent("android.intent.action.VIEW", android.net.Uri.parse("baidumap://map/direction?origin=latlng:0,0|name:我的位置&coord_type=gcj02&src=smartshanghai&mode=walking&destination=" + mModelVenue.getMap_x() + "," + mModelVenue.getMap_y()));
                    getApplicationContext().startActivity(naviIntent);
                }
            });
        } else if (isPackageInstalled("com.autonavi.minimap")) {
            mNavigateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_DEFAULT);

                    Uri uri = Uri.parse("androidamap://route/plan/?sourceApplication=smartshanghai&dlat=" + mModelVenue.getMap_x() + "&dlon=" + mModelVenue.getMap_y() + "&dev=0&t=2");
                    intent.setData(uri);

                    startActivity(intent);
                }});
        } else {
            mNavigateButton.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Install Baidu map or Amap to get a\nturn-by-turn navigation", Toast.LENGTH_LONG).show();
        }

        mDatabase = new Database(this);

        mLatLngUserPosition = new com.baidu.mapapi.model.LatLng(MainActivity.mXuser, MainActivity.mYuser);

        CoordinateConverter converterMyPos = new CoordinateConverter();
        converterMyPos.from(CoordinateConverter.CoordType.GPS);
        converterMyPos.coord(mLatLngUserPosition);
        mLatLngUserPosition = converterMyPos.convert();


        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(mLatLngUserPosition, 15);
        mBaiduMap.setMapStatus(mapStatusUpdate);

        mBitmapVenue = BitmapDescriptorFactory.fromResource(R.drawable.ic_location_venue);
        mBitmapUser = BitmapDescriptorFactory.fromResource(R.drawable.ic_location_user);

        OverlayOptions optionUser = new MarkerOptions()
                .position(mLatLngUserPosition)
                .icon(mBitmapUser);
        mBaiduMap.addOverlay(optionUser);


        mModelVenue = mDatabase.getAModelVenuePositionFromId(mIdVenueSelected);
        com.baidu.mapapi.model.LatLng venueLatLon = new com.baidu.mapapi.model.LatLng(mModelVenue.getMap_x(), mModelVenue.getMap_y());


        CoordinateConverter converterVenue = new CoordinateConverter();
        converterVenue.from(CoordinateConverter.CoordType.COMMON);
        converterVenue.coord(venueLatLon);
        venueLatLon = converterVenue.convert();


        OverlayOptions option = new MarkerOptions()
                .position(venueLatLon)
                .icon(mBitmapVenue);
        mBaiduMap.addOverlay(option);


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



    public boolean isGoogleMapsInstalled() {
        try {
            ApplicationInfo info = getPackageManager().getApplicationInfo("com.google.android.apps.maps", 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static boolean isPackageInstalled(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }
}
