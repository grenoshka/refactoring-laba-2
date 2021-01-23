package com.example.laba4.profile

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.laba4.databinding.FragmentProfileBinding
import com.captaindroid.tvg.Tvg

class ProfileFragment : Fragment() {

    private lateinit var binding:FragmentProfileBinding
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)

        Tvg.change(
            binding.profileTextView as TextView, intArrayOf(
                Color.parseColor("#F97C3C"),
                Color.parseColor("#FDB54E"),
                Color.parseColor("#64B678"),
                Color.parseColor("#478AEA")
            )
        )

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