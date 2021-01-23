package com.example.laba4.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.laba4.R
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import android.graphics.drawable.AnimationDrawable
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.laba4.databinding.FragmentMenuBinding
import kotlinx.android.synthetic.main.fragment_menu.*
import kotlinx.android.synthetic.main.fragment_sign_up.view.*
import com.captaindroid.tvg.Tvg

class MenuFragment : Fragment(){

    lateinit var navController: NavController
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_menu, container,false)
        navController = Navigation.findNavController(requireActivity(), R.id.navHostFragment)

        setUpBackGround()

        return binding.root
    }

    private fun setUpBackGround(){
        val imageView: ImageView = binding.catBackgroundImageView
        imageView.setBackgroundResource(R.drawable.cat2)
        val mAnimationDrawable = imageView.background as AnimationDrawable
        mAnimationDrawable.start()
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

    fun openMap(){
        navController.navigate(R.id.leaderboardFragment)
    }
}