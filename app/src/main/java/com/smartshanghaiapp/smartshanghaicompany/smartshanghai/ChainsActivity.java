package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.DatabaseDirectory.Database;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelChain;

import java.util.ArrayList;

public class ChainsActivity extends Activity {

    private ListView mLvChains;

    private ArrayList<ModelChain> mChains;

    private Database mDatabase;

    private ChainsActivityAdapter mAdapter;


    private int query_chain_id;
    private String query_chain_name;

    public final static String queryChainName = "query chain name";
    public final static String queryChainId = "query chain id";
    public final static String queryChainFrom = "query chain from";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chains);

        mLvChains = (ListView) findViewById(R.id.lv_chain_activity_chain);

        mDatabase = new Database(this);
        mChains = new ArrayList<ModelChain>();
        mChains = mDatabase.getChainsItems(12);

        mAdapter = new ChainsActivityAdapter(mChains, this);
        mLvChains.setAdapter(mAdapter);

        mLvChains.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                query_chain_id = mChains.get(position).getId();
                query_chain_name = mChains.get(position).getName();
                Intent i = new Intent(ChainsActivity.this, DirectoryListing.class);
                i.putExtra(queryChainName, query_chain_name);
                i.putExtra(queryChainId, query_chain_id);
                i.putExtra("LISTING_FROM", "chain");
                startActivity(i);
            }
        });

    }
}
