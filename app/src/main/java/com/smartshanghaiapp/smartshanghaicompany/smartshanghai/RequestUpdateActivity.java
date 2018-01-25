package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage.FragmentTickets;

public class RequestUpdateActivity extends Activity {

    private Button mButGoDb;
    private Button mButGoOnline;
    private Button mButCancel;

    private int mVenueId;

    public final static String KEY_BUNDLE_WEB_ADDRESS_FROM_REQUEST_UPDATE = "key bundle web address from request update";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_update);

        mVenueId = getIntent().getExtras().getInt(RecentlyOpenedActivity.ID_VENUE_SELECTED_FROM_RECENTLY_OPENED_TO_REQUEST);

        mButGoDb = (Button) findViewById(R.id.but_request_update_go_to_db);
        mButGoOnline= (Button) findViewById(R.id.but_request_update_view_listing_online);
        mButCancel = (Button) findViewById(R.id.but_request_update_cancel);

        mButGoDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RequestUpdateActivity.this, UpdateDatabaseActivity.class);
                startActivity(i);
                finish();
            }
        });

        mButGoOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RequestUpdateActivity.this, OnlineFrameActivity.class);
                i.putExtra(KEY_BUNDLE_WEB_ADDRESS_FROM_REQUEST_UPDATE, "http://www.smartshanghai.com/venue/" + mVenueId);
                startActivity(i);
                finish();
            }
        });
        mButCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
