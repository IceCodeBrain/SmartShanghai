package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TaxiPrintoutActivity extends Activity {

    private TextView mTvAddressChinese;
    private TextView mTvAddressEnglish;

    private String mChineseAddress;
    private String mEnglishAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxi_printout);


        mChineseAddress = getIntent().getExtras().getString(VenuePageActivity.KEY_BUNDLE_CHINESE_ADDRESS_FROM_VENUE_ACT);
        mEnglishAddress= getIntent().getExtras().getString(VenuePageActivity.KEY_BUNDLE_ENGLISH_ADDRESS_FROM_VENUE_ACT);

        mTvAddressChinese = (TextView) findViewById(R.id.tv_taxi_printout_activity_chinese_address);
        mTvAddressEnglish= (TextView) findViewById(R.id.tv_taxi_printout_activity_english_address);

        mTvAddressEnglish.setText(mEnglishAddress);
        mTvAddressChinese.setText(mChineseAddress);
    }
}
