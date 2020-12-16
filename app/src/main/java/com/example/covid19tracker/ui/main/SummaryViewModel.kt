package com.example.covid19tracker.ui.main

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.example.covid19tracker.data.SummaryRepository
import com.example.covid19tracker.data.models.Country
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class SummaryViewModel (
    private val summaryRepository: SummaryRepository,
    private val state: SavedStateHandle
): ViewModel() {

//    val countriesListInfo = summaryRepository.countriesInfoList
    val globalInfo = summaryRepository.globalInfo

    private val _navigateToCountryDetail = MutableLiveData<Country>(null)
    val navigateToCountryDetail: LiveData<Country?> get() = _navigateToCountryDetail

    fun onCountryClick(country: Country) {
        _navigateToCountryDetail.value = country
    }

    fun onNavigateToCountryDetailsComplete() {
        _navigateToCountryDetail.value = null
    }

    private val searchKey = MutableLiveData<String?>(null)
    fun setSearchKey(query: String?){
        searchKey.value = query
    }

    val countriesListInfo = searchKey.switchMap { query ->
        summaryRepository.countriesInfoList.map { list ->
            list.filter {
                it.country != null && (
                query.isNullOrEmpty() || it.country.contains(query, ignoreCase = true))
            }
        }
    }

    init {
        viewModelScope.launch {
            summaryRepository.getSummary()
        }
    }
}

class SummaryViewModelFactory(
    private val summaryRepository: SummaryRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle?
): AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(SummaryViewModel::class.java)) {
            return SummaryViewModel(
                summaryRepository,
                handle
            ) as T
        }

        throw IllegalArgumentException("Invalid model class")
    }
}