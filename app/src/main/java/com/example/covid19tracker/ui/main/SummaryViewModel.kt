package com.example.covid19tracker.ui.main

import android.os.Bundle
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.example.covid19tracker.data.SummaryRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class SummaryViewModel (
    private val summaryRepository: SummaryRepository,
    private val state: SavedStateHandle
): ViewModel() {
    val countriesListInfo = summaryRepository.countriesInfoList
    val globalInfo = summaryRepository.globalInfo

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
            return SummaryViewModel(summaryRepository, handle) as T
        }

        throw IllegalArgumentException("Invalid model class")
    }
}