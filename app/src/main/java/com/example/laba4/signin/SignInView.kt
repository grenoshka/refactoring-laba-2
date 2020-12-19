package com.example.laba4.signin

interface SignInView
{
    fun onSignInSuccess(email:String, password:String)
    fun onSignInError(message:String)
    fun goToSignUp()
}