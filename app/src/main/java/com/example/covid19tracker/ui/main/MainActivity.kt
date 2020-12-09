package com.example.covid19tracker.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.covid19tracker.R
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewPager.adapter = MainViewPagerAdapter(this)
        TabLayoutMediator(mainTabLayout, mainViewPager) { tab, position ->
            tab.setText(MainTab.values()[position].titleRes)
        }.attach()
    }
}