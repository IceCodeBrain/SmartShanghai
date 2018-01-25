package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.DraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage.MainActivity;


public class FragmentPictureOfGalleryFullScreen extends Fragment implements FragmentLifeCycle {

    private static final String TAG = FragmentPictureOfGalleryFullScreen.class.getSimpleName();
    public static Context mContext;

    private DraweeView dvPictureDisplayed;

    private int mIdPictureDisplayed;

    private int mPosition;


    public FragmentPictureOfGalleryFullScreen() {
    }

    public FragmentPictureOfGalleryFullScreen(int position) {
        this.mPosition = position;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_full_screen_image, container, false);
        setRetainInstance(true);

        dvPictureDisplayed = (DraweeView) view.findViewById(R.id.dv_fragment_full_screen_picture);

        mIdPictureDisplayed = mPosition;

        Uri imageUri = Uri.parse("http://images.smartchina.com/image.php?id=" + mIdPictureDisplayed);
        dvPictureDisplayed.setImageURI(imageUri);

        return view;
    }

    @Override
    public void onPauseFragment() {

    }

    @Override
    public void onResumeFragment() {


    }

    @Override
    public void onBackPressed() {

    }

}



