package com.example.laba4.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.laba4.R
import com.example.laba4.databinding.ActivityMapBinding

class MapFragment : Fragment(){
    private var _binding: ActivityMapBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater,R.layout.activity_map, container,false)
        binding.openGame.setOnClickListener {
            val navController = Navigation.findNavController(requireActivity(), R.id.navHostFragment)
            navController.navigate(R.id.gameActivity)
        }
        return binding.root
    }
}