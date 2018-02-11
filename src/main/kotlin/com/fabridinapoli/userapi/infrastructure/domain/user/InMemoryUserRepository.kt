package com.fabridinapoli.userapi.infrastructure.domain.user

import com.fabridinapoli.userapi.domain.user.User
import com.fabridinapoli.userapi.domain.user.UserRepository

class InMemoryUserRepository: UserRepository {
    override fun findAll(): MutableList<User> {
        return mutableListOf(
                User(1, "Fabri")
        )
    }
}