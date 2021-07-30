 package com.example.mars_app.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mars_app.PhotoGridAdapter
import com.example.mars_app.databinding.FragmentOverViewBinding
import com.example.mars_app.databinding.FragmentOverViewBinding.inflate
 class OverViewFragment : Fragment() {

    private lateinit var viewModel: OverviewViewModel
 private lateinit var binding: FragmentOverViewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         viewModel=ViewModelProvider(this).get(OverviewViewModel::class.java)
        binding= inflate(layoutInflater)
        //allow dataBinding to observe Livedata updates within the lifecycle of this fragment
        binding.lifecycleOwner = viewLifecycleOwner
        // giving the viewModel info to binding class
        binding.viewModel=viewModel
        binding.recyclerView.adapter= PhotoGridAdapter(PhotoGridAdapter.OnClickListener{marsProperty ->
            viewModel.displayPropertyDetails(marsProperty)
        })
        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, {
            if (null!=it){
                findNavController().navigate(OverViewFragmentDirections.actionOverViewFragmentToDetailsFragment(it))
                viewModel.displayPropertyDetailsCompleted(it)
            }
        })
        return binding.root
    }

}