package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfCollections;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfPictureOfGallery;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Url;

/**
 * Created by Pithou on 05/04/2016.
 */
public interface InterfaceImagesOfGalleryAPI {


    @GET
    public Call<ModelListOfPictureOfGallery> getPicturesOfGallery(@Url String url);


}
