package com.mansi.epayassignment.network

import com.mansi.epayassignment.model.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("onecall")
    suspend fun getForecasts(@Query("lat") lan: Double,
                             @Query("lon") lon : Double,
                             @Query("exclude") exclude : String,
                             @Query("appid") appId : String)
            : ForecastResponse

}