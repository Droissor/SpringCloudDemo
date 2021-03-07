package com.droissor.userapi.configuration

import com.droissor.userapi.configuration.filter.AuthenticationFilter
import com.droissor.userapi.service.UserService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class WebSecurity(
    val userService: UserService,
    val bCryptPasswordEncoder: BCryptPasswordEncoder,
    @Value("\${gateway.ipaddress}") val gatewayIpAddress: String,
    @Value("\${token.secret}") private val tokenSecret: String,
    @Value("\${session.expiration.seconds}") private val sessionExpirationSeconds: Long,
    @Value("\${login.url}") private val loginUrl: String
) : WebSecurityConfigurerAdapter() {

    override fun configure(httpSecurity: HttpSecurity) {
        httpSecurity.csrf().disable()
        httpSecurity.authorizeRequests().antMatchers("/**").hasIpAddress(gatewayIpAddress)
            .and().addFilter(getAuthenticationFilter())
        httpSecurity.headers().frameOptions().disable()
    }

    private fun getAuthenticationFilter(): AuthenticationFilter {
        val authenticationFilter =
            AuthenticationFilter(userService, authenticationManager(), tokenSecret, sessionExpirationSeconds)
        authenticationFilter.setFilterProcessesUrl(loginUrl)

        return authenticationFilter
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder)
        super.configure(auth)
    }
}