package com.example.covid19tracker.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.covid19tracker.CovidApplication
import com.example.covid19tracker.R
import com.example.covid19tracker.data.SummaryRepository

class CountriesFragment(): Fragment() {

    private val summaryViewModel by viewModels<SummaryViewModel> {
        val apiService = (requireActivity().applicationContext as CovidApplication).serviceLocator.apiService
        val database = (requireActivity().applicationContext as CovidApplication).serviceLocator.database
        val dogRepository = SummaryRepository(apiService, database)
        SummaryViewModelFactory(dogRepository, this, arguments)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.countries_fragment, container, false)


        return  rootView
    }
}