package com.droissor.albumapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class AlbumApiApplication

fun main(args: Array<String>) {
	runApplication<AlbumApiApplication>(*args)
}
