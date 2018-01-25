package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfCollections;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelResultLogin;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Url;

/**
 * Created by Pithou on 05/04/2016.
 */
public interface InterfaceLoginAPI {

    @FormUrlEncoded
    @POST("login")
    public Call<ModelResultLogin> postLogin (@Field("key") String key,
                                                    @Field("emailAddress") String emailAddress,
                                                    @Field("password") String password
    );

}
