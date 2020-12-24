package com.example.laba4.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.laba4.R
import com.example.laba4.databinding.ActivitySignInBinding
import com.example.laba4.singup.SignUpActivity

class SignInActivity : Fragment(), SignInView {

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

        val view = inflater.inflate(R.layout.activity_sign_in,
            container, false)

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
        Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
    }
}