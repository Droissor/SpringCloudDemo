package com.droissor.userapi.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class WebSecurity(@Value("\${gateway.ipaddress}") val gatewayIpAddress: String) : WebSecurityConfigurerAdapter() {

    override fun configure(httpSecurity: HttpSecurity) {
        httpSecurity.csrf().disable()
        httpSecurity.authorizeRequests().antMatchers("/**").hasIpAddress(gatewayIpAddress)
        httpSecurity.headers().frameOptions().disable()
    }

    @Bean
    fun bCryptPasswordEncoder() = BCryptPasswordEncoder()
}