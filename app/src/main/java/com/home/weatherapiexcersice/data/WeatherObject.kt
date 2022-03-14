package com.home.weatherapiexcersice.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherObject(

    val product: String,
    val init: Int,
    val dataseries: WeatherData
) : Parcelable {

    @Parcelize
    data class WeatherData(
        val weather: String,
        val timepoint: Int,
        val temp2m: Int,
        val cloudcover: String
    ) : Parcelable
}
