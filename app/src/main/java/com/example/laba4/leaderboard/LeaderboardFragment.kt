package com.example.laba4.leaderboard

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.captaindroid.tvg.Tvg
import com.example.laba4.R
import com.example.laba4.databinding.FragmentLeaderboardBinding
import kotlinx.android.synthetic.main.fragment_leaderboard.*


class LeaderboardFragment : Fragment(){
    private var _binding: FragmentLeaderboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var leaderboardViewModel: LeaderboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_leaderboard,
            container,
            false
        )

        Tvg.change(
            binding.leadersTextView as TextView, intArrayOf(
                Color.parseColor("#F97C3C"),
                Color.parseColor("#FDB54E"),
                Color.parseColor("#64B678"),
                Color.parseColor("#478AEA")
            )
        )

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