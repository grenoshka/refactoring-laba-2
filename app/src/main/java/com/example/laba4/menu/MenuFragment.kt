package com.example.laba4.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.laba4.R
import com.example.laba4.databinding.FragmentMenuBinding

class MenuFragment : Fragment(){

    lateinit var navController:NavController
    private var _binding:FragmentMenuBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_menu, container,false)
        navController = Navigation.findNavController(requireActivity(), R.id.navHostFragment)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonLeaderboard.setOnClickListener {
            navController.navigate(R.id.leaderboardFragment)
        }
        binding.buttonPlay.setOnClickListener{
            navController.navigate(R.id.gameActivity)
        }
        binding.buttonProfile.setOnClickListener {
            navController.navigate(R.id.profileFragment)
        }
    }
}