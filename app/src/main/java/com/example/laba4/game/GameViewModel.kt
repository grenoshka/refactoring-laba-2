package com.example.laba4.game

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.laba4.model.Repository
import kotlinx.coroutines.launch

class GameViewModel(context: Context):ViewModel() {
    val repository = Repository(context)

    fun updatePoints(newPoints:Int){
        viewModelScope.launch {
            val user = repository.getSignedInUser()
            if (newPoints>user.points)
                repository.updateUserPoints(user.id, newPoints)
        }
    }
}