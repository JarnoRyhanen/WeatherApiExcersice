package com.home.weatherapiexcersice.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.home.weatherapiexcersice.api.WeatherApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(private val weatherApi: WeatherApi) {

    fun getWeatherResults(
        lon: Float,
        lat: Float,
        unit: String,
        output: String,
        tzshift: Int
    ) = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            WeatherPagingSource(
                weatherApi,
                lon,
                lat,
                unit,
                output,
                tzshift
            )
        }
    ).liveData


}