package com.example.covid19tracker.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.covid19tracker.data.models.Global
import com.example.covid19tracker.network.models.WebGlobalInfo

@Entity(tableName = "global_info")
data class LocalGlobalInfo (
    @PrimaryKey
    val id: Int = 1,
    val newConfirmed: Int,
    val totalConfirmed: Int,
    val newDeaths: Int,
    val totalDeaths: Int,
    val newRecovered: Int,
    val totalRecovered: Int
) {
    companion object {
//        fun fromWeb(webGlobalInfo: WebGlobalInfo): LocalGlobalInfo {
//            return LocalGlobalInfo(
//                newConfirmed = webGlobalInfo.newConfirmed,
//                totalConfirmed = webGlobalInfo.totalConfirmed,
//                newDeaths = webGlobalInfo.newDeaths,
//                totalDeaths = webGlobalInfo.totalDeaths,
//                newRecovered = webGlobalInfo.newRecovered,
//                totalRecovered = webGlobalInfo.totalRecovered
//            )
//        }
    }

    fun toDomainGlobalInfo(): Global {
        return Global(
            id = id,
            newConfirmed = newConfirmed,
            totalConfirmed = totalConfirmed,
            newDeaths = newDeaths,
            totalDeaths = totalDeaths,
            newRecovered = newRecovered,
            totalRecovered = totalRecovered
        )
    }
}