package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfEvents;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Url;

/**
 * Created by Pithou on 05/04/2016.
 */
public interface InterfaceTodayEventsAPI {

    @GET
    public Call<ModelListOfEvents> getEventsListToday(@Url String url);


}
