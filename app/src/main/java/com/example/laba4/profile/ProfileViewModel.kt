package com.example.laba4.profile

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.laba4.model.Repository

class ProfileViewModel(val context: Context): ViewModel() {
    val repository = Repository(context)

    var name = MutableLiveData("")
    var points = MutableLiveData(-1)
    var leaderboardPos = MutableLiveData(-1)

    init{
        getData()
    }

    fun getData(){
        //TODO:GET DATA FROM DB AND PUT IT IN VARIABLES
    }

}