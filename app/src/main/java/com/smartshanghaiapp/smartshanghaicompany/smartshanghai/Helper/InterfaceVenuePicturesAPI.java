package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfCollectionResult;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfVenueImage;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Url;

/**
 * Created by Pithou on 05/04/2016.
 */
public interface InterfaceVenuePicturesAPI {


    @GET
    public Call<ModelListOfVenueImage> getVenueImages(@Url String url);


}
