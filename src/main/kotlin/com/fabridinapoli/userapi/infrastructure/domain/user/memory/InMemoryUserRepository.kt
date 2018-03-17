package com.fabridinapoli.userapi.infrastructure.domain.user.memory

import com.fabridinapoli.userapi.domain.user.User
import com.fabridinapoli.userapi.domain.user.UserId
import com.fabridinapoli.userapi.domain.user.UserRepository
import java.util.UUID

class InMemoryUserRepository : UserRepository {

    private var users: MutableList<User> = mutableListOf()

    init {
        this.users = mutableListOf(
                User(UserId(UUID.randomUUID().toString()), "Fabri", "Di Napoli", "anemail1@test.com", "12345"),
                User(UserId(UUID.randomUUID().toString()), "John", "Smith", "anemail@test.com", "12345")
        )
    }

    override fun findAll(): MutableList<User> = users

    override fun save(user: User) {
        users.add(user)
    }

    fun setUsers(users: MutableList<User>) {
        this.users = users
    }
}