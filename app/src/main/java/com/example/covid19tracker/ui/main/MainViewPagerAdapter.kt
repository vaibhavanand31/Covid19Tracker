package com.example.covid19tracker.ui.main

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.covid19tracker.R
import com.example.covid19tracker.ui.CountriesFragment
import com.example.covid19tracker.ui.WorldFragment

class MainViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int  = MainTab.values().size

    override fun createFragment(position: Int): Fragment {
        // Find a proper way to do this
        if(MainTab.values()[position].toString() == "Countries") {
            return CountriesFragment()
        }
       return WorldFragment()
    }
}

enum class MainTab(@StringRes val titleRes: Int) {
    World(R.string.main_tab_world),
    Countries(R.string.main_tab_countries)
}