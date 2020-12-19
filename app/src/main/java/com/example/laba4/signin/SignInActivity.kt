package com.example.laba4.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.laba4.R
import com.example.laba4.databinding.ActivitySignInBinding
import com.example.laba4.singup.SignUpActivity

class SignInActivity : AppCompatActivity(), SignInView {
    private var _binding:ActivitySignInBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val signInViewModel = SignInViewModel(this)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        binding.signInViewModel = signInViewModel
    }

    override fun onSignInSuccess(email: String, password: String) {
        //go to player's info
    }

    override fun goToSignUp() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    override fun onSignInError(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}