package com.example.covid19tracker.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.room.Database
import com.example.covid19tracker.data.models.Country
import com.example.covid19tracker.data.models.Global
import com.example.covid19tracker.database.CovidDatabase
import com.example.covid19tracker.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

class SummaryRepository(
    private val apiService: ApiService,
    private val database: CovidDatabase
) {
    val countriesInfoList: LiveData<List<Country>> = Transformations.map(database.getCovidDao().getCountriesInfo()) {localCountriesDataList ->
        localCountriesDataList.map {
            localCountryInfo ->
                localCountryInfo.toDomainCountriesInfo()
        }
    }

    val globalInfo: LiveData<Global> = Transformations.map(database.getCovidDao().getGlobalInfo()){ localGlobalDataList ->
        if (localGlobalDataList.isNotEmpty()) {
            localGlobalDataList[0].toDomainGlobalInfo()
        }
        else {
            Global()
        }
    }

    suspend fun getSummary() {
        try {
            val summary = apiService.getSummary().body()
            withContext(Dispatchers.IO) {
                if (summary != null) {
                    database.getCovidDao().insertGlobalData(summary.global.toLocalWebGlobalInfo())
                    database.getCovidDao().insertCountriesData(summary.countries.map {
                        it.toLocalCountryInfo()
                    })
                }
            }
        } catch (e: HttpException) {
            //For Http request related failures
        } catch (e: IOException) {
            //For JSON conversion
        } catch (e: Exception) {
            //For all other exceptions
        }
    }
}