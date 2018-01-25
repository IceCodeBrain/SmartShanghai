package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage.FragmentDirectory;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelCollection;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfCollections;

import java.util.ArrayList;

public class ListOfCollections extends Activity {

    private ModelListOfCollections mModelListOfCollection;
    private ArrayList<ModelCollection> mListOfCollections;


    private ListView mCollectionListview;
    private ListOfCollectionsAdapter mAdapter;

    public final static String KEY_BUNDLE_COLLECTION_FROM_COLLECTIONS = "Key_bundle_collection_from_collections";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_collections);

        mModelListOfCollection = getIntent().getExtras().getParcelable(FragmentDirectory.KEY_BUNDLE_LIST_OF_COLLECTION);
        mListOfCollections = mModelListOfCollection.getData();


        mCollectionListview = (ListView) findViewById(R.id.lv_list_of_collections_activity);

        mAdapter = new ListOfCollectionsAdapter(mListOfCollections, this);
        mCollectionListview.setAdapter(mAdapter);

        mCollectionListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mListOfCollections.get(position).getTag_id() != 305) { // 305 is recently opened
                    Intent i = new Intent(ListOfCollections.this, CollectionActivity.class);
                    i.putExtra(KEY_BUNDLE_COLLECTION_FROM_COLLECTIONS, mListOfCollections.get(position));
                    startActivity(i);
                } else {
                    Intent i = new Intent(ListOfCollections.this, RecentlyOpenedActivity.class);
                    startActivity(i);
                }
            }
        });





    }
}
