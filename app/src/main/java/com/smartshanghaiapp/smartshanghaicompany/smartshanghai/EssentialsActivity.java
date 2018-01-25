package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper.InterfaceArticlesAPI;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper.InterfaceEventsAPI;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfArticles;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfEvents;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class EssentialsActivity extends Activity {

    private RelativeLayout mRlHowTo;
    private RelativeLayout mRlTested;
    private RelativeLayout mRlActivities;
    private RelativeLayout mRlOutbound;
    private RelativeLayout mRlCommunities;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_essentials);

        mRlHowTo = (RelativeLayout) findViewById(R.id.rl_essentials_activity_how_to);
        mRlTested= (RelativeLayout) findViewById(R.id.rl_essentials_activity_tested);
        mRlActivities= (RelativeLayout) findViewById(R.id.rl_essentials_activity_activities);
        mRlOutbound= (RelativeLayout) findViewById(R.id.rl_essentials_activity_outbound);
        mRlCommunities = (RelativeLayout) findViewById(R.id.rl_essentials_activity_communities);


        mRlHowTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EssentialsActivity.this, ArticlesActivity.class);
                i.putExtra("tag_id", "1020"); //differents id to go to the right place
                i.putExtra("tag_name", "How To");
                startActivity(i);
            }
        });


        mRlTested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EssentialsActivity.this, ArticlesActivity.class);
                i.putExtra("tag_id", "1047"); //differents id to go to the right place
                i.putExtra("tag_name", "Tested");
                startActivity(i);
            }
        });



        mRlActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EssentialsActivity.this, ArticlesActivity.class);
                i.putExtra("tag_id", "1658"); //differents id to go to the right place
                i.putExtra("tag_name", "Activities");
                startActivity(i);
            }
        });



        mRlOutbound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EssentialsActivity.this, ArticlesActivity.class);
                i.putExtra("tag_id", "1211"); //differents id to go to the right place
                i.putExtra("tag_name", "Outbound");
                startActivity(i);
            }
        });

        mRlCommunities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EssentialsActivity.this, ArticlesActivity.class);
                i.putExtra("tag_id", "1164"); //differents id to go to the right place
                i.putExtra("tag_name", "Communities");
                startActivity(i);
            }
        });



    }





}
