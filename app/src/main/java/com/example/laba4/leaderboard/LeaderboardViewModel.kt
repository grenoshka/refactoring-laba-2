package com.example.laba4.leaderboard

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.laba4.model.Repository

class LeaderboardViewModel(val context: Context) {
    val repository = Repository(context)

    private val _leaders:MutableLiveData<List<Leader>> = MutableLiveData()
    val leaders:LiveData<List<Leader>> = _leaders

    init{
        getLeaders()
    }

    fun getLeaders(){
        //TODO:GET fkn leaders
        //TODO:GIVE EM TO THE VIEW TO HANDLE HEHEHE
    }
}