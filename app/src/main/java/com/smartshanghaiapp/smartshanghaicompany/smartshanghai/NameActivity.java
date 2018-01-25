package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AlphabetIndexer;
import android.widget.ListView;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.DatabaseDirectory.Database;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelChain;

import java.util.ArrayList;

public class NameActivity extends Activity {

    private ListView mLvLetter;

    private ArrayList<String> mLetters;

    private Database mDatabase;

    private NameActivityAdapter mAdapter;


    private String query_letter_selected;

    public final static String queryLetterSelected = "query letter selected";
    public final static String queryNameFrom = "query name from";
    public final static String queryNameId = "query name id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        mLvLetter = (ListView) findViewById(R.id.lv_activity_name_letters);

        mDatabase = new Database(this);

        mLetters = new ArrayList<String>();
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        for (int i = 0 ; i < alphabet.length ; i++){
            mLetters.add(String.valueOf(alphabet[i]));
        }

        mAdapter = new NameActivityAdapter(mLetters, this);
        mLvLetter.setAdapter(mAdapter);

        mLvLetter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                query_letter_selected = mLetters.get(position);
                Intent i = new Intent(NameActivity.this, DirectoryListing.class);
                i.putExtra(queryNameId, -1); // -1 because no need of id
                i.putExtra(queryLetterSelected, query_letter_selected);
                i.putExtra("LISTING_FROM", "name");
                startActivity(i);
            }
        });


    }
}
