package com.example.aqi.Api;

import com.example.aqi.pojo.Example;
import com.example.aqi.pojo.PiChart;
import com.google.gson.JsonArray;

import org.json.JSONArray;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("chart.json")
    Call<PiChart> init();
    @GET("/HUST/no2/10")
    Call<List<Example>> getChart();
}
