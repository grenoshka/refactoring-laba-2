package com.example.laba4.signup

import android.graphics.drawable.AnimationDrawable
import com.example.laba4.R
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
import com.example.laba4.databinding.FragmentSignUpBinding
import kotlinx.android.synthetic.main.fragment_sign_up.view.*

class SignUpFragment : Fragment(){

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private lateinit var signUpViewModel: SignUpViewModel
    private lateinit var navController:NavController
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = Navigation.findNavController(requireActivity(), R.id.navHostFragment)
        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sign_up, container,false)
        signUpViewModel = SignUpViewModel(requireContext())

        setUpBackGround()

        return binding.root
    }

    private fun setUpBackGround(){
        val imageView: ImageView = binding.root.catBackgroundImageView
        imageView.setBackgroundResource(R.drawable.cat4)
        val mAnimationDrawable = imageView.background as AnimationDrawable
        mAnimationDrawable.start()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.signUpViewModel = signUpViewModel
        binding.lifecycleOwner = this

        signUpViewModel.userSignedUp.observe(viewLifecycleOwner, Observer {
            if (it){
                navController.navigate(R.id.menuActivity)
            }
        })

        signUpViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })

        binding.buttonGoToSignIn.setOnClickListener {
            navController.navigate(R.id.signInActivity)
        }
    }

}