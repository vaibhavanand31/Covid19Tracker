package com.example.covid19tracker.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.covid19tracker.network.ApiService
import com.example.covid19tracker.network.models.Summary
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

class SummaryRepository(private val apiService: ApiService) {
    private val _resultSummary = MutableLiveData<Summary>()
    val resultSummary: LiveData<Summary> get() = _resultSummary

    suspend fun getSummary() {
        try {
            _resultSummary.value = apiService.getSummary().results
        } catch (e: HttpException) {
            //For Http request related failures
        } catch (e: IOException) {
            //For JSON conversion
        } catch (e: Exception) {
            //For all other exceptions
        }
    }
    // save summary
}