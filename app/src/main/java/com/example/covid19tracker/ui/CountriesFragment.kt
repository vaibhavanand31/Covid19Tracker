package com.example.covid19tracker.ui

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.example.covid19tracker.CovidApplication
import com.example.covid19tracker.R
import com.example.covid19tracker.data.SummaryRepository
import com.example.covid19tracker.data.models.Country
import com.example.covid19tracker.databinding.CountriesFragmentBinding
import com.example.covid19tracker.ui.detail.CountryDetail
import com.example.covid19tracker.ui.main.SummaryViewModel
import com.example.covid19tracker.ui.main.SummaryViewModelFactory
import java.io.Serializable

class CountriesFragment(): Fragment() {

    lateinit var binding: CountriesFragmentBinding

    private val viewModel by viewModels<SummaryViewModel> {
        val apiService = (requireActivity().applicationContext as CovidApplication).serviceLocator.apiService
        val database = (requireActivity().applicationContext as CovidApplication).serviceLocator.database
        val dogRepository = SummaryRepository(apiService, database)
        SummaryViewModelFactory(
            dogRepository,
            this,
            arguments
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CountriesFragmentBinding.inflate(inflater, container, false)
        val adapter =
            CountriesAdapter(object :
                CountryClickListner {
                override fun onCountryClick(country: Country) {
                    viewModel.onCountryClick(country)
                }
            })

        binding.countriesRecyclerView.adapter = adapter

        binding.searchEditText.addTextChangedListener {
            viewModel.setSearchKey(it?.toString())
        }

        viewModel.countriesListInfo.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        viewModel.navigateToCountryDetail.observe(viewLifecycleOwner){
            it?.let {
                val intent = Intent(activity, CountryDetail::class.java).apply {
                    putExtra("countryInfo", it as Serializable)
                }
                startActivity(intent)
                viewModel.onNavigateToCountryDetailsComplete()
            }
        }
        return binding.root
    }
}