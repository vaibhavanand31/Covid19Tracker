package com.example.covid19tracker.network.models

import com.example.covid19tracker.database.LocalCountryInfo
import com.example.covid19tracker.database.LocalGlobalInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
class SummaryListWrapper(
    @Json(name = "Global")
    val global: WebGlobalInfo,
    @Json(name = "Countries")
    val countries: List<WebCountryInfo>,
)

data class WebGlobalInfo(
    @Json(name = "NewConfirmed")
    val newConfirmed: Int,
    @Json(name = "TotalConfirmed")
    val totalConfirmed: Int,
    @Json(name = "NewDeaths")
    val newDeaths: Int,
    @Json(name = "TotalDeaths")
    val totalDeaths: Int,
    @Json(name = "NewRecovered")
    val newRecovered: Int,
    @Json(name = "TotalRecovered")
    val totalRecovered: Int
): Comparable<WebGlobalInfo>, Serializable {

    override fun compareTo(other: WebGlobalInfo): Int {
        val compareGlobal = newConfirmed.compareTo(other.newConfirmed)
        return if (compareGlobal != 0) compareGlobal else newConfirmed.compareTo((other.newConfirmed))
    }

    fun toLocalWebGlobalInfo(): LocalGlobalInfo {
        return LocalGlobalInfo(
            newConfirmed = newConfirmed,
            totalConfirmed = totalConfirmed,
            newDeaths =  newDeaths,
            totalDeaths = totalDeaths,
            newRecovered = newRecovered,
            totalRecovered = totalRecovered
        )
    }
}

data class WebCountryInfo(
    @Json(name = "Country")
    val country: String,
    @Json(name = "CountryCode")
    val countryCode: String,
    @Json(name = "Slug")
    val slug: String,
    @Json(name = "NewConfirmed")
    val newConfirmed: Int,
    @Json(name = "TotalConfirmed")
    val totalConfirmed: Int,
    @Json(name = "NewDeaths")
    val newDeaths: Int,
    @Json(name = "TotalDeaths")
    val totalDeaths: Int,
    @Json(name = "NewRecovered")
    val newRecovered: Int,
    @Json(name = "TotalRecovered")
    val totalRecovered: Int,
    @Json(name = "Date")
    val date: String,
): Comparable<WebCountryInfo>, Serializable {
    override fun compareTo(other: WebCountryInfo): Int {
        val compareGlobal = newConfirmed.compareTo(other.newConfirmed)
        return if (compareGlobal != 0) compareGlobal else newConfirmed.compareTo((other.newConfirmed))
    }

    fun toLocalCountryInfo(): LocalCountryInfo {
        return LocalCountryInfo(
            country = country,
            countryCode = countryCode,
            slug = slug,
            newConfirmed = newConfirmed,
            totalConfirmed = totalConfirmed,
            newDeaths = newDeaths,
            totalDeaths = totalDeaths,
            newRecovered = newRecovered,
            totalRecovered = totalRecovered,
            date = date
        )
    }
}