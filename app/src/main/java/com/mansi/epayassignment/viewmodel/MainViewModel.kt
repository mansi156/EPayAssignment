package com.mansi.epayassignment.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mansi.epayassignment.model.ForecastResponse
import com.mansi.epayassignment.respository.WeatherRepository
import com.mansi.epayassignment.utlis.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(private val weatherRepository: WeatherRepository): ViewModel() {

    val forecastResponse : MutableLiveData<Resource<ForecastResponse?>?> = MutableLiveData()

    fun getLatLng(title : String?){
        when(title){
            "Rio de Janeiro" -> getForeCastList(22.9068, 43.1729)
            "Beijing" -> getForeCastList(39.9042, 116.4074)
            "Los Angeles" -> getForeCastList(34.0522, 118.2437)
        }
    }
    private fun getForeCastList(lat: Double, lng: Double){
        viewModelScope.launch {
            weatherRepository.getForecasts(lat, lng)
                .catch {e->
                    forecastResponse.value = null
                    Log.d("main", "getPost: ${e.message}")
                }.collect {
                    forecastResponse.value = it
                    Log.d("response main", "getPost: $it")
                }
        }
    }

}
