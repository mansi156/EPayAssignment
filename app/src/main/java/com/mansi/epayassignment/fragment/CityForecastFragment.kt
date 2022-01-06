package com.mansi.epayassignment.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mansi.epayassignment.adapter.NextFiveDayAdapter
import com.mansi.epayassignment.adapter.NextHourAdapter
import com.mansi.epayassignment.databinding.FragmentCityforecastBinding
import com.mansi.epayassignment.model.Daily
import com.mansi.epayassignment.model.ForecastResponse
import com.mansi.epayassignment.model.Hourly
import com.mansi.epayassignment.utlis.Constants
import com.mansi.epayassignment.utlis.Status
import com.mansi.epayassignment.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


@AndroidEntryPoint
class CityForecastFragment : Fragment() {
    private val weatherViewModel: MainViewModel by viewModels()
    private lateinit var nextFiveDayAdapter: NextFiveDayAdapter
    private lateinit var nextHourAdapter: NextHourAdapter
    lateinit var binding: FragmentCityforecastBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCityforecastBinding.inflate(inflater, container, false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        weatherViewModel.getLatLng(arguments?.getString(Constants.TITLE))
        setupUI()
        setupObservers()
    }


    private fun setupUI() {
        val sdf = SimpleDateFormat("MMM dd HH:mm", Locale.getDefault())
        val currentDateandTime: String = sdf.format(Date())
        binding.lastUpadtedTv.text = "Last updated on $currentDateandTime"
        nextFiveDayAdapter = NextFiveDayAdapter(arrayListOf())
        nextHourAdapter = NextHourAdapter(arrayListOf())
        binding.nextDayRecyclerView.adapter = nextFiveDayAdapter
        binding.nextHoursRecyclerView.adapter = nextHourAdapter
    }

    private fun retrieveList(forecastResponse: ForecastResponse) {
        val next5Response : ArrayList<Daily>? = arrayListOf()
        for(i in 0..4) {
            next5Response?.add(forecastResponse.daily[i])
        }
        nextFiveDayAdapter.apply {
            next5Response?.let { setData(it) }
            notifyDataSetChanged()
        }
        nextHourAdapter.apply {
            setData(forecastResponse.hourly as ArrayList<Hourly>)
            notifyDataSetChanged()
        }
    }

    fun setupObservers() {
        weatherViewModel.forecastResponse.observe(this.requireActivity(), { response ->
            when (response?.status) {
                Status.SUCCESS -> {
                    response.data?.let { forecastResponse -> retrieveList(forecastResponse) }
                }
                Status.ERROR -> {
                    //Toast.makeText(this.context, "ERROR", Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> {
                    //Toast.makeText(this.context, "LOADING", Toast.LENGTH_LONG).show()
                }
            }
        })

    }
}