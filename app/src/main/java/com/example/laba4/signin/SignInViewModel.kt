package com.example.laba4.signin

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.laba4.model.Repository
import androidx.lifecycle.viewModelScope
import com.example.laba4.R
import com.example.laba4.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class  SignInViewModel(val context: Context):ViewModel() {

    val repository = Repository(context)

    var email = MutableLiveData("")
    var password = MutableLiveData("")

    private val _loading = MutableLiveData(true)
    val loading: LiveData<Boolean> = _loading

    private val _errorMessage = MutableLiveData("")
    val errorMessage:LiveData<String> = _errorMessage

    val isUserSignedIn = SingleLiveEvent<Boolean>()

    init{
        CheckIfUserIsSignedIn()
    }

    fun signIn(){
        //TODO: ADD CHECKING IN WITH SERVER
//        viewModelScope.launch {
//            if (isInputDataValid){
//                _loading.value=true
//                //check in the network
//                //if success
//                isUserSignedIn.value=true
//                //else show error
//                _loading.value=false
//            }
//        }
        if (inputDataIsValid()) {
            //check user in network
            isUserSignedIn.value = true
        }
    }

    private fun CheckIfUserIsSignedIn() {
        viewModelScope.launch(Dispatchers.Main){
            _loading.value = true
            isUserSignedIn.value = repository.isUserSignedInInDB()
            _loading.value = false
        }
    }

    private fun inputDataIsValid():Boolean {
        if (!email.value.isEmailValid()){
            _errorMessage.value = context.resources.getString(R.string.error_incorrect_email)
            return false
        }
        if (password.value.isNullOrEmpty() || password.value!!.length<=2){
            _errorMessage.value = context.resources.getString(R.string.error_incorrect_password)
            return false
        }
        return true
    }
    private fun String?.isEmailValid(): Boolean {
        return !isNullOrEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }
}