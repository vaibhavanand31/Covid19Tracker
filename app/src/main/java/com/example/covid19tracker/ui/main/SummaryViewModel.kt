package com.example.covid19tracker.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.covid19tracker.data.SummaryRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class SummaryViewModel (private val repository: SummaryRepository): ViewModel() {
    val summary = repository.resultSummary

    init {
        viewModelScope.launch {
            repository.getSummary()
        }
    }
}

class SummaryViewModelFactory(private val repository: SummaryRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SummaryViewModel::class.java)) {
            return SummaryViewModel(repository) as T
        }

        throw IllegalArgumentException("Invalid model class")
    }
}