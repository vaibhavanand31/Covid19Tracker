package com.example.covid19tracker.data.models

import com.example.covid19tracker.database.LocalGlobalInfo
import java.io.Serializable

data class Global(
    val id: Int = 0,
    val newConfirmed: Int = 0,
    val totalConfirmed: Int = 0,
    val newDeaths: Int = 0,
    val totalDeaths: Int = 0,
    val newRecovered: Int = 0,
    val totalRecovered: Int = 0
): Serializable {

    fun toLocalGlobalInfo(): LocalGlobalInfo = LocalGlobalInfo(
        id, newConfirmed, totalConfirmed, newDeaths, totalDeaths, newRecovered, totalRecovered
    )
}