package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper.InterfaceImagesOfGalleryAPI;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage.FragmentFeed;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfPictureOfGallery;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelPictureOfGallery;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class GalleryActivity extends Activity implements Callback<ModelListOfPictureOfGallery> {

    private GridView mGvGallery;
    private ArrayList<String> mListOfIdPictures;
    private ArrayList<ModelPictureOfGallery> mListOfObjectsPictures;
    private int mIdGalerySelected;
    private Context mContext;
    private ProgressBar mPgLoading;

    public final static String KEY_BUNDLE_PICTURE_LIST_IMAGES = "Key_bundle_picture_list_images";
    public final static String KEY_BUNDLE_PICTURE_SELECTED_POSITION = "Key_bundle_picture_selected_position";


    private GalleryActivityGridViewAdapter mGalleryGridViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        mContext = this;

        mPgLoading = (ProgressBar) findViewById(R.id.pg_gallery_activity_loading_images);

        mGvGallery = (GridView) findViewById(R.id.gv_activity_gallery);
        mListOfIdPictures = new ArrayList<String>();
        mListOfObjectsPictures = new ArrayList<ModelPictureOfGallery>();

        if (mListOfObjectsPictures.size() == 0 || mListOfObjectsPictures == null){
            mGvGallery.setVisibility(View.GONE);
            mPgLoading.setVisibility(View.VISIBLE);
        }

        mIdGalerySelected = -1;
        mIdGalerySelected = getIntent().getExtras().getInt(FragmentFeed.KEY_BUNDLE_GALLERY_SELECTED_ID);

        if (mIdGalerySelected != -1) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://www.smartshanghai.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            InterfaceImagesOfGalleryAPI api = retrofit.create(InterfaceImagesOfGalleryAPI.class);

            Call<ModelListOfPictureOfGallery> callListOfPicture = api.getPicturesOfGallery("gallery/" + mIdGalerySelected + "/pictures?key=abc123iphone");
            callListOfPicture.enqueue(this);
        }


        mGvGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(GalleryActivity.this, FullScreenPictureActivity.class);
                i.putExtra(KEY_BUNDLE_PICTURE_LIST_IMAGES, mListOfObjectsPictures);
                i.putExtra(KEY_BUNDLE_PICTURE_SELECTED_POSITION, position);
                startActivity(i);

            }
        });


    }

    @Override
    public void onResponse(Response<ModelListOfPictureOfGallery> response, Retrofit retrofit) {
        response.body();

        if (response.body().data != null && response.body().data.size() != 0) {
            for (int i = 0; i < response.body().data.size(); i++) {
                mListOfObjectsPictures.add(new ModelPictureOfGallery(
                        response.body().data.get(i).getPixid(),
                        response.body().data.get(i).getSmartchinaid(),
                        response.body().data.get(i).getGalid(),
                        response.body().data.get(i).getUptime()

                ));
                mListOfIdPictures.add(String.valueOf(response.body().data.get(i).getSmartchinaid()));
            }
        }

        mGvGallery.setVisibility(View.VISIBLE);
        mPgLoading.setVisibility(View.GONE);
        mGalleryGridViewAdapter = new GalleryActivityGridViewAdapter(mContext, R.layout.custom_item_gridview_gallery_image, mListOfIdPictures);
        mGvGallery.setAdapter(mGalleryGridViewAdapter);
    }

    @Override
    public void onFailure(Throwable t) {

    }
}
