package com.droissor.userapi.service

import com.droissor.userapi.entity.User
import com.droissor.userapi.repository.UserRepository
import com.droissor.userapi.vo.UserDto
import com.droissor.userapi.vo.toUser
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository, val bCryptPasswordEncoder: BCryptPasswordEncoder) {

    fun createUser(userDto: UserDto): User {
        val user = userDto.toUser { password -> bCryptPasswordEncoder.encode(password) }
        return userRepository.save(user)
    }
}
