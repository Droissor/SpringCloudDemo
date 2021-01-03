package com.droissor.accountmanagement

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class AccountManagementApplication

fun main(args: Array<String>) {
    runApplication<AccountManagementApplication>(*args)
}
