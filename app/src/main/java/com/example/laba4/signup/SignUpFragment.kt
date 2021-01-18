package com.example.laba4.signup

import android.graphics.drawable.AnimationDrawable
import com.example.laba4.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.laba4.databinding.ActivitySignUpBinding
import kotlinx.android.synthetic.main.activity_sign_in.view.*
import kotlinx.android.synthetic.main.activity_sign_up.view.*

class SignUpFragment : Fragment(), SignUpView {

    private var _binding: ActivitySignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val signUpViewModel = SignUpViewModel(this)
        _binding = DataBindingUtil.inflate(inflater,R.layout.activity_sign_up, container,false)
        binding.signUpViewModel = signUpViewModel

        val imageView: ImageView = binding.root.catBackgroundImageView
        imageView.setBackgroundResource(R.drawable.cat2)
        val mAnimationDrawable = imageView.background as AnimationDrawable
        mAnimationDrawable.start()

        return binding.root
    }
}