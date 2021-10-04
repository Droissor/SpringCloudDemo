package com.droissor.userapi.service

import com.droissor.userapi.client.AlbumClient
import com.droissor.userapi.repository.UserRepository
import com.droissor.userapi.vo.UserDto
import com.droissor.userapi.vo.toUser
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository, val bCryptPasswordEncoder: BCryptPasswordEncoder, val albumClient: AlbumClient) :
        UserDetailsService {

    fun createUser(userDto: UserDto): UserDto {
        val user = userDto.toUser { password -> bCryptPasswordEncoder.encode(password) }
        return UserDto.fromUser(userRepository.save(user))
    }

    fun getUserDetailByEmail(email: String): UserDto = UserDto.fromUser(userRepository.findByEmail(email))

    fun getUserDetailById(userId: String): UserDto {
        val userDto = UserDto.fromUser(userRepository.findByUserId(userId))

        logger.info("m=getUserDetailById, msg={}", "Calling albumAPI")
        userDto.albums = albumClient.getUserAlbums(userId)
        logger.info("m=getUserDetailById, msg={}", "Success call")

        return userDto
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByEmail(username)
        return User(user.email, user.encryptedPassword, true, true, true, true, listOf())
    }

    companion object {
        val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }
}
