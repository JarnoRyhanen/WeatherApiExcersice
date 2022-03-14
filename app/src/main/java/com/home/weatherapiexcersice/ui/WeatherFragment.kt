package com.home.weatherapiexcersice.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.home.weatherapiexcersice.R
import com.home.weatherapiexcersice.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "WeatherFragment"

@AndroidEntryPoint
class WeatherFragment : Fragment(R.layout.fragment_main) {

    private val viewModel by viewModels<WeatherViewModel>()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMainBinding.bind(view)
        val adapter = RecyclerViewWeatherAdapter()

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
        }

        viewModel.weather.observe(viewLifecycleOwner) {
            Log.d(TAG, "onViewCreated: data went to adapter")
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}