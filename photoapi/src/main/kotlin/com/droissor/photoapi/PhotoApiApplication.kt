package com.droissor.photoapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class PhotoApiApplication

fun main(args: Array<String>) {
	runApplication<PhotoApiApplication>(*args)
}
