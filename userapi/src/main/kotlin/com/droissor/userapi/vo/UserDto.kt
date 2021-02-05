package com.droissor.userapi.vo

import com.droissor.userapi.entity.UserEntity
import com.droissor.userapi.response.UserResponse
import java.io.Serializable
import java.util.UUID

data class UserDto (
    val name: String,
    val email: String,
    val password: String,
    val encryptedPassword: String? = null,
    val userId: String = UUID.randomUUID().toString()
) : Serializable {
    companion object {
        fun fromUser(userEntity: UserEntity) = UserDto(
            name = userEntity.name,
            email = userEntity.email,
            userId = userEntity.userId,
            password = userEntity.encryptedPassword,
            encryptedPassword = userEntity.encryptedPassword
        )
    }
}

fun UserDto.toUser(passwordEncryptFunction: (String) -> String) = UserEntity(
    name = this.name,
    email = this.email,
    encryptedPassword = passwordEncryptFunction(this.password),
    userId = this.userId
)

fun UserDto.toUserResponse() = UserResponse(
    name = this.name,
    email = this.email,
    userId = this.userId
)