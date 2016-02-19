package com.yiptv.sam.samsretrofitpractice.Service;

import com.yiptv.sam.samsretrofitpractice.Api.WeatherInfoApi;

import java.io.IOException;

import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 *  a service, which will handle Retrofit’s configuration
 *  and will expose an API for other components (like Activities) to use.
 */
public class WeatherInfoService  {

    private WeatherInfoApi weatherInfoApi;


    /**
     * The constructor takes care of configuring Retrofit, by: 
     * — Setting the requests’ base URL.
     * — Setting the library it’ll use to convert the responses’ body into our model structure
     * (This is why we had to include Retrofit’s GSON converter in our dependencies)
     */
    public WeatherInfoService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(" http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherInfoApi = retrofit.create(WeatherInfoApi.class);
    }


    /**
     * Notice the methods receive a Callback as a parameter.
     * This will allow the services and activities which use our service
     * to specify tasks they want to be executed when the response arrives.
     * They are queued to be executed asynchronously,
     * so that the calling thread doesn’t get blocked,
     * avoiding causing a weird experience for the user.
     */

    public void getWeatherForName(String name, Callback callback, String apiId) throws IOException{
        weatherInfoApi.getWeatherForName(name,apiId).enqueue(callback);
    }

    public void getWeatherForCoords(double lat, double lon, Callback callback, String apiId){
        weatherInfoApi.getWeatherForCoords(lat,lon,apiId).enqueue(callback);
    }
}
