package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfEvents;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Url;

/**
 * Created by Pithou on 05/04/2016.
 */
public interface InterfaceEventsAPI {

    @GET("?tagName=nightlife&key=abc123iphone")
    public Call<ModelListOfEvents> getEventsListNightLife();

    @GET("?tagName=artExhibitions&key=abc123iphone")
    public Call<ModelListOfEvents> getEventsListartExhibitions();

    @GET("?tagName=happyHours&key=abc123iphone")
    public Call<ModelListOfEvents> getEventshappyHours();

    @GET("?tagName=liveMusic&key=abc123iphone")
    public Call<ModelListOfEvents> getEventsliveMusic();

    @GET("?tagName=markets&key=abc123iphone")
    public Call<ModelListOfEvents> getEventsListmarkets();

    @GET("?tagName=networking&key=abc123iphone")
    public Call<ModelListOfEvents> getEventsListnetworking();

    @GET("?tagName=ladiesNights&key=abc123iphone")
    public Call<ModelListOfEvents> getEventsListladiesNights();

    @GET("?tagName=brunchDeals&key=abc123iphone")
    public Call<ModelListOfEvents> getEventsListbrunchDeals();

    @GET("?tagName=lunchDeals&key=abc123iphone")
    public Call<ModelListOfEvents> getEventsListlunchDeals();

    @GET("?tagName=sports&key=abc123iphone")
    public Call<ModelListOfEvents> getEventsListsports();

    @GET("?tagName=stage&key=abc123iphone")
    public Call<ModelListOfEvents> getEventsListstage();

    @GET
    public Call<ModelListOfEvents> getEventList(@Url String url);





}
