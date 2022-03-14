package com.home.weatherapiexcersice.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.home.weatherapiexcersice.data.WeatherObject
import com.home.weatherapiexcersice.databinding.RecyclerViewListRowBinding

class RecyclerViewWeatherAdapter : PagingDataAdapter<WeatherObject, RecyclerViewWeatherAdapter.RecyclerViewHolder>(
    OBJECT_COMPARATOR
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding =
            RecyclerViewListRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            Log.d("RECYCLERVIEW", "onBindViewHolder: $currentItem")
            holder.bind(currentItem)
        }
    }

    inner class RecyclerViewHolder(private val binding: RecyclerViewListRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(weatherObject: WeatherObject) {
            binding.apply {
                weather.text = weatherObject.dataseries.weather
                temp2m.text = weatherObject.dataseries.temp2m.toString()
                Log.d("Recyclerviewweatheradap", "bind: ${weatherObject.dataseries.weather}")
            }
        }
    }

    companion object {
        private val OBJECT_COMPARATOR = object : DiffUtil.ItemCallback<WeatherObject>() {
            override fun areItemsTheSame(oldItem: WeatherObject, newItem: WeatherObject) =
                oldItem.init == newItem.init

            override fun areContentsTheSame(
                oldItem: WeatherObject,
                newItem: WeatherObject
            ) = oldItem == newItem
        }
    }
}