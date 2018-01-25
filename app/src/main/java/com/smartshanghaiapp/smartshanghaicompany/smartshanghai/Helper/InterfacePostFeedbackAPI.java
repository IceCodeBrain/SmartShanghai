package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelResponseGeneral;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelResponseRegister;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Pithou on 05/04/2016.
 */
public interface InterfacePostFeedbackAPI {

    @FormUrlEncoded
    @POST("feedback")
    public Call<ModelResponseGeneral> postFeedback(@Field("key") String key,
                                                    @Field("appId") int appId,
                                                    @Field("versionNumber") String versionNUmber,
                                                    @Field("messageText") String messageTExt
    );


}
