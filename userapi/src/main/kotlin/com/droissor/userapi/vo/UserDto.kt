package com.droissor.userapi.vo

import com.droissor.userapi.entity.UserEntity
import com.droissor.userapi.response.Album
import com.droissor.userapi.response.UserResponse
import java.io.Serializable
import java.util.*

data class UserDto(
        val name: String,
        val email: String,
        val password: String,
        val encryptedPassword: String? = null,
        val userId: String = UUID.randomUUID().toString(),
        var albums: List<Album>
) : Serializable {
    companion object {
        fun fromUser(userEntity: UserEntity) = UserDto(
                name = userEntity.name,
                email = userEntity.email,
                userId = userEntity.userId,
                password = userEntity.encryptedPassword,
                encryptedPassword = userEntity.encryptedPassword,
                albums = listOf()
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
        userId = this.userId,
        albums = this.albums
)