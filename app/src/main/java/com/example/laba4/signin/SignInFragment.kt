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
import com.example.laba4.R
import com.example.laba4.databinding.ActivitySignInBinding
import kotlinx.android.synthetic.main.activity_sign_in.view.*


class SignInFragment : Fragment(), SignInView {

    private var _binding:ActivitySignInBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val signInViewModel = SignInViewModel(this)
        _binding = DataBindingUtil.setContentView(requireActivity(), R.layout.activity_sign_in)
        binding.signInViewModel = signInViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(
            R.layout.activity_sign_in,
            container, false
        )

        val imageView: ImageView = view.сatAnimation
        imageView.setBackgroundResource(R.drawable.cat)
        val mAnimationDrawable = imageView.background as AnimationDrawable
        mAnimationDrawable.start()

        return view
    }

    override fun onSignInSuccess(email: String, password: String) {
        //go to player's info
    }

    override fun goToSignUp() {
        //val intent = Intent(this, SignUpActivity::class.java)
        //startActivity(intent)
    }

    override fun onSignInError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}