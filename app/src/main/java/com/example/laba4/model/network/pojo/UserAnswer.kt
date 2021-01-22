package com.example.laba4.model.network.pojo

import kotlinx.serialization.Serializable

@Serializable
data class UserAnswer(
    val access_token:String,
    val refresh_token:String,
    val user:User
)