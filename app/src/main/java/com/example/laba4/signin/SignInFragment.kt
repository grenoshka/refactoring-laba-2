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
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.laba4.R
import com.example.laba4.databinding.FragmentSignInBinding
import kotlinx.android.synthetic.main.fragment_sign_in.view.*


class SignInFragment : Fragment() {

    private lateinit var navController:NavController

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!
    private lateinit var signInViewModel:SignInViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = Navigation.findNavController(requireActivity(), R.id.navHostFragment)
        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sign_in, container,false)
        signInViewModel = SignInViewModel(requireContext())

        setUpBackground()

        return binding.root
    }

    private fun setUpBackground(){
        val imageView: ImageView = binding.root.сatBackgroundImageView
        imageView.setBackgroundResource(R.drawable.cat)
        val mAnimationDrawable = imageView.background as AnimationDrawable
        mAnimationDrawable.start()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.signInViewModel = signInViewModel
        binding.lifecycleOwner = this
        signInViewModel.isUserSignedIn.observe(this, Observer {
            if (it){
                navController.navigate(R.id.menuActivity)
            }
        })
        signInViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
        binding.buttonGoToSignUp.setOnClickListener {
            navController.navigate(R.id.signUpActivity)
        }
    }
}