package com.example.covid19tracker.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.covid19tracker.data.SummaryRepository
import com.example.covid19tracker.data.models.Country
import java.lang.IllegalArgumentException

class CountriesDViewModel (
    private val repository : SummaryRepository,
    private val country : Country) : ViewModel(){


    val countryDetail = country;
}


class CountryDModelFactory(
    private val repository: SummaryRepository,
    private val country : Country?) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountriesDViewModel::class.java)) {
            return CountriesDViewModel(repository,country!!) as T
        }

        throw IllegalArgumentException("Not supported")
    }
}