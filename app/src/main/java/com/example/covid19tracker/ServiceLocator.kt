package com.example.covid19tracker

import android.content.Context
import androidx.room.Room
import com.example.covid19tracker.network.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ServiceLocator(applicationContext: Context) {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.covid19api.com/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val apiService = retrofit.create(ApiService::class.java)

}