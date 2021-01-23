package com.example.laba4.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.laba4.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding:FragmentProfileBinding
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        profileViewModel = ProfileViewModel(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.profileViewModel = profileViewModel
        profileViewModel.name.observe(viewLifecycleOwner, Observer {
            binding.profileUserNameItself.text = it
        })
        profileViewModel.point.observe(viewLifecycleOwner, Observer {
            binding.profilePointsItself.text = it.toString()
        })
        profileViewModel.leaderboardPos.observe(viewLifecycleOwner, Observer {
            binding.profileLeaderboardItself.text = it.toString()
        })
    }
}