package com.mansi.epayassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mansi.epayassignment.adapter.CityTabPagerAdapter
import com.mansi.epayassignment.databinding.ActivityMainBinding
import com.mansi.epayassignment.utlis.CityName
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var cityTabPagerAdapter : CityTabPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setPagerAdapter()
    }


    private fun setPagerAdapter() {
        cityTabPagerAdapter = CityTabPagerAdapter(this)
        binding.tabsViewpager.setAdapter(cityTabPagerAdapter)
        val tabTtiles = arrayOf(CityName.RIO.title, CityName.BEIJING.title, CityName.LA.title)
        TabLayoutMediator(
            binding.tabLayout, binding.tabsViewpager
        ) { myTabLayout: TabLayout.Tab, position: Int ->
            myTabLayout.text = tabTtiles[position]
        }.attach()
    }

}