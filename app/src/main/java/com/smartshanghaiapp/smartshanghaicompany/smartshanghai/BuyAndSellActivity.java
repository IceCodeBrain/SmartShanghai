package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.DatabaseDirectory.Database;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper.InterfaceArticlesAPI;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper.InterfaceBuyAndSellAPI;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelArticle;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelBuyAndSell;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelBuyAndSellCategory;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfArticles;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfBuyAndSell;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class BuyAndSellActivity extends Activity implements Callback<ModelListOfBuyAndSell> {

    private ArrayList<ModelBuyAndSell> mBuyAndSell;

    private BuyAndSellAdapter mAdapter;

    private ListView mLvBuyAndSell;
    private ProgressBar mPgBuyAndSell;

    private ArrayList<ModelBuyAndSellCategory> mCategories;
    private ArrayList<String> mCategoriesString;

    private SharedPreferences mSharedPreferences;
    private Database mDatabase;

    public final static String KEY_BUNDLE_BUY_AND_SELL_ID= "key_bundle_buy_and_sell_id";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_and_sell);

        mLvBuyAndSell = (ListView) findViewById(R.id.lv_buy_and_sell_activity);
        mPgBuyAndSell = (ProgressBar) findViewById(R.id.loadbar_buy_and_sell);
        mBuyAndSell = new ArrayList<>();
        mCategories = new ArrayList<>();
        mCategoriesString = new ArrayList<>();
        mDatabase = new Database(this);


        // Getting categories from the db and put it in a str list
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
        boolean mDbIsUsable = mSharedPreferences.getBoolean("dbIsUsable", false);
        if (mDatabase.checkDataBase() == true && mDbIsUsable) {
            ModelBuyAndSellCategory modelBuyAndSellCategory = new ModelBuyAndSellCategory(1, "All", 1);// add the category all with the id 1
            mCategories.add(modelBuyAndSellCategory);
            mCategories.addAll(mDatabase.getCategoriesBuyAndSell());
            if (mCategories.size() > 0){
                for (int i = 0 ; i < mCategories.size() ; i++){
                    mCategoriesString.add(mCategories.get(i).getTag_name());
                }
            }
        }


        final Spinner mySpinner = (Spinner) findViewById(R.id.spin_buy_and_sell_act_cat);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.custom_item_spinner_buy_and_sell,
                R.id.tv_custom_spinner_buy_and_sell,
                mCategoriesString);
        mySpinner.setAdapter(adapter);


        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mBuyAndSell.clear();
                callBuyAndSellItems(mCategories.get(position).getTag_id());
                // call the items
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nothing can't be selected, default is all
            }
        });


        mLvBuyAndSell.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mBuyAndSell.get(position).getId() > 0) {
                   Intent i = new Intent(BuyAndSellActivity.this, OnlineFrameActivity.class);
                   String url = "http://www.smartshanghai.com/buyandsell/all/" + mBuyAndSell.get(position).getId() + "?source=android";
                   i.putExtra(KEY_BUNDLE_BUY_AND_SELL_ID, url);
                    startActivity(i);
                }
            }
        });


    }

    private void callBuyAndSellItems(int idCat) {
        mLvBuyAndSell.setVisibility(View.GONE);
        mPgBuyAndSell.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.smartshanghai.com/api/buyandsell")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfaceBuyAndSellAPI api = retrofit.create(InterfaceBuyAndSellAPI.class);
        String url = "";
        if (idCat == 1) {// Category is ALL
            url = "?key=" + getResources().getString(R.string.key_webservices_android);
        } else if (idCat > 1){
            url = "?categoryId=" + idCat + "&key=" + getResources().getString(R.string.key_webservices_android);
        }
        Call<ModelListOfBuyAndSell> callBuyANdSellList = api.getBuyAndSellList(url);
        callBuyANdSellList.enqueue(this);
    }


    @Override
    public void onResponse(Response<ModelListOfBuyAndSell> response, Retrofit retrofit) {
        response.body();

        mLvBuyAndSell.setVisibility(View.VISIBLE);
        mPgBuyAndSell.setVisibility(View.GONE);

        if (response.body().data != null && response.body().data.size() != 0) {
            for (int i = 0; i < response.body().data.size(); i++) {
                mBuyAndSell.add(new ModelBuyAndSell(
                        response.body().data.get(i).getId(),
                        response.body().data.get(i).getTitle(),
                        response.body().data.get(i).getPrice(),
                        response.body().data.get(i).getDescription(),
                        response.body().data.get(i).getDatePosted(),
                        response.body().data.get(i).getThumb()
                        ));
            }
        }

        mAdapter = new BuyAndSellAdapter(mBuyAndSell, this);
        mLvBuyAndSell.setAdapter(mAdapter);
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(this, "No buy & sell listing found", Toast.LENGTH_SHORT).show();
        mPgBuyAndSell.setVisibility(View.GONE);



    }



}
