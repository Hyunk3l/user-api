package com.fabridinapoli.userapi.infrastructure.framework

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class UserApiApplication

fun main(args: Array<String>) {
    SpringApplication.run(UserApiApplication::class.java, *args)
}
