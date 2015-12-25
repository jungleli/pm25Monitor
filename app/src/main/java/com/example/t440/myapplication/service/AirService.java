package com.example.t440.myapplication.service;

import com.example.t440.myapplication.domain.PM25;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface AirService {

    @GET("/api/querys/aqi_details.json")
    Call<List<PM25>> getPM25(@Query("token") String token, @Query("city") String city);
}
