package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.DatabaseDirectory.Database;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelEssentials;

import java.util.ArrayList;

public class MoreListActivity extends Activity {


    private ListView mLvEssentials;
    private ArrayList<ModelEssentials> mEssentials;
    private Database mDatabase;
    private int mComeFrom;
    private Intent mIntent;
    private MoreListAdapter mAdapter;

    public final static String KEY_BUNDLE_ID_TO_VENUE_FROM_ESSENTIALS = "key bundle id to venue from essentials";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_list);

        mEssentials = new ArrayList<>();
        mLvEssentials = (ListView) findViewById(R.id.lv_essentials_list);


        mComeFrom = 0;
        mIntent = getIntent();
        mComeFrom = mIntent.getIntExtra("essentials_id", 0);



        mDatabase = new Database(this);
        if (mDatabase.checkDataBase() == true && mComeFrom > 0){
            mEssentials = mDatabase.getModelsEssentials(mComeFrom);
            mAdapter = new MoreListAdapter(mEssentials, getBaseContext());
            mLvEssentials.setAdapter(mAdapter);

        }


        mLvEssentials.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mEssentials.get(position).getSmsh_venue_id() > 0){
                    Intent i = new Intent(MoreListActivity.this, VenuePageActivity.class);
                    i.putExtra(KEY_BUNDLE_ID_TO_VENUE_FROM_ESSENTIALS, mEssentials.get(position).getSmsh_venue_id());
                    startActivity(i);
                }
            }
        });


    }
}
