package com.example.laba4.model.network.pojo

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val pk:Int,
    val username:String,
    val email:String,
    val first_name:String,
    val last_name:String
)