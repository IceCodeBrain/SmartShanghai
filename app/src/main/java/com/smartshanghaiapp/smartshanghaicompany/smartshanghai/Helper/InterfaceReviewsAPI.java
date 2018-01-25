package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfFeedItems;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfReviewsVenue;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelReviewVenue;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Url;

/**
 * Created by Pithou on 05/04/2016.
 */
public interface InterfaceReviewsAPI {


    @GET
    public Call<ModelListOfReviewsVenue> getReviews (@Url String url);


}
