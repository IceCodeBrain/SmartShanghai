package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.DatabaseDirectory.Database;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelChildrenArea;

import java.util.ArrayList;

public class AreaActivity extends Activity {

    private ListView mLvPlaces;

    private ArrayList<ModelChildrenArea> mMotherAreas;
    private ArrayList<ModelChildrenArea> mChildrenAreas;

    private Database mDatabase;

    private AreaActivityAdapter mAdapter;

    public final static String query_come_from = "query_come_from";
    private int query_area;
    private String query_name;
    public final static String queryArea = "query Area";
    public final static String queryName = "Query Name";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);

        mLvPlaces = (ListView) findViewById(R.id.lv_area_activity_chil);

        mDatabase = new Database(this);
        mChildrenAreas = new ArrayList<ModelChildrenArea>();
        mMotherAreas = new ArrayList<ModelChildrenArea>();
        mMotherAreas = mDatabase.getChildAreaItems(11);

        for (int i = 0; i < mMotherAreas.size(); i++) {
            mChildrenAreas.add(new ModelChildrenArea(-1, mMotherAreas.get(i).getName()));
            mChildrenAreas.addAll(mDatabase.getChildAreaItems(mMotherAreas.get(i).getId()));
        }



        mAdapter = new AreaActivityAdapter(mChildrenAreas, this);
        mLvPlaces.setAdapter(mAdapter);

        mLvPlaces.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                query_area = mChildrenAreas.get(position).getId();
                query_name = mChildrenAreas.get(position).getName();
                if (mChildrenAreas.get(position).getId() == -1) {

                } else {
                    Intent i = new Intent(AreaActivity.this, DirectoryListing.class);
                    i.putExtra(queryName, query_name);
                    i.putExtra(queryArea, query_area);
                    i.putExtra("LISTING_FROM", "area");
                    i.putExtra(query_come_from, "area_activity");
                    startActivity(i);

                }
            }
        });

    }
}
