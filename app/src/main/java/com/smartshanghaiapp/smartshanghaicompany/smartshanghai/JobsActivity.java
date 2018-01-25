package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper.InterfaceArticlesAPI;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper.InterfaceJobsAPI;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelArticle;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelJob;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfArticles;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfJobs;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class JobsActivity extends Activity implements Callback<ModelListOfJobs> {

    private ArrayList<ModelJob> mJobs;

    private JobsActivityAdapter mAdapter;

    private ListView mLvJobs;
    private ProgressBar mPgJobs;

    public final static String KEY_BUNDLE_JOBS_JOB_ID = "key_bundle_jobs_job_id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);

        mLvJobs = (ListView) findViewById(R.id.lv_jobs_avtivity);
        mPgJobs = (ProgressBar) findViewById(R.id.loadbar_jobs_activity);
        mJobs = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.smartshanghai.com/api/jobs")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfaceJobsAPI api = retrofit.create(InterfaceJobsAPI.class);

        String url = "?key=" + getResources().getString(R.string.key_webservices_android);

        Call<ModelListOfJobs> callListOfJobs = api.getJobList(url);
        callListOfJobs.enqueue(this);


        mLvJobs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mJobs.get(position).getId() > 0) {
                    Intent i = new Intent(JobsActivity.this, OnlineFrameActivity.class);
                    String url = "http://www.smartshanghai.com/jobs/sales-and-marketing/" + String.valueOf(mJobs.get(position).getId()) + "?source=android";
                    i.putExtra(KEY_BUNDLE_JOBS_JOB_ID, url);
                    startActivity(i);
                }
            }
        });


    }


    @Override
    public void onResponse(Response<ModelListOfJobs> response, Retrofit retrofit) {
        response.body();

        mLvJobs.setVisibility(View.VISIBLE);
        mPgJobs.setVisibility(View.GONE);


        if (response.body().data != null && response.body().data.size() != 0) {
            for (int i = 0; i < response.body().data.size(); i++) {
                mJobs.add(new ModelJob(
                        response.body().data.get(i).getId(),
                        response.body().data.get(i).getTitle(),
                        response.body().data.get(i).getCompany_name(),
                        response.body().data.get(i).getCompany_logo(),
                        response.body().data.get(i).getDate_published()
                ));
            }
        }

        mAdapter = new JobsActivityAdapter(mJobs, this);
        mLvJobs.setAdapter(mAdapter);
    }

    @Override
    public void onFailure(Throwable t) {

    }
}
