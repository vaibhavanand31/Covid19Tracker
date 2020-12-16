package com.example.covid19tracker.data.models

import com.example.covid19tracker.database.LocalCountryInfo
import java.io.Serializable

data class Country(
    val country: String,
    val countryCode: String,
    val slug: String,
    val newConfirmed: Int,
    val totalConfirmed: Int,
    val newDeaths: Int,
    val totalDeaths: Int,
    val newRecovered: Int,
    val totalRecovered: Int,
    val date: String,
): Serializable {

    fun toLocalCountriesInfo(): LocalCountryInfo = LocalCountryInfo(
       country, countryCode, slug, newConfirmed, totalConfirmed, newDeaths, totalDeaths, newRecovered, totalRecovered, date
    )
}