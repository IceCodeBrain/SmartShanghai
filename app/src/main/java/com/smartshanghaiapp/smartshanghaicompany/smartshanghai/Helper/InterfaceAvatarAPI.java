package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfFeedItems;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelResponseAvatar;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Url;

/**
 * Created by Pithou on 05/04/2016.
 */
public interface InterfaceAvatarAPI {


    @GET
    public Call<ModelResponseAvatar> getAvatar(@Url String url);


}
