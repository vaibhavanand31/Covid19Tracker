package com.example.covid19tracker.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.covid19tracker.R

class WorldFragment : Fragment() {

    //private val viewModel by activityViewModels<SummaryViewModel>{}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.world_fragment, container, false)
        return  rootView
    }

}