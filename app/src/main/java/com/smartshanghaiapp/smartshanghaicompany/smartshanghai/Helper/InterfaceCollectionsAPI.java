package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfCollections;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfTickets;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by Pithou on 05/04/2016.
 */
public interface InterfaceCollectionsAPI {


    @GET("collections?key=abc123iphone")
    public Call<ModelListOfCollections> getCollectionsList();


}
