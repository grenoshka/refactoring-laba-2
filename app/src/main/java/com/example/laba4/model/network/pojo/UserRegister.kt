package com.example.laba4.model.network.pojo

import kotlinx.serialization.Serializable

@Serializable
data class UserRegister(
    val email: String,
    val username: String,
    val password: String
)