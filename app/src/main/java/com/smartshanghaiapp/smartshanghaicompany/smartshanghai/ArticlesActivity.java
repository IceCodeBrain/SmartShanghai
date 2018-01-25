package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper.InterfaceArticlesAPI;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage.MainActivity;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelArticle;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelFeedItem;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfArticles;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ArticlesActivity extends Activity implements Callback<ModelListOfArticles> {

    private Intent mIntent;
    private String mTagId;

    private TextView mTvTitle;

    private ArrayList<ModelArticle> mArticles;

    private ArticlesActivityAdapter mAdapter;

    private ListView mLvArticles;
    private ProgressBar mPgArticles;

    public final static String KEY_BUNDLE_ARTICLES_ARTICLE_ID = "key_bundle_articles_article_id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        mIntent = getIntent();
        mTagId = mIntent.getStringExtra("tag_id");
        String title = mIntent.getStringExtra("tag_name");

        mLvArticles = (ListView) findViewById(R.id.lv_article_activity);
        mTvTitle = (TextView) findViewById(R.id.tv_article_activity_top_bar);
        mPgArticles = (ProgressBar) findViewById(R.id.loadbar_article_activity);
        mArticles = new ArrayList<>();

        if (title != null && !title.equals(""))
            mTvTitle.setText(title);


        if (mTagId != null && !mTagId.equals("")){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://www.smartshanghai.com/api/articles")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            InterfaceArticlesAPI api = retrofit.create(InterfaceArticlesAPI.class);

            String url = "?tagId=" + mTagId + "&key=" + getResources().getString(R.string.key_webservices_android);

            Call<ModelListOfArticles> callListOfArticles = api.getArticlesList(url);
            callListOfArticles.enqueue(this);
        }


        mLvArticles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mArticles.get(position).getUrl() != null && !mArticles.get(position).getUrl().equals("")) {
                    Intent i = new Intent(ArticlesActivity.this, OnlineFrameActivity.class);
                    i.putExtra(KEY_BUNDLE_ARTICLES_ARTICLE_ID, mArticles.get(position).getUrl() + "?source=android");
                    startActivity(i);
                }
            }
        });


    }


    @Override
    public void onResponse(Response<ModelListOfArticles> response, Retrofit retrofit) {

        response.body();

        mLvArticles.setVisibility(View.VISIBLE);
        mPgArticles.setVisibility(View.GONE);


        if (response.body().data != null && response.body().data.size() != 0) {
            for (int i = 0; i < response.body().data.size(); i++) {
                mArticles.add(new ModelArticle(
                        response.body().data.get(i).getId(),
                        response.body().data.get(i).getTitle(),
                        response.body().data.get(i).getUrl(),
                        response.body().data.get(i).getTeaser(),
                        response.body().data.get(i).getDatePublished(),
                        response.body().data.get(i).getCoverImage()
                ));
            }
        }

        mAdapter = new ArticlesActivityAdapter(mArticles, this);
        mLvArticles.setAdapter(mAdapter);

    }

    @Override
    public void onFailure(Throwable t) {

    }
}
