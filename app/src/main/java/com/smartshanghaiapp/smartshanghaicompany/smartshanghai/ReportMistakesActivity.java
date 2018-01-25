package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper.InterfaceReportMistakeAPI;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelResponseGeneral;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ReportMistakesActivity extends Activity {

    private int mIdVenue;
    private int mIdEvent;
    private String mVenueName;
    private String mEventName;
    private EditText mEtVenueName;
    private EditText mEtReport;

    private TextView mTvVenueName;

    private Intent mIntent;
    private String mPrevious;

    private Button mButSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_mistakes);

        mIntent = getIntent();
        mPrevious = mIntent.getStringExtra("REPORT MISTAKE FROM");

        mEtVenueName = (EditText) findViewById(R.id.et_report_mistakes_activity_venue_name);
        mEtReport = (EditText) findViewById(R.id.et_report_mistakes_activity_report);
        mButSubmit = (Button) findViewById(R.id.but_report_mistakes_activity_submit);
        mTvVenueName = (TextView) findViewById(R.id.tv_report_mistakes_activity_venue_name);

        if (mPrevious.equals("main")) {
            mTvVenueName.setVisibility(View.GONE);
            mEtVenueName.setVisibility(View.VISIBLE);
        } else if (mPrevious.equals("venuePage")) {
            mTvVenueName.setVisibility(View.VISIBLE);
            mEtVenueName.setVisibility(View.GONE);
            mIdVenue = getIntent().getExtras().getInt(VenuePageActivity.KEY_BUNDLE_VENUE_TO_REPORT_MISTAKE_ID);
            mVenueName = getIntent().getExtras().getString(VenuePageActivity.KEY_BUNDLE_VENUE_TO_REPORT_MISTAKE_VENUE_NAME);
            mTvVenueName.setText("@ " + mVenueName);
        } else if (mPrevious.equals("eventpage")) {
            mTvVenueName.setVisibility(View.VISIBLE);
            mEtVenueName.setVisibility(View.GONE);
            mIdEvent = getIntent().getExtras().getInt(EventActivity.KEY_BUNDLE_EVENT_TO_REPORT_MISTAKE_ID);
            mEventName = getIntent().getExtras().getString(EventActivity.KEY_BUNDLE_EVENT_TO_REPORT_MISTAKE_EVENT_NAME);
            mTvVenueName.setText("@ " + mEventName);
        }


        mButSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://www.smartshanghai.com/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                InterfaceReportMistakeAPI apiMistake = retrofit.create(InterfaceReportMistakeAPI.class);
                Call<ModelResponseGeneral> callPostReport;
                if (mPrevious.equals("main")) {
                    callPostReport = apiMistake.postReportWithName(
                            getResources().getString(R.string.key_webservices_android),
                            mEtReport.getText().toString(),
                            mEtVenueName.getText().toString()
                    );
                } else if (mPrevious.equals("venuePage")) {
                    callPostReport = apiMistake.postReportWithID(
                            getResources().getString(R.string.key_webservices_android),
                            mEtReport.getText().toString(),
                            mIdVenue
                    );
                } else { // come from event activity
                    callPostReport = apiMistake.postReportWithID(
                            getResources().getString(R.string.key_webservices_android),
                            mEtReport.getText().toString(),
                            mIdEvent
                    );
                }
                callPostReport.enqueue(new Callback<ModelResponseGeneral>() {
                    @Override
                    public void onResponse(Response<ModelResponseGeneral> response, Retrofit retrofit) {
                        response.body();
                        response.message();
                        Toast.makeText(getBaseContext(), "posted", Toast.LENGTH_SHORT).show();
                        finish();

                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(getBaseContext(), "connection problem", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}
