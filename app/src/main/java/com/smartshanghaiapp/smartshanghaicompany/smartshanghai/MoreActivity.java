package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.DraweeView;

public class MoreActivity extends Activity {



    private LinearLayout mRlIsDisconnected;
    private LinearLayout mllIsConnected;

    private Uri mUriAvatar;

    private SharedPreferences mPreferencesUserInfos;

    private ImageView mIvSignIn;

    private LinearLayout mLlRegister;

    private boolean mIsConnected;

    private LinearLayout mLlSettings;


    private DraweeView mDvAvatar;

    private TextView mTvUsername;

    private ImageView mIvSignOut;

    private TextView mTvForgotPassword;




    private RelativeLayout mRlUpdateDb;
    private RelativeLayout mRlSubmitVenue;
    private RelativeLayout mRlSubmitEvent;
    private RelativeLayout mRlReportMistakes;
    private RelativeLayout mRlAppFeedBacks;
    private RelativeLayout mRlSupport;
    private RelativeLayout mRlContact;
    private RelativeLayout mRlAboutUs;
    private RelativeLayout mRlAdvertise;

    public final static String KEY_BUNDLE_WEB_ADDRESS_SUBMIT_VENUE = "Key_bundle_web_address_submit_venue";
    public final static String KEY_BUNDLE_WEB_ADDRESS_SUBMIT_EVENT = "Key_bundle_web_address_submit_event";
    public final static String KEY_BUNDLE_WEB_ADDRESS_REPORT_MISTAKE = "Key_bundle_web_address_report_mistakes";
    public final static String KEY_BUNDLE_WEB_ADDRESS_CONTACT = "Key_bundle_web_address_contact";
    public final static String KEY_BUNDLE_WEB_ADDRESS_ABOUT_US = "Key_bundle_web_address_about_us";
    public final static String KEY_BUNDLE_WEB_ADDRESS_APP_FEEDBACKS = "Key_bundle_web_address_app_feedbacks";
    public final static String KEY_BUNDLE_WEB_ADDRESS_SUPPORT = "Key_bundle_web_address_support";
    public final static String KEY_BUNDLE_WEB_ADDRESS_FORGOT_PASS = "Key_bundle_web_address_forgot_pass";
    public final static String KEY_BUNDLE_WEB_ADDRESS_ADVERTISE = "Key_bundle_web_address_advertise";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);


        mRlIsDisconnected = (LinearLayout) findViewById(R.id.rl_frag_more_settings_disconnected);
        mllIsConnected = (LinearLayout) findViewById(R.id.ll_frag_more_settings_connected);
        mTvForgotPassword = (TextView) findViewById(R.id.tv_frag_more_forgotpassword);

        mTvUsername = (TextView) findViewById(R.id.tv_frag_more_username);

        mLlRegister = (LinearLayout) findViewById(R.id.ll_frag_more_register);
        mLlRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MoreActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        mIvSignOut = (ImageView) findViewById(R.id.iv_frag_more_sign_out);
        mIvSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = mPreferencesUserInfos.edit();
                editor.putString("Username", "");
                editor.putString("EmailAddress", "");
                editor.putString("Password", "");
                editor.putInt("userID", 0);
                editor.putBoolean("isConnected", false);
                editor.commit();

                setPageConnection();

            }
        });

        mDvAvatar = (DraweeView) findViewById(R.id.dv_frag_more_avatar);

        mIvSignIn = (ImageView) findViewById(R.id.iv_frag_more_settings_signin);
        mIvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MoreActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        mTvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MoreActivity.this, OnlineFrameActivity.class);
                i.putExtra(KEY_BUNDLE_WEB_ADDRESS_FORGOT_PASS, "http://www.smartshanghai.com/resetpassword?source=android");
                startActivity(i);
            }
        });

        mRlUpdateDb = (RelativeLayout) findViewById(R.id.rl_frag_more_update_database);
        mRlUpdateDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MoreActivity.this, UpdateDatabaseActivity.class);
                startActivity(i);
            }
        });


        mRlSubmitVenue = (RelativeLayout) findViewById(R.id.rl_frag_more_submit_a_venue);
        mRlSubmitVenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MoreActivity.this, OnlineFrameActivity.class);
                i.putExtra(KEY_BUNDLE_WEB_ADDRESS_SUBMIT_VENUE, "http://www.smartshanghai.com/service/venues?source=android");
                startActivity(i);
            }
        });

        mRlSubmitEvent = (RelativeLayout) findViewById(R.id.rl_frag_more_submit_an_event);
        mRlSubmitEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MoreActivity.this, OnlineFrameActivity.class);
                i.putExtra(KEY_BUNDLE_WEB_ADDRESS_SUBMIT_EVENT, "http://www.smartshanghai.com/service/events?source=android");
                startActivity(i);
            }
        });

        mRlReportMistakes = (RelativeLayout) findViewById(R.id.rl_frag_more_report_mistakes);
        mRlReportMistakes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MoreActivity.this, ReportMistakesActivity.class);
                i.putExtra("REPORT MISTAKE FROM", "main");
                startActivity(i);
            }
        });


        mRlContact = (RelativeLayout) findViewById(R.id.rl_frag_more_contact);
        mRlContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MoreActivity.this, OnlineFrameActivity.class);
                i.putExtra(KEY_BUNDLE_WEB_ADDRESS_CONTACT, "http://www.smartshanghai.com/contact?source=android");
                startActivity(i);
            }
        });

        mRlAboutUs = (RelativeLayout) findViewById(R.id.rl_frag_more_about_us);
        mRlAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MoreActivity.this, OnlineFrameActivity.class);
                i.putExtra(KEY_BUNDLE_WEB_ADDRESS_ABOUT_US, "http://www.smartshanghai.com/service/about?source=android");
                startActivity(i);
            }
        });

        mRlAppFeedBacks = (RelativeLayout) findViewById(R.id.rl_frag_more_app_feedbacks);
        mRlAppFeedBacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MoreActivity.this, AppFeedbackActivity.class);
                startActivity(i);
            }
        });

        mRlSupport = (RelativeLayout) findViewById(R.id.rl_frag_more_support);
        mRlSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MoreActivity.this, OnlineFrameActivity.class);
                i.putExtra(KEY_BUNDLE_WEB_ADDRESS_SUPPORT, "http://www.smartshanghai.com/faqs/smsh-android-app?source=android");
                startActivity(i);
            }
        });
        mRlAdvertise = (RelativeLayout) findViewById(R.id.rl_frag_more_advertise_with_us);
        mRlAdvertise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MoreActivity.this, OnlineFrameActivity.class);
                i.putExtra(KEY_BUNDLE_WEB_ADDRESS_ADVERTISE, "http://www.smartshanghai.com/service/advertise?source=android");
                startActivity(i);
            }
        });




    }


    private void setPageConnection() {
        mPreferencesUserInfos = PreferenceManager.getDefaultSharedPreferences(this);
        mIsConnected = mPreferencesUserInfos.getBoolean("isConnected", false);

        if (mIsConnected) {
            mllIsConnected.setVisibility(View.VISIBLE);
            mRlIsDisconnected.setVisibility(View.GONE);
            mTvUsername.setText(mPreferencesUserInfos.getString("Username", ""));

            String avatar = mPreferencesUserInfos.getString("avatar", "");
            if (avatar != null && !avatar.equals("")) {
                mUriAvatar = Uri.parse("http://www.smartshanghai.com/uploads/avatar/" + avatar);
                mDvAvatar.setImageURI(mUriAvatar);
            }

        } else {
            mllIsConnected.setVisibility(View.GONE);
            mRlIsDisconnected.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setPageConnection();
    }
}
