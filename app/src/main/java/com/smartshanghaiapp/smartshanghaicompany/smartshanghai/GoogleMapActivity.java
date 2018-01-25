package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.baidu.mapapi.SDKInitializer;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.DatabaseDirectory.Database;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage.MainActivity;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelVenue;

import java.util.ArrayList;
import java.util.HashMap;


public class GoogleMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Database mDatabase;
    private ArrayList<ModelVenue> mArrayListOfVenuesForMarkers;
    private Double mXuser;
    private Double mYuser;
    private int mIdCatParent;
    private int mIdSubCat;


    public final static String BUNDLE_FROM_GOOGLE_ID_PARENT_CAT = "id_cat_parent_from_google";
    public final static String BUNDLE_FROM_GOOGLE_ID_SUB_CAT = "id_sub_cat_from_google";



    private HashMap<Marker, Integer> mMarkerMap = new HashMap<>();


    public final static String ID_VENUE_SELECTED_FROM_MAP = "no id selected";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory_google_map);

        mDatabase = new Database(this);

        SDKInitializer.initialize(getApplicationContext());

        mIdCatParent = getIntent().getExtras().getInt(DirectoryListing.ID_PARENT_CAT);
        mIdSubCat = getIntent().getExtras().getInt(DirectoryListing.ID_SUB_CAT);
        mXuser = MainActivity.mXGooglePosition;
        mYuser = MainActivity.mYGooglePosition;
        if (mIdCatParent == 0 && mIdSubCat == 0) {
            mIdCatParent = getIntent().getExtras().getInt(BaiduMapVenuesActivity.BUNDLE_FROM_BAIDU_ID_PARENT_CAT);
            mIdSubCat = getIntent().getExtras().getInt(BaiduMapVenuesActivity.BUNDLE_FROM_BAIDU_ID_SUB_CAT);
        }




        mArrayListOfVenuesForMarkers = new ArrayList<ModelVenue>();


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(false);

        LatLng myLoc = new LatLng(mXuser, mYuser);

        Marker self = mMap.addMarker(new MarkerOptions()
                .position(myLoc)
                .icon(com.google.android.gms.maps.model.BitmapDescriptorFactory.fromResource(R.drawable.ic_location_user)));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLoc));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLoc, 15.0f));

        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {


                LatLngBounds curScreen = mMap.getProjection().getVisibleRegion().latLngBounds;

                Double xMax = cameraPosition.target.latitude - curScreen.southwest.latitude;
                Double yMax = cameraPosition.target.longitude - curScreen.southwest.longitude;


                mArrayListOfVenuesForMarkers = mDatabase.getModelVenueCloseWithPositionMarkers(mIdCatParent, mIdSubCat, xMax, yMax, cameraPosition.target.latitude, cameraPosition.target.longitude);
                for (int i = 0; i < mArrayListOfVenuesForMarkers.size(); i++) {
                    LatLng venueLatLon = new LatLng(mArrayListOfVenuesForMarkers.get(i).getMap_x(), mArrayListOfVenuesForMarkers.get(i).getMap_y());


                    Marker marker = mMap.addMarker(new MarkerOptions()
                            .position(venueLatLon)
                            .title(mArrayListOfVenuesForMarkers.get(i).getName()));

                    mMarkerMap.put(marker, mArrayListOfVenuesForMarkers.get(i).getId());

                }
            }
        });

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                int id = mMarkerMap.get(marker);
                Intent i = new Intent(GoogleMapActivity.this, VenuePageActivity.class);
                i.putExtra(ID_VENUE_SELECTED_FROM_MAP, id);
                startActivity(i);
            }
        });

    }


}
