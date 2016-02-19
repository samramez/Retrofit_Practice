package com.yiptv.sam.samsretrofitpractice.Model;

/**
 * Created by Sam on 2/3/16.
 */
public class WeatherInfo {

    private Weather[] weather;
    private String name;

    public Weather[] getWeather() {
        return weather;
    }

    public void setWeather(Weather[] weather) {
        this.weather = weather;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        if(name == null){
            return "No data specified.";
        }
        return "Weather information for " + name + ":\n" + weather[0].toString();
    }
}
