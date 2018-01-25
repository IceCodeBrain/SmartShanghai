package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfArticles;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfJobs;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Url;

/**
 * Created by Pithou on 05/04/2016.
 */
public interface InterfaceJobsAPI {

    @GET
    public Call<ModelListOfJobs> getJobList(@Url String url);

}
