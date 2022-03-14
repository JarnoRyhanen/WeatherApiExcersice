package com.home.weatherapiexcersice.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.home.weatherapiexcersice.data.WeatherRepository

class WeatherViewModel @ViewModelInject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    private val currentQuery = MutableLiveData(
        arrayOf(
            DEFAULT_LATITUDE.toFloat(), DEFAULT_LONGITUDE.toFloat(), DEFAULT_UNIT, DEFAULT_OUTPUT,
            DEFAULT_TIMEZONE_SHIFT
        )
    )

    val weather = currentQuery.switchMap { queryString ->
        repository.getWeatherResults(
            DEFAULT_LONGITUDE.toFloat(),
            DEFAULT_LATITUDE.toFloat(),
            DEFAULT_UNIT,
            DEFAULT_OUTPUT,
            DEFAULT_TIMEZONE_SHIFT
        )
    }.cachedIn(viewModelScope)


    companion object {
        private const val DEFAULT_LONGITUDE = 24.945831
        private const val DEFAULT_LATITUDE = 60.192059
        private const val DEFAULT_UNIT = "metric"
        private const val DEFAULT_OUTPUT = "json"
        private const val DEFAULT_TIMEZONE_SHIFT = 1
    }

}