package com.fabridinapoli.userapi.Infrastructure.framework.configuration

import com.fabridinapoli.userapi.Application.Service.getusers.GetUsers
import com.fabridinapoli.userapi.Infrastructure.framework.controller.UsersController
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ControllerConfiguration {

    @Bean
    fun getUsers() = GetUsers()

    @Bean
    fun usersController(getUsers: GetUsers) = UsersController(getUsers)
}