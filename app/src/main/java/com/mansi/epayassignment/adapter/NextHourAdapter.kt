package com.mansi.epayassignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mansi.epayassignment.databinding.RowNextHoursLayoutBinding
import com.mansi.epayassignment.model.Hourly
import com.mansi.epayassignment.utlis.Constants
import com.mansi.epayassignment.utlis.Utils

class NextHourAdapter(private var forecastsList: ArrayList<Hourly>) :
    RecyclerView.Adapter<NextHourAdapter.ForecastViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val itemBinding =
            RowNextHoursLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForecastViewHolder(itemBinding, parent.context)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val forecasts: Hourly = forecastsList[position]
        holder.bind(forecasts)
    }

    override fun getItemCount(): Int = forecastsList.size

    class ForecastViewHolder(
        private val itemBinding: RowNextHoursLayoutBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(hourData: Hourly) {
            val temp = Utils.convertCelsius(hourData.temp)
            val humidity = "${hourData.humidity}%"
            itemBinding.maxTempTv.text = "$temp \u2103"
            itemBinding.humidityTv.text = humidity
            itemBinding.timeTv.text = Utils.getmDate(hourData.dt.toLong(),"hh:mm aa")

            val dailyImageIcon = Constants.IMAGE_URL + "${hourData.weather[0].icon}.png"
            Glide.with(this.context)
                .load(dailyImageIcon)
                .into(itemBinding.ivWeatherIcon)
        }
    }

    fun setData(forecastsList: ArrayList<Hourly>) {
        this.forecastsList = forecastsList
        notifyDataSetChanged()
    }
}
