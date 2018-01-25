package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper.InterfaceLoginAPI;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper.InterfaceReviewVenuesAPI;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelResultLogin;

import org.w3c.dom.Text;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginActivity extends Activity implements Callback<ModelResultLogin> {

    private EditText mEtUsername;
    private EditText mEtPassword;
    private Button mButSubmit;
    private String mUserName;
    private String mPassword;
    private TextView mButForgotPass;

    private RelativeLayout mRlLoading;

    private SharedPreferences mPreferencesUserInfos;


    private TextView mButRegister;


    private RelativeLayout mRlIncorrect;


    public final static String KEY_BUNDLE_WEB_ADDRESS_FORGOT_PASS_LOGIN = "Key_bundle_web_address_forgot_pass_login";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPreferencesUserInfos = PreferenceManager.getDefaultSharedPreferences(this);


        mEtUsername = (EditText) findViewById(R.id.et_login_activity_email_address);
        mEtPassword = (EditText) findViewById(R.id.et_login_activity_password);
        mButSubmit = (Button) findViewById(R.id.but_login_activity_send);
        mButRegister = (TextView) findViewById(R.id.but_login_activity_register);
        mRlIncorrect = (RelativeLayout) findViewById(R.id.rl_login_activity_incorrect);
        mButForgotPass = (TextView) findViewById(R.id.but_login_activity_forgot_password);

        mRlLoading = (RelativeLayout) findViewById(R.id.rl_login_activity_loading);

        mButRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });

        mButForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, OnlineFrameActivity.class);
                i.putExtra(KEY_BUNDLE_WEB_ADDRESS_FORGOT_PASS_LOGIN, "http://www.smartshanghai.com/resetpassword/");
                startActivity(i);
            }
        });


        mButSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mUserName = mEtUsername.getText().toString();
                mPassword = mEtPassword.getText().toString();

                callConnect(mUserName, mPassword);
                mRlLoading.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void onResponse(Response<ModelResultLogin> response, Retrofit retrofit) {
        response.body();
        mRlLoading.setVisibility(View.GONE);

        if (response.body().data == null) {
            Toast.makeText(this, "error", Toast.LENGTH_LONG).show();
            SharedPreferences.Editor editor = mPreferencesUserInfos.edit();
            editor.putBoolean("isConnected", false);
            mRlIncorrect.setVisibility(View.VISIBLE);

        } else {
            SharedPreferences.Editor editor = mPreferencesUserInfos.edit();
            editor.putString("Username", response.body().data.getUsername());
            editor.putString("EmailAddress", response.body().data.getEmailAddress());
            editor.putInt("userID", response.body().data.getId());
            editor.putString("Password", mPassword);
            editor.putString("avatar", response.body().data.getAvatar());
            editor.putBoolean("isConnected", true);
            editor.commit();

            Toast.makeText(this, "Connected", Toast.LENGTH_LONG).show();
            finish();
        }

    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(getBaseContext(), "connection problem", Toast.LENGTH_SHORT).show();

        mRlLoading.setVisibility(View.GONE);

    }


    private void callConnect(String username, String password) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.smartshanghai.com/api/login")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfaceLoginAPI api = retrofit.create(InterfaceLoginAPI.class);


        Call<ModelResultLogin> callLogin = api.postLogin(
                getResources().getString(R.string.key_webservices_android),
                username,
                password
        );
        callLogin.enqueue(LoginActivity.this);

    }

}
