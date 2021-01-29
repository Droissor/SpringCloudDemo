package com.droissor.userapi.repository

import com.droissor.userapi.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>