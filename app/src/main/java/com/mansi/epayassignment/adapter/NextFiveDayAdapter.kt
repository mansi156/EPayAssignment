package com.mansi.epayassignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mansi.epayassignment.R
import com.mansi.epayassignment.databinding.RowNextDaysLayoutBinding
import com.mansi.epayassignment.model.Daily
import com.mansi.epayassignment.utlis.Constants
import com.mansi.epayassignment.utlis.Utils

class NextFiveDayAdapter(private var forecastsList: ArrayList<Daily>) :
    RecyclerView.Adapter<NextFiveDayAdapter.ForecastViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val itemBinding =
            RowNextDaysLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForecastViewHolder(itemBinding, parent.context)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val forecasts: Daily = forecastsList[position]
        holder.bind(forecasts)
    }

    override fun getItemCount(): Int = forecastsList.size

    class ForecastViewHolder(
        private val itemBinding: RowNextDaysLayoutBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(dailyData: Daily) {
            val maxTemp = Utils.convertCelsius(dailyData.temp.max)
            val minTemp = Utils.convertCelsius(dailyData.temp.min)
            itemBinding.dayMaxTempTv.text = maxTemp.toString() + "\u2103"
            itemBinding.dayMinTempTv.text = minTemp.toString() + "\u2103"
            itemBinding.tvDayMonth.text = Utils.getmDate(dailyData.dt.toLong(),"E, MMM dd")
            itemBinding.tvWeatherStatus.text = dailyData.weather[0].main + " " +context.getString(R.string.throughout_the_day)

            val dailyImageIcon = Constants.IMAGE_URL + "${dailyData.weather[0].icon}.png"
            Glide.with(context)
                .load(dailyImageIcon)
                .into(itemBinding.dayIconIv)
        }
    }

    fun setData(forecastsList: ArrayList<Daily>) {
        this.forecastsList = forecastsList
        notifyDataSetChanged()
    }
}