package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfReviewsVenue;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfTickets;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Url;

/**
 * Created by Pithou on 05/04/2016.
 */
public interface InterfaceReviewVenuesAPI {


    @GET
    public Call<ModelListOfReviewsVenue> getReviewsVenue(@Url String url);


}
