package com.example.laba4.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.laba4.R
import com.example.laba4.databinding.ActivitySignInBinding

class MapFragment : Fragment(){

    private var _binding: ActivitySignInBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val signInViewModel = SignInViewModel(this)
        //_binding = DataBindingUtil.setContentView(requireActivity(), R.layout.activity_sign_in)
        //binding.signInViewModel = signInViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(
            R.layout.activity_map,
            container, false
        )

        return view
    }
}