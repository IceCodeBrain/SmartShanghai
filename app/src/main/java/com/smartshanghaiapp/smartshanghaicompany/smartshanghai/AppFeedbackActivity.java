package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper.InterfacePostFeedbackAPI;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelResponseGeneral;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class AppFeedbackActivity extends Activity {

    private EditText mEtFeedBack;
    private Button mButSubmit;

    private String mVersionNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_feedback);


        mEtFeedBack = (EditText) findViewById(R.id.et_app_feedback_activity_feedback);
        mButSubmit = (Button) findViewById(R.id.but_app_feedback_activity_submit);

        mVersionNumber = "no version detected";
        try {
            mVersionNumber = this.getPackageManager()
                    .getPackageInfo(this.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        mButSubmit.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {


                                              Retrofit retrofit = new Retrofit.Builder()
                                                      .baseUrl("http://www.smartshanghai.com/api/")
                                                      .addConverterFactory(GsonConverterFactory.create())
                                                      .build();

                                              InterfacePostFeedbackAPI apiFeedback = retrofit.create(InterfacePostFeedbackAPI.class);
                                              Call<ModelResponseGeneral> callPostFeedback = apiFeedback.postFeedback(
                                                      getResources().getString(R.string.key_webservices_android),
                                                      2, //means from ANDROID
                                                      mVersionNumber,
                                                      mEtFeedBack.getText().toString()
                                              );

                                              callPostFeedback.enqueue(new Callback<ModelResponseGeneral>() {
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
                                                                       }

                                              );

                                          }

                                      }

        );

    }


}


