package com.droissor.userapi.service

import com.droissor.userapi.entity.UserEntity
import com.droissor.userapi.repository.UserRepository
import com.droissor.userapi.vo.UserDto
import com.droissor.userapi.vo.toUser
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository, val bCryptPasswordEncoder: BCryptPasswordEncoder) :
    UserDetailsService {

    fun createUser(userDto: UserDto): UserDto {
        val user = userDto.toUser { password -> bCryptPasswordEncoder.encode(password) }
        return UserDto.fromUser(userRepository.save(user))
    }

    fun getUserDetailByEmail(email: String): UserDto = UserDto.fromUser(userRepository.findByEmail(email))

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByEmail(username)
        return User(user.email, user.encryptedPassword, true, true, true, true, listOf())
    }
}
