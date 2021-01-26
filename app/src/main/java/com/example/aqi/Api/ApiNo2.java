package com.example.aqi.Api;

import com.example.aqi.pojo.Example;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiNo2 {
    @GET("/HUST/pm2.5/10")
    Call<List<Example>> getChart();
}
