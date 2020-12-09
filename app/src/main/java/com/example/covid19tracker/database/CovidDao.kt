package com.example.covid19tracker.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.covid19tracker.data.models.Global

@Dao
interface CovidDao {

    @Query("SELECT * FROM countries_info")
    fun getCountriesInfo(): LiveData<List<LocalCountryInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCountriesData(countriesInfo: List<LocalCountryInfo>)

    @Query("SELECT * FROM global_info")
    fun getGlobalInfo(): LiveData<List<LocalGlobalInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGlobalData(globalInfo: LocalGlobalInfo)
}