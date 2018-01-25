package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper.InterfacePostRegisterUserAPI;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelResponsePostReview;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelResponseRegister;

import org.w3c.dom.Text;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class RegisterActivity extends Activity implements Callback<ModelResponseRegister> {

    private RelativeLayout mRlLoading;

    private Button mButSubmitRegister;
    private EditText mEtRegisterEmail;
    private EditText mEtRegisterUsername;
    private EditText mEtRegisterPassword;
    private TextView mTvLogin;

    private SharedPreferences mPreferencesUserInfos;

    public final static String KEY_BUNDLE_MAIL_REGISTER_REGISTRER = "KEY_BUNDLE_MAIL_REGISTER_REGISTRER";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mButSubmitRegister = (Button) findViewById(R.id.but_register_activity_send);
        mEtRegisterEmail = (EditText) findViewById(R.id.et_register_activity_email_address);
        mEtRegisterUsername = (EditText) findViewById(R.id.et_register_activity_username);
        mEtRegisterPassword = (EditText) findViewById(R.id.et_register_activity_password);
        mRlLoading = (RelativeLayout) findViewById(R.id.rl_register_activity_loading);


        mPreferencesUserInfos = PreferenceManager.getDefaultSharedPreferences(this);

        mTvLogin = (TextView) findViewById(R.id.but_register_activity_login);
        mTvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        mButSubmitRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRlLoading.setVisibility(View.VISIBLE);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://www.smartshanghai.com/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                InterfacePostRegisterUserAPI api = retrofit.create(InterfacePostRegisterUserAPI.class);

                Call<ModelResponseRegister> callToRegister = api.postRegister(
                        getResources().getString(R.string.key_webservices_android),
                        mEtRegisterEmail.getText().toString(),
                        mEtRegisterUsername.getText().toString(),
                        mEtRegisterPassword.getText().toString(),
                        3 // sourceId toknow the user registered with android
                );


                callToRegister.enqueue(RegisterActivity.this);

            }
        });
    }

    @Override
    public void onResponse(Response<ModelResponseRegister> response, Retrofit retrofit) {

        response.body();
        mRlLoading.setVisibility(View.GONE);

        if (response.body().getMessage().equals("OK")) {


            Toast.makeText(getBaseContext(), "registered", Toast.LENGTH_SHORT).show();


            SharedPreferences.Editor editor = mPreferencesUserInfos.edit();
            editor.putString("Username", mEtRegisterUsername.getText().toString());
            editor.putString("EmailAddress", mEtRegisterEmail.getText().toString());
            editor.putInt("userID", response.body().getData().getUserId());
            //editor.putString("Password", mEtRegisterPassword.getText().toString());
            editor.putBoolean("isConnected", true);
            editor.commit();

            Intent i = new Intent(RegisterActivity.this, RegisteredActivity.class);
            i.putExtra(KEY_BUNDLE_MAIL_REGISTER_REGISTRER, mEtRegisterEmail.getText().toString());
            startActivity(i);

            finish();


        } else if (response.body().getMessage() != null) {
            Toast.makeText(getBaseContext(), response.body().getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        mRlLoading.setVisibility(View.GONE);

        Toast.makeText(getBaseContext(), "connection problem",
                Toast.LENGTH_SHORT).show();
    }
}
