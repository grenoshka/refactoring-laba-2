package com.example.laba4.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.laba4.R
import com.example.laba4.databinding.ActivityMenuBinding

class MenuFragment : Fragment(){

    private var _binding:ActivityMenuBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater,R.layout.activity_menu, container,false)
        binding.buttonOpenMap.setOnClickListener {
            val navController = Navigation.findNavController(requireActivity(), R.id.navHostFragment)
            navController.navigate(R.id.map)
        }
        return binding.root
    }
}