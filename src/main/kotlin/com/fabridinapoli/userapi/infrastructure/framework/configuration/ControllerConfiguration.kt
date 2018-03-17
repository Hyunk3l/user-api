package com.fabridinapoli.userapi.infrastructure.framework.configuration

import com.fabridinapoli.userapi.application.service.getusers.GetUsers
import com.fabridinapoli.userapi.infrastructure.domain.user.memory.InMemoryUserRepository
import com.fabridinapoli.userapi.infrastructure.framework.controller.UsersController
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ControllerConfiguration {

    @Bean
    fun getUsers() = GetUsers(InMemoryUserRepository())

    @Bean
    fun usersController(getUsers: GetUsers) = UsersController(getUsers)
}