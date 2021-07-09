package com.droissor.userapi.controller

import com.droissor.userapi.request.UserCreateRequest
import com.droissor.userapi.request.toUserDto
import com.droissor.userapi.response.UserResponse
import com.droissor.userapi.service.UserService
import com.droissor.userapi.vo.toUserResponse
import javax.validation.Valid
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.env.Environment
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    @Value("\${eureka.instance.instance-id}") val instanceId: String,
    val environment: Environment,
    val userService: UserService
) {

    @GetMapping("/info")
    fun status() = ResponseEntity.ok(
        "Response by $instanceId, token default expirationSeconds=${
            environment.getProperty(EXPIRATION_SECONDS_PROP_NAME)
        }"
    )

    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE]
    )
    fun createUser(@Valid @RequestBody userUpdateRequest: UserCreateRequest): ResponseEntity<UserResponse> {
        val user = userService.createUser(userUpdateRequest.toUserDto())
        return ResponseEntity.ok(user.toUserResponse())
    }

    companion object {
        const val EXPIRATION_SECONDS_PROP_NAME = "session.expiration.seconds"
    }
}