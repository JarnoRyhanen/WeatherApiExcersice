package com.home.weatherapiexcersice.api

import com.home.weatherapiexcersice.data.WeatherObject

data class WeatherResponse(
    val results: List<WeatherObject>
)
