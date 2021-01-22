package com.example.laba4.model.network.pojo

import kotlinx.serialization.Serializable

@Serializable
data class UserLogin(
    val email:String,
    val password:String
)