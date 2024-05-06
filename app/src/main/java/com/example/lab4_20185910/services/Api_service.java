package com.example.lab4_20185910.services;

import com.example.lab4_20185910.dto.Geolocalizacion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api_service {

    @GET("/geo/1.0/direct")
    Call<List<Geolocalizacion>> getGeolocalizacion(@Query("q") String q, @Query("limit") Integer number, @Query("appid") String appId);

    //@GET("/data/2.5/weather")
    //Call<Clima> getWeather(@Query("lat") Double lat, @Query("lon") Double lon, @Query("appid") String appId);

}
