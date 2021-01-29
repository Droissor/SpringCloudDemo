package com.droissor.userapi.vo

import com.droissor.userapi.entity.User
import java.io.Serializable
import java.util.UUID

data class UserDto (
    val name: String,
    val email: String,
    val password: String,
    val encryptedPassword: String? = null,
    val userId: String = UUID.randomUUID().toString()
) : Serializable

fun UserDto.toUser(passwordEncryptFunction: (String) -> String) = User(
    name = this.name,
    email = this.email,
    encryptedPassword = passwordEncryptFunction(this.password),
    userId = this.userId
)