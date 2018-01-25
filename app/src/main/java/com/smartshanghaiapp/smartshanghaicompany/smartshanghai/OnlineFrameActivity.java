package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage.FragmentDirectory;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage.FragmentFeed;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage.FragmentMore;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage.FragmentTickets;

public class OnlineFrameActivity extends Activity {

    private String mAddress;

    private RelativeLayout mRlShareButton;

    private String mToBeShared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_frame);

        mRlShareButton = (RelativeLayout) findViewById(R.id.rl_online_frame_share);

        mAddress = getIntent().getExtras().getString(FragmentTickets.KEY_BUNDLE_WEB_ADDRESS);
        if (mAddress == null) {
            mAddress = getIntent().getExtras().getString(FragmentFeed.KEY_BUNDLE_WEB_ADDRESS_FOR_ADVERTISEMENT);
        }
        if (mAddress == null) {
            mAddress = getIntent().getExtras().getString(MoreActivity.KEY_BUNDLE_WEB_ADDRESS_SUBMIT_VENUE);
        }
        if (mAddress == null) {
            mAddress = getIntent().getExtras().getString(MoreActivity.KEY_BUNDLE_WEB_ADDRESS_SUBMIT_EVENT);
        }
        if (mAddress == null) {
            mAddress = getIntent().getExtras().getString(MoreActivity.KEY_BUNDLE_WEB_ADDRESS_REPORT_MISTAKE);
        }
        if (mAddress == null) {
            mAddress = getIntent().getExtras().getString(MoreActivity.KEY_BUNDLE_WEB_ADDRESS_CONTACT);
        }
        if (mAddress == null) {
            mAddress = getIntent().getExtras().getString(MoreActivity.KEY_BUNDLE_WEB_ADDRESS_ABOUT_US);
        }
        if (mAddress == null) {
            mAddress = getIntent().getExtras().getString(MoreActivity.KEY_BUNDLE_WEB_ADDRESS_APP_FEEDBACKS);
        }
        if (mAddress == null) {
            mAddress = getIntent().getExtras().getString(MoreActivity.KEY_BUNDLE_WEB_ADDRESS_SUPPORT);
        }
        if (mAddress == null) {
            mAddress = getIntent().getExtras().getString(VenuePageActivity.KEY_BUNDLE_WEB_ADDRESS_FROM_VENUE);
        }
        if (mAddress == null) {
            mAddress = getIntent().getExtras().getString(EventActivity.KEY_BUNDLE_WEB_ADDRESS_FROM_EVENT);
        }
        if (mAddress == null) {
            mAddress = getIntent().getExtras().getString(DirectoryListingVenuesAdapter.KEY_BUNDLE_WEB_ADDRESS_AD_DIR_LISTING);
        }
        if (mAddress == null) {
            mAddress = getIntent().getExtras().getString(FragmentFeed.KEY_BUNDLE_WEB_ADDRESS_FROM_FEED_WIRE);
            if (mAddress != null) {
                mRlShareButton.setVisibility(View.VISIBLE);
                String shareUrl = getIntent().getExtras().getString(FragmentFeed.KEY_BUNDLE_ONLINE_ACT_SHARE_URL);
                String shareTitle = getIntent().getExtras().getString(FragmentFeed.KEY_BUNDLE_ONLINE_ACT_SHARE_TITLE);
                String sharePhrase = getIntent().getExtras().getString(FragmentFeed.KEY_BUNDLE_ONLINE_ACT_SHARE_PHRASE);
                mToBeShared = sharePhrase + shareTitle + " " + shareUrl + "?source=android";
                mRlShareButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_TEXT, mToBeShared);
                        startActivity(Intent.createChooser(intent, "Share"));

                    }
                });
            }
        }
        if (mAddress == null) {
            mAddress = getIntent().getExtras().getString(FragmentFeed.KEY_BUNDLE_WEB_ADDRESS_FROM_FEED_TICKET);
        }
        if (mAddress == null) {
            mAddress = getIntent().getExtras().getString(MoreActivity.KEY_BUNDLE_WEB_ADDRESS_FORGOT_PASS);
        }
        if (mAddress == null) {
            mAddress = getIntent().getExtras().getString(VenuePageActivity.KEY_BUNDLE_WEB_ADDRESS_VENUE_CHOPE);
        }
        if (mAddress == null) {
            mAddress = getIntent().getExtras().getString(LoginActivity.KEY_BUNDLE_WEB_ADDRESS_FORGOT_PASS_LOGIN);
        }
        if (mAddress == null) {
            mAddress = getIntent().getExtras().getString(MoreActivity.KEY_BUNDLE_WEB_ADDRESS_ADVERTISE);
        }
        if (mAddress == null) {
            mAddress = getIntent().getExtras().getString(RequestUpdateActivity.KEY_BUNDLE_WEB_ADDRESS_FROM_REQUEST_UPDATE);
        }
        if (mAddress == null) {
            mAddress = getIntent().getExtras().getString(FragmentMore.KEY_BUNDLE_WEB_ADDRESS_BEGINNER_GUIDE);
        }
        if (mAddress == null) {
            mAddress = getIntent().getExtras().getString(FragmentDirectory.KEY_BUNDLE_WEB_ADDRESS_EDUCATION);
        }
        if (mAddress == null) {
            mAddress = getIntent().getExtras().getString(ArticlesActivity.KEY_BUNDLE_ARTICLES_ARTICLE_ID);
        }
        if (mAddress == null) {
            mAddress = getIntent().getExtras().getString(JobsActivity.KEY_BUNDLE_JOBS_JOB_ID);
        }
        if (mAddress == null) {
            mAddress = getIntent().getExtras().getString(BuyAndSellActivity.KEY_BUNDLE_BUY_AND_SELL_ID);
        }


        WebView myWebView = (WebView) findViewById(R.id.online_activity_webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        if (mAddress != null && !mAddress.equals("")) {
            myWebView.loadUrl(mAddress); // here we should load the url send previously through the intent
        }


    }
}
