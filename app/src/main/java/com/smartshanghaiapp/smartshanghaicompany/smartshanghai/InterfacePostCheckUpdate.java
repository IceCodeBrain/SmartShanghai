package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelResponseGeneral;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelResponseUpdate;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Pithou on 05/04/2016.
 */
public interface InterfacePostCheckUpdate {

    @FormUrlEncoded
    @POST("getLatestAndroidAppVersion")
    public Call<ModelResponseUpdate> postUpdateRequest(@Field("key") String key,
                                                   @Field("versionNumber") String versionNumber
    );


}
