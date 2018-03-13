package com.fabridinapoli.userapi.infrastructure.domain.user

import com.fabridinapoli.userapi.domain.user.User
import com.fabridinapoli.userapi.domain.user.UserRepository

class InMemoryUserRepository : UserRepository {

    private var users: MutableList<User> = mutableListOf()

    constructor() {
        this.users = mutableListOf(
                User(1, "Fabri"),
                User(2, "John")
        )
    }

    override fun findAll(): MutableList<User> = users

    fun setUsers(users: MutableList<User>) {
        this.users = users
    }
}