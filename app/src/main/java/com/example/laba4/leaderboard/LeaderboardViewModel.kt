package com.example.laba4.leaderboard

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.laba4.model.Repository
import com.example.laba4.model.database.User
import kotlinx.coroutines.launch

class LeaderboardViewModel(val context: Context):ViewModel() {
    val repository = Repository(context)

    private val _leaders:MutableLiveData<List<Leader>> = MutableLiveData()
    val leaders:LiveData<List<Leader>> = _leaders

    init{
        getLeaders()
    }

    fun getLeaders(){
        viewModelScope.launch {
            val db_leaders = repository.getLeaders()
            _leaders.value = MapdbleadersToRvLeaders(db_leaders)
        }
    }

    private fun MapdbleadersToRvLeaders(db_leaders:List<User>):List<Leader>{
        var newList:MutableList<Leader> = arrayListOf()
        for (user in db_leaders){
            newList.add(Leader(user.name, user.points))
        }
        return newList
    }
}