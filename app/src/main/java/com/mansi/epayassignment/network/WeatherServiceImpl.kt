package com.mansi.epayassignment.network

import com.mansi.epayassignment.model.ForecastResponse
import javax.inject.Inject

class WeatherServiceImpl @Inject constructor(private val weatherApiService: WeatherApiService) {

    suspend fun getForeCasts(lan: Double,
                             lon : Double,
                             exclude : String,
                             appId : String) : ForecastResponse = weatherApiService.getForecasts(lan,lon,exclude,appId)
}
