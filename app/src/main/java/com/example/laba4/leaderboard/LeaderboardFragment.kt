package com.example.laba4.leaderboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.laba4.R
import com.example.laba4.databinding.FragmentLeaderboardBinding

class LeaderboardFragment : Fragment(){
    private var _binding: FragmentLeaderboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var leaderboardViewModel: LeaderboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_leaderboard, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        leaderboardViewModel = LeaderboardViewModel(requireContext())
        leaderboardViewModel.leaders.observe(viewLifecycleOwner, Observer {
            binding.leaderboardRecyclerView.adapter = LeaderboardAdapter(it)
            binding.leaderboardRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.leaderboardRecyclerView.setHasFixedSize(true)
        })
    }
}