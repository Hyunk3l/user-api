package com.fabridinapoli.userapi.infrastructure.domain.user.memory

import com.fabridinapoli.userapi.domain.user.User
import com.fabridinapoli.userapi.domain.user.UserId
import com.fabridinapoli.userapi.domain.user.UserRepository
import java.util.UUID

class InMemoryUserRepository : UserRepository {

    private var users: MutableList<User> = mutableListOf()

    init {
        this.users = mutableListOf(
                User(UserId(UUID.randomUUID().toString()), "Fabri"),
                User(UserId(UUID.randomUUID().toString()), "John")
        )
    }

    override fun findAll(): MutableList<User> = users

    fun setUsers(users: MutableList<User>) {
        this.users = users
    }
}