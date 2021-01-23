package com.example.laba4.profile

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.laba4.model.Repository
import kotlinx.coroutines.launch

class ProfileViewModel(val context: Context): ViewModel() {
    val repository = Repository(context)

    private var _name = MutableLiveData("")
    val name:LiveData<String> = _name

    private var _points = MutableLiveData(-1)
    val point:LiveData<Int> = _points

    private var _leaderboardPos = MutableLiveData(-1)
    val leaderboardPos:LiveData<Int> = _leaderboardPos

    init{
        getData()
    }

    fun getData(){
        viewModelScope.launch {
            val user = repository.getSignedInUser()
            _name.value =user.name
            _points.value=user.points
            _leaderboardPos.value = repository.getUserRank(user.email, user.password)
        }
    }

}