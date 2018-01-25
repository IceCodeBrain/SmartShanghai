package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelResponsePostReview;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelResponseRegister;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Pithou on 05/04/2016.
 */
public interface InterfacePostRegisterUserAPI {

    @FormUrlEncoded
    @POST("register")
    public Call<ModelResponseRegister> postRegister(@Field("key") String key,
                                                    @Field("emailAddress") String emailAddress,
                                                    @Field("username") String username,
                                                    @Field("password") String password,
                                                    @Field("sourceId") int sourceId
                                                    );


}
