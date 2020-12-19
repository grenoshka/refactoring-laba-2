package com.example.laba4.signin

import android.text.Editable
import android.text.TextUtils
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.databinding.BaseObservable


class SignInViewModel(val signInView:SignInView) {
    val signInRequest = SignInRequest("","")

    fun signIn(){
        signInView.onSignInSuccess(signInRequest.getEmail(), signInRequest.getPassword())
    }

    fun signUp(){
        signInView.goToSignUp()
    }
//
//    fun onEmailChange(newEmail:String){
//        signInRequest.email=newEmail
//    }
//
//    fun onPasswordChange(newPassword:String){
//        signInRequest.password=newPassword
//    }
//
//    fun onEditorAction(textView: TextView, actionId: Int,keyEvent: KeyEvent?): Boolean {
//        if (actionId == EditorInfo.IME_ACTION_SEND && isInputDataValid()) {
//            signIn()
//        }
//        return false
//    }

    class SignInRequest(private var email: String, private var password: String): BaseObservable() {
        fun getEmail():String{
            return email
        }
        fun setEmail(_email:String){
            email=_email
        }

        fun getPassword():String{
            return password
        }
        fun setPassword(_password:String){
            password=_password
        }

        fun isInputDataValid():Boolean {
            return email.isEmailValid() && password.length > 2
        }
        private fun String.isEmailValid(): Boolean {
            return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
        }
    }
}