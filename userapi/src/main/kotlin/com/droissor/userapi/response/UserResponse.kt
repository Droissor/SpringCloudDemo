package com.droissor.userapi.response

import com.droissor.userapi.entity.User
import java.io.Serializable

data class UserResponse(
    val name: String,
    val email: String,
    val userId: String,
) : Serializable {
    companion object {
        fun fromUser(user: User) = UserResponse(
            name = user.name,
            email = user.email,
            userId = user.userId
        )
    }
}


