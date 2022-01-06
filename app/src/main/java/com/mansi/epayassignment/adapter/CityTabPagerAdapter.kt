package com.mansi.epayassignment.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mansi.epayassignment.fragment.CityForecastFragment
import com.mansi.epayassignment.utlis.CityName
import com.mansi.epayassignment.utlis.Constants

class CityTabPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        var title = ""
        val fragment = CityForecastFragment()
        val bundle = Bundle()
        when(position){
            0 -> title = CityName.RIO.title
            1 -> title = CityName.BEIJING.title
            2 -> title = CityName.LA.title
        }
        bundle.putString(Constants.TITLE, title)
        fragment.arguments = bundle
        return fragment


    }
}
