package com.example.covid19tracker.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocalCountryInfo::class, LocalGlobalInfo::class], version = 1)
abstract class CovidDatabase: RoomDatabase() {
    abstract fun getCovidDao(): CovidDao
}