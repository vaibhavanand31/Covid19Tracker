package com.example.covid19tracker.network

import com.example.covid19tracker.network.models.SummaryListWrapper
import retrofit2.http.GET

interface ApiService {

    @GET("summary/")
    suspend fun getSummary(): SummaryListWrapper

}