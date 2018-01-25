package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
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


public class GoogleMapActivityOneVenue extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Database mDatabase;
    private ModelVenue mModelVenue;
    private Double mXuser;
    private Double mYuser;




    private Button mButSwitchBaiduMap;


    private HashMap<Marker, Integer> mMarkerMap = new HashMap<>();

    private int mIdVenueSelected;

    public final static String ID_VENUE_SELECTED_FROM_MAP = "no id selected";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory_google_map);

        mDatabase = new Database(this);

        SDKInitializer.initialize(getApplicationContext());

        mXuser = MainActivity.mXGooglePosition;
        mYuser = MainActivity.mYGooglePosition;

        mIdVenueSelected = getIntent().getExtras().getInt(BaiduMapOneVenueActivity.BUNDLE_FROM_BAIDU_ID_VENUE);




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


        mModelVenue = mDatabase.getAModelVenuePositionFromId(mIdVenueSelected);
        LatLng venueLatLon = new LatLng(mModelVenue.getMap_x(), mModelVenue.getMap_y());


        Marker marker = mMap.addMarker(new MarkerOptions()
                .position(venueLatLon)
                .title(mModelVenue.getName()));


    }

}
