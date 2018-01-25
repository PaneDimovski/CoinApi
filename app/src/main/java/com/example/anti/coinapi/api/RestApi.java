package com.example.anti.coinapi.api;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.anti.coinapi.BuildConfig;
import com.example.anti.coinapi.Models.Coins;
import com.example.anti.coinapi.others.LoggingInterceptor;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RestApi {

    public static final int request_max_time_in_secconds = 20;

    private Context activity;

    public RestApi(Context activity) {
        this.activity = activity;
    }

    public Retrofit getRetrofitInstance() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .readTimeout(request_max_time_in_secconds, TimeUnit.SECONDS)
                .connectTimeout(request_max_time_in_secconds, TimeUnit.SECONDS)
                .build();
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


    }

    public ApiService request() {

        return getRetrofitInstance().create(ApiService.class);
    }

    public  Call<ArrayList<Coins>> getBtc ( String btc) {return  request().getBtc(  btc);}

    public Call<ArrayList<Coins>> getCoins()
    {
        return request().getCoins();
    }
//
//    public Call<PhotosModel> getPhotosSearch(String keyword)
//    {
//        return request().getPhotosSearch(keyword);
//    }
//
//    public Call<PhotosModel> getRefPhotosSearch(String link)
//    {
//        return request().getPhotosRefreshSearch(link);
//    }
}