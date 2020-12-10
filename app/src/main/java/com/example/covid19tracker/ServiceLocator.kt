package com.example.covid19tracker

import android.content.Context
import androidx.room.Room
import com.example.covid19tracker.database.CovidDatabase
import com.example.covid19tracker.network.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ServiceLocator(applicationContext: Context) {

    private val SERVER_URL = "https://api.covid19api.com/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(SERVER_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val apiService = retrofit.create(ApiService::class.java)

    val database = Room.databaseBuilder(
        applicationContext,
        CovidDatabase::class.java,
        "dog_database"
    ).fallbackToDestructiveMigration().build()

}