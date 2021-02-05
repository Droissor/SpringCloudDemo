package com.droissor.userapi.response

import java.io.Serializable

data class UserResponse(
    val name: String,
    val email: String,
    val userId: String,
) : Serializable

