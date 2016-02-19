package com.yiptv.sam.samsretrofitpractice.Api;

import com.yiptv.sam.samsretrofitpractice.Model.WeatherInfo;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Sam on 2/3/16.
 */
public interface WeatherInfoApi {

    /**
     *  The following api calls are based on this link:
     *  http://api.openweathermap.org/data/2.5/weather?q=London
     */

    @GET("data/2.5/weather")
    Call<WeatherInfo> getWeatherForName(@Query("q") String name,
                                        @Query("appid") String apiId);

    @GET("data/2.5/weather")
    Call<WeatherInfo> getWeatherForCoords(@Query("lat") double lat,
                                          @Query("lon") double lon,
                                          @Query("appid") String apiId);
}
