package com.fabridinapoli.userapi.infrastructure.framework.configuration

import com.fabridinapoli.userapi.application.service.getusers.GetUsers
import com.fabridinapoli.userapi.application.service.saveuser.SaveUser
import com.fabridinapoli.userapi.infrastructure.domain.user.memory.InMemoryUserRepository
import com.fabridinapoli.userapi.infrastructure.framework.controller.UsersController
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ControllerConfiguration {

    @Bean
    fun userRepository() = InMemoryUserRepository()

    @Bean
    fun getUsers(inMemoryUserRepository: InMemoryUserRepository) = GetUsers(inMemoryUserRepository)

    @Bean
    fun saveUser(inMemoryUserRepository: InMemoryUserRepository) = SaveUser(inMemoryUserRepository)

    @Bean
    fun usersController(getUsers: GetUsers, saveUser: SaveUser) = UsersController(getUsers, saveUser)
}