package com.example.covid19tracker.network.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class SummaryListWrapper(
    val results: Summary
)

@JsonClass(generateAdapter = true)
data class Summary (
    val Message: String,
    val Global: Global,
    val Countries: List<Country>,
    val Data: String
)

data class Global(
    val NewConfirmed: Int,
    val TotalConfirmed: Int,
    val NewDeaths: Int,
    val TotalDeaths: Int,
    val NewRecovered: Int,
    val TotalRecovered: Int

)

data class Country(
    val Country: String,
    val CountryCode: String,
    val Slug: String,
    val NewConfirmed: Int,
    val TotalConfirmed: Int,
    val NewDeaths: Int,
    val TotalDeaths: Int,
    val NewRecovered: Int,
    val TotalRecovered: Int,
    val Date: String,
)