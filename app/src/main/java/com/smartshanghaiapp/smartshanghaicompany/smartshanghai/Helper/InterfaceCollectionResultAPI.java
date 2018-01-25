package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfCollectionResult;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfPictureOfGallery;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Url;

/**
 * Created by Pithou on 05/04/2016.
 */
public interface InterfaceCollectionResultAPI {


    @GET
    public Call<ModelListOfCollectionResult> getCollection(@Url String url);


}
