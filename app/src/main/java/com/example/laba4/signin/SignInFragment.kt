package com.example.laba4.signin

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.laba4.R
import com.example.laba4.databinding.ActivitySignInBinding
import kotlinx.android.synthetic.main.activity_sign_in.view.*
import kotlin.contracts.contract


class SignInFragment : Fragment(), SignInView {

    private var _binding:ActivitySignInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val signInViewModel = SignInViewModel(this)
        _binding = DataBindingUtil.inflate(inflater,R.layout.activity_sign_in, container,false)
        binding.signInViewModel = signInViewModel

        val imageView: ImageView = binding.root.сatBackgroundImageView
        imageView.setBackgroundResource(R.drawable.cat)
        val mAnimationDrawable = imageView.background as AnimationDrawable
        mAnimationDrawable.start()

        return binding.root
    }

    override fun onSignInSuccess(email: String, password: String) {
        val navController = Navigation.findNavController(requireActivity(), R.id.navHostFragment)
        navController.navigate(R.id.menuActivity)
    }

    override fun goToSignUp() {
        val navController = Navigation.findNavController(requireActivity(), R.id.navHostFragment)
        navController.navigate(R.id.signUpActivity)
    }

    override fun onSignInError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}