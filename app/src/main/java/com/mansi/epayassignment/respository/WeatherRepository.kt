package com.mansi.epayassignment.respository

import com.mansi.epayassignment.network.WeatherServiceImpl
import com.mansi.epayassignment.model.ForecastResponse
import com.mansi.epayassignment.utlis.Constants
import com.mansi.epayassignment.utlis.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherServiceImpl: WeatherServiceImpl) {

    fun getForecasts(
        lan: Double,
        lon: Double
    ): Flow<Resource<ForecastResponse>> = flow {
        val response = weatherServiceImpl.getForeCasts(
            lan,
            lon,
            Constants.EXCLUDE, Constants.API_KEY
        )

        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = response))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }

    }.flowOn(Dispatchers.IO)

}
