package com.droissor.userapi.request

import com.droissor.userapi.vo.UserDto
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class UserCreateRequest(
    @field:NotBlank(message = "name cannot be null or blank")
    val name: String,

    @field:NotBlank(message = "email cannot be null or blank")
    @field:Email
    val email: String,

    @field:NotBlank(message = "password cannot be null or blank")
    @field:Size(min = 8, max = 16, message = "password must be between 8 and 16 characters")
    val password: String
)

fun UserCreateRequest.toUserDto() = UserDto(
    name = this.name,
    email = this.email,
    password = this.password
)