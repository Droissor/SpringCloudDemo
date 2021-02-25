package com.droissor.userapi.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class EncryptConfig {
    @Bean
    fun bCryptPasswordEncoder() = BCryptPasswordEncoder()
}