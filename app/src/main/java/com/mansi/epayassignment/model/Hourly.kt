package com.mansi.epayassignment.model

data class Hourly(
    val clouds: Int,
    val dew_point: Double,
    val dt: Int,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val weather: List<Weather>,
    val wind_deg: Int,
    val wind_gust: Double,
    val wind_speed: Double
)