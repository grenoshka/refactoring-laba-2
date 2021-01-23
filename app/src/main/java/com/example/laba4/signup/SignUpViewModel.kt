package com.example.laba4.signup

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.laba4.R
import com.example.laba4.SingleLiveEvent
import com.example.laba4.model.Repository
import kotlinx.coroutines.launch

class SignUpViewModel (val context: Context):ViewModel() {
    val repository = Repository(context)

    private val _loading = MutableLiveData(true)
    val loading: LiveData<Boolean> = _loading

    private val _errorMessage = MutableLiveData("")
    val errorMessage:LiveData<String> = _errorMessage

    private val _userSignedUp = SingleLiveEvent<Boolean>()
    val userSignedUp:LiveData<Boolean> = _userSignedUp

    val name = MutableLiveData("")
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val repeatPassword = MutableLiveData("")

    fun signUp(){
        viewModelScope.launch {
            _loading.value =true
            if (inputDataIsValid()){
                //repository.signUp(email,password,name,)
                _userSignedUp.value = true
            }
            _loading.value=false
        }
    }

    private fun inputDataIsValid():Boolean {
        if (name.value.isNullOrEmpty()){
            _errorMessage.value = context.resources.getString(R.string.error_incorrect_name)
            return false
        }

        if (!email.value.isEmailValid()){
            _errorMessage.value = context.resources.getString(R.string.error_incorrect_email)
            return false
        }
        if (password.value.isNullOrEmpty() || password.value!!.length<=2){
            _errorMessage.value = context.resources.getString(R.string.error_incorrect_password)
            return false
        }
        if (repeatPassword.value.isNullOrEmpty()){
            _errorMessage.value = context.resources.getString(R.string.error_password_not_repeated)
            return false
        }
        if (repeatPassword.value!=password.value){
            _errorMessage.value = context.resources.getString(R.string.error_passwords_dont_match)
            return false
        }
        return true
    }

    fun String?.isEmailValid(): Boolean {
        return !isNullOrEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

}