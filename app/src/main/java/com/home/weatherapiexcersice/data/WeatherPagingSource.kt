package com.home.weatherapiexcersice.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.home.weatherapiexcersice.api.WeatherApi
import retrofit2.HttpException
import java.io.IOException
import kotlin.math.log

private const val WEATHER_STARTING_PAGE_INDEX = 1
private const val TAG = "WeatherPagingSource"
class WeatherPagingSource(
    private val weatherApi: WeatherApi,
    private val lon: Float,
    private val lat: Float,
    private val unit: String,
    private val output: String,
    private val tzshift: Int
) : PagingSource<Int, WeatherObject>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, WeatherObject> {
        val position = params.key ?: WEATHER_STARTING_PAGE_INDEX

        Log.d(TAG, "load: $lon, $lat, $unit ,$output, $tzshift" )

       return try {
            val response = weatherApi.getWeatherData(
                lon,
                lat,
                unit,
                output,
                tzshift
            )
            val weather = response.results
            LoadResult.Page(
                data = weather,
                prevKey = if (position == WEATHER_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (weather.isEmpty()) null else position + 1
            )
           
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, WeatherObject>): Int? {
        TODO("Not yet implemented")
    }
}
