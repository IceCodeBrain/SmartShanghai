package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper;

import android.util.TypedValue;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfCollections;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelResponsePostReview;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelReviewToPost;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by Pithou on 05/04/2016.
 */
public interface InterfacePostReviewAPI {

    @FormUrlEncoded
    @POST("review")
    public Call<ModelResponsePostReview> postReview(@Field("key") String key,
                                                    @Field("emailAddress") String emailAddress,
                                                    @Field("password") String password,
                                                    @Field("user_id") int user_id,
                                                    @Field("cityId") int cityId,
                                                    @Field("rev_quote") String rev_quote,
                                                    @Field("rev_venue") int rev_venue,
                                                    @Field("rev_totalrating") int rev_totalrating,
                                                    @Field("source_id") int source_id,
                                                    @Field("rev_food") int rev_food,
                                                    @Field("rev_service") int rev_service,
                                                    @Field("rev_txt") String rev_txt,
                                                    @Field("rev_decor") int rev_decor,
                                                    @Field("rev_value") int rev_value,
                                                    @Field("rev_drinks") int rev_drinks,
                                                    @Field("rev_goodforfriends") int rev_goodforfriends,
                                                    @Field("rev_goodforbusiness") int rev_goodforbusiness,
                                                    @Field("rev_goodforfamily") int rev_goodforfamily,
                                                    @Field("rev_goodfordate") int rev_goodfordate);


}
