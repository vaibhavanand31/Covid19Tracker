package com.example.covid19tracker.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19tracker.R
import com.example.covid19tracker.data.models.Country
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.countries_list_layout.view.*

class CountriesAdapter(private val countryListner: CountryClickListner) : RecyclerView.Adapter<CountriesViewHolder>() {

    var countries: List<Country> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder =
        CountriesViewHolder.from(parent)

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) =
        holder.bind(countries[position], countryListner)

    override fun getItemCount(): Int = countries.size
}

class CountriesViewHolder private constructor(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {
    companion object {
        fun from(parent: ViewGroup): CountriesViewHolder {
            val layout = LayoutInflater.from(parent.context)
                .inflate(R.layout.countries_list_layout, parent, false)
            return CountriesViewHolder(layout)
        }
    }

    fun bind(country: Country, countryListner: CountryClickListner) {
        itemView.countryName.text = country.country
        itemView.countryTotalConfirmedCases.text = country.totalConfirmed.toString()
        itemView.countryTodayConfirmedCases.text = country.newConfirmed.toString()

        containerView.setOnClickListener{
            countryListner.onCountryClick(country)
        }
    }
}

interface CountryClickListner{
    fun onCountryClick(country: Country)
}