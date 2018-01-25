package com.example.anti.coinapi.api;

import com.example.anti.coinapi.Models.Coins;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {



    @GET("ticker")
    Call<ArrayList<Coins>> getCoins();

    @GET("ticker/{id}")
    Call<ArrayList<Coins>> getBtc(@Path ("id") String id);


//    @GET(ApiConstants.PHOTOS_ENDPOINT+"search?"+ApiConstants.ConsumerKey)
//    Call<PhotosModel> getPhotosSearch(@Query("term") String featureString);

//
//    @GET("{link}")
//    Call<PhotosModel> getPhotosRefreshSearch(@Path(value = "link", encoded = true) String link);
//
//
//    @FormUrlEncoded
//    @POST("photos")
//    Call<Item> uploadPhoto(@Field("name") String stringName, @Body Item photo);

}
