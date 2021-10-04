package com.droissor.userapi.response

data class Album(
        val albumId: String,
        val userId: String,
        val name: String,
        val description: String
)