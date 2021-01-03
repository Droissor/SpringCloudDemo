package com.droissor.userapi.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(@Value("\${eureka.instance.instance-id}") val instanceId: String) {

    @GetMapping("/info")
    fun status() = ResponseEntity.ok("Response by $instanceId")

}