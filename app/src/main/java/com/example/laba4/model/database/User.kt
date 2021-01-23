package com.example.laba4.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "myUsers")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var token: String,
    var name: String,
    var email: String,
    var password: String,
    var placeInRanking: Int,
    var points: Int,
    var isLoggedIn:Boolean
)