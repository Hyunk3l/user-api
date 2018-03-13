package com.fabridinapoli.userapi.infrastructure.domain.user

import com.fabridinapoli.userapi.domain.user.User
import com.fabridinapoli.userapi.domain.user.UserId
import com.fabridinapoli.userapi.domain.user.UserRepository
import java.util.UUID

class InMemoryUserRepository : UserRepository {

    private var users: MutableList<User> = mutableListOf()

    constructor() {

        this.users = mutableListOf(
                User(UserId(UUID.randomUUID()), "Fabri"),
                User(UserId(UUID.randomUUID()), "John")
        )
    }

    override fun findAll(): MutableList<User> = users

    fun setUsers(users: MutableList<User>) {
        this.users = users
    }
}