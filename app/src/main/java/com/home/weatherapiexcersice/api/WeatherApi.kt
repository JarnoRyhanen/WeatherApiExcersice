package com.home.weatherapiexcersice.api

import com.home.weatherapiexcersice.api.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    companion object {
        const val BASE_URL = "https://www.7timer.info/"
    }

    @GET("bin/civil.php")
    suspend fun getWeatherData(
        @Query("lon") lon: Float,
        @Query("lat") lat: Float,
        @Query("unit") unit: String,
        @Query("output") output: String,
        @Query("tzshift") tzshift: Int
    ): WeatherResponse

}