package com.mansi.epayassignment.utlis

import android.text.format.DateFormat
import java.util.*

object Utils {

    fun convertCelsius(kelvin : Double): Int = (kelvin.minus(273.15)).toInt()

    fun getmDate(time1: Long, format : String): String? {
        val time = Date(time1 * 1000)
        val date: String = DateFormat.format(format, time).toString()
        return date + ""
    }
}