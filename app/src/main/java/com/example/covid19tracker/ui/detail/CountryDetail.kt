package com.example.covid19tracker.ui.detail
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.covid19tracker.CovidApplication
import com.example.covid19tracker.R
import com.example.covid19tracker.data.SummaryRepository
import com.example.covid19tracker.data.models.Country
import kotlinx.android.synthetic.main.activity_country_detail.*

class CountryDetail : AppCompatActivity() {

    private val viewModel by viewModels<CountriesDViewModel> {


        val apiService = (applicationContext as CovidApplication).serviceLocator.apiService
        val database = (applicationContext as CovidApplication).serviceLocator.database
        val repository = SummaryRepository(apiService,database)
        val country : Country? = intent.getSerializableExtra("countryInfo") as Country?
        CountryDModelFactory(repository, country)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail)


        setData(viewModel.countryDetail)
    }


    fun setData(country : Country){

        txtCountryName.setText(country.country)

        totalCase.setText(String.format(country.totalConfirmed.toString()))
        txtNewCase.setText(String.format(country.newConfirmed.toString()))

        txtNewDeath.setText(country.newDeaths.toString())
        txtTotalDeath.setText(country.totalDeaths.toString())

        txtTotalRecovered.setText(country.totalRecovered.toString())
        //txt_totalcase.setText(country.totalConfirmed)

    }
}