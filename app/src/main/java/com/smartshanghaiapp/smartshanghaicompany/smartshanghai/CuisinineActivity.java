package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.DatabaseDirectory.Database;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelCuisine;

import java.util.ArrayList;

public class CuisinineActivity extends Activity {


    private ListView mLvCuisines;

    private ArrayList<ModelCuisine> mCuinines;

    private Database mDatabase;

    private CuisineActivityAdapter mAdapter;


    private int query_cuisine_id;
    private String query_cuisine_name;

    public final static String queryCuisineName = "query cuisine name";
    public final static String queryCuisineId = "query cuisine id";
    public final static String queryCuisineFrom = "query cuisine from";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuisinine);


        mLvCuisines = (ListView) findViewById(R.id.lv_cuisine_activity_cuisines);

        mDatabase = new Database(this);
        mCuinines = new ArrayList<ModelCuisine>();

        mCuinines = mDatabase.getSubCuisines(20);

        mAdapter = new CuisineActivityAdapter(mCuinines, this);
        mLvCuisines.setAdapter(mAdapter);

        mLvCuisines.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                query_cuisine_id = mCuinines.get(position).getId();
                query_cuisine_name = mCuinines.get(position).getName();
                Intent i = new Intent(CuisinineActivity.this, DirectoryListing.class);
                i.putExtra(queryCuisineName, query_cuisine_name);
                i.putExtra(queryCuisineId, query_cuisine_id);
                i.putExtra("LISTING_FROM", "cuisine");
                startActivity(i);
            }
        });
    }
}
