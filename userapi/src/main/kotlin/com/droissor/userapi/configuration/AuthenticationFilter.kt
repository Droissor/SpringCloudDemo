package com.droissor.userapi.configuration

import com.droissor.userapi.request.LoginRequest
import com.droissor.userapi.service.UserService
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.time.Instant
import java.util.Date
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class AuthenticationFilter(
    private val userService: UserService,
    authenticationManager: AuthenticationManager,
    private val tokenSecret: String,
    private val sessionExpirationSeconds: Long
) :
    UsernamePasswordAuthenticationFilter(authenticationManager) {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {

        val loginCredentials = jacksonObjectMapper().readValue(request.inputStream, LoginRequest::class.java)

        return authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                loginCredentials.username,
                loginCredentials.password,
                listOf()
            )
        )
    }

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authResult: Authentication
    ) {
        val username = (authResult.principal as User).username
        val user = userService.getUserDetailByEmail(username)

        val token = Jwts.builder()
            .setSubject(user.userId)
            .setExpiration(Date.from(Instant.now().plusSeconds(sessionExpirationSeconds)))
            .signWith(SignatureAlgorithm.HS512, tokenSecret)
            .compact()

        response.addHeader("token", token)
        response.addHeader("userID", user.userId)
    }
}