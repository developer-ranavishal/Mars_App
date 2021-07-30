package com.example.mars_app.detail

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mars_app.R
import com.example.mars_app.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding=FragmentDetailsBinding.inflate(layoutInflater)
        binding.lifecycleOwner=this
        val marsProperty=DetailsFragmentArgs.fromBundle(requireArguments()).selectedProperty
        val application= requireNotNull(activity).application
        val viewModelFactory= DetailViewModelFactory(marsProperty,application )
        binding.detailViewModel=ViewModelProvider(this,viewModelFactory).get(DetailViewModel::class.java)
        return binding.root
    }

}