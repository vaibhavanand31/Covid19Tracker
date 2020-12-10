package com.example.covid19tracker.ui.main

import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.covid19tracker.CovidApplication
import com.example.covid19tracker.R
import com.example.covid19tracker.data.SummaryRepository
import com.example.covid19tracker.data.models.Global
import com.example.covid19tracker.database.LocalGlobalInfo
import com.example.covid19tracker.databinding.WorldFragmentBinding
import kotlinx.android.synthetic.main.world_fragment.*

class WorldFragment : Fragment() {

    private val viewModel by viewModels<SummaryViewModel> {
        val apiService = (requireActivity().applicationContext as CovidApplication).serviceLocator.apiService
        val database = (requireActivity().applicationContext as CovidApplication).serviceLocator.database
        val dogRepository = SummaryRepository(apiService, database)
        SummaryViewModelFactory(dogRepository, this, arguments)
    }

    lateinit var binding: WorldFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WorldFragmentBinding.inflate(inflater, container, false)
        viewModel.globalInfo.observe(viewLifecycleOwner){ globalInfo ->
            printGlobalSummary(globalInfo)
        }
        return binding.root
    }

    private fun printGlobalSummary(global: Global){

        Log.d("qq",global.totalConfirmed.toString())
        Log.d("qq",global.totalDeaths.toString())
        Log.d("qq",global.totalRecovered.toString())
        Log.d("qq",global.newConfirmed.toString())
        Log.d("qq",global.newDeaths.toString())
        Log.d("qq",global.newRecovered.toString())
       


        confirmedTotalText.setText(Integer.toString(global.totalConfirmed))
        deceasedTotalText.setText(Integer.toString(global.totalDeaths))
        recoveredTotalText.setText(Integer.toString(global.totalRecovered))
    }
}