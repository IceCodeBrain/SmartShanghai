package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfFeedItems;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelResponseGeneral;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Url;

/**
 * Created by Pithou on 05/04/2016.
 */
public interface InterfaceReportMistakeAPI {


    @FormUrlEncoded
    @POST("reportmistake")
    public Call<ModelResponseGeneral> postReportWithID(@Field("key") String key,
                                                    @Field("messageText") String messageText,
                                                    @Field("venueID") int venueID);



    @FormUrlEncoded
    @POST("reportmistake")
    public Call<ModelResponseGeneral> postReportWithName(@Field("key") String key,
                                                 @Field("messageText") String messageText,
                                                 @Field("venueName") String venueName);

}
