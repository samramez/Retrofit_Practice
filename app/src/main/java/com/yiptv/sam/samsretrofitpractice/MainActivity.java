package com.yiptv.sam.samsretrofitpractice;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.yiptv.sam.samsretrofitpractice.Model.Weather;
import com.yiptv.sam.samsretrofitpractice.Model.WeatherInfo;
import com.yiptv.sam.samsretrofitpractice.Service.WeatherInfoService;

import java.io.IOException;

import retrofit.Callback;
import retrofit.Response;

public class MainActivity extends AppCompatActivity {

    public WeatherInfoService mWeatherInfoService;

    private TextView mTextView;
    private String apiId = "44db6a862fba0b067b1930da0d769e98" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mWeatherInfoService = new WeatherInfoService();

        mTextView = (TextView) findViewById(R.id.textView);

        getWeatherForNameInActivity("London");
    }

    private void getWeatherForNameInActivity(String name){
        try {
            mWeatherInfoService.getWeatherForName(name, new Callback() {
                @Override
                public void onResponse(Response response) {

                    // response.body holds the response, mapped to our model.
                    WeatherInfo weatherInfo = (WeatherInfo) response.body();
                    // Do something with weatherInfo, like
                    // String description = weatherInfo.getWeather()[0].getDescription();
                    // which will be something like "Sky is Clear".

                    String info = weatherInfo.getWeather()[0].getDescription();

                    mTextView.setText(info);
                }

                // This one's called when it goes bad :(
                @Override
                public void onFailure(Throwable t) {
                    // Tell the user there was an error.
                }
            },apiId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getWeatherForCoords(double lat,double lon){
        mWeatherInfoService.getWeatherForCoords(lat, lon, new Callback() {
            @Override
            public void onResponse(Response response) {
                Weather weather = (Weather) response.body();
            }

            @Override
            public void onFailure(Throwable t) {

            }
        },apiId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
