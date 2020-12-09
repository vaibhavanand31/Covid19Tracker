package com.example.covid19tracker.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.covid19tracker.data.models.Country
import com.example.covid19tracker.data.models.Global
import com.example.covid19tracker.network.models.WebCountryInfo
import com.example.covid19tracker.network.models.WebGlobalInfo

@Entity(tableName = "countries_info")
data class LocalCountryInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val country: String,
    val countryCode: String,
    val slug: String,
    val newConfirmed: Int,
    val totalConfirmed: Int,
    val newDeaths: Int,
    val totalDeaths: Int,
    val newRecovered: Int,
    val totalRecovered: Int,
    val date: String
) {
    companion object {
        fun fromWeb(webCountriesInfo: WebCountryInfo): LocalCountryInfo {
            return LocalCountryInfo(
                country = webCountriesInfo.country,
                countryCode = webCountriesInfo.countryCode,
                slug = webCountriesInfo.slug,
                newConfirmed = webCountriesInfo.newConfirmed,
                totalConfirmed = webCountriesInfo.totalConfirmed,
                newDeaths = webCountriesInfo.newDeaths,
                totalDeaths = webCountriesInfo.totalDeaths,
                newRecovered = webCountriesInfo.newRecovered,
                totalRecovered = webCountriesInfo.totalRecovered,
                date = webCountriesInfo.date
            )
        }
    }

    fun toDomainCountriesInfo(): Country {
        return Country(
            id = id,
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