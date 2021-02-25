package com.droissor.userapi.entity

import com.droissor.userapi.response.UserResponse
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user")
data class UserEntity (
    @Id
    @GeneratedValue
    val id: Long = -1,

    @Column(nullable = false, length = 150)
    val name: String,

    @Column(nullable = false, length = 150, unique = true)
    val email: String,

    @Column(nullable = false)
    val encryptedPassword: String,

    @Column(nullable = false, unique = true)
    val userId: String
)