package com.fabridinapoli.userapi.infrastructure.domain.user.memory

import com.fabridinapoli.userapi.domain.user.Email
import com.fabridinapoli.userapi.domain.user.User
import com.fabridinapoli.userapi.domain.user.UserRepository
import java.util.*

class InMemoryUserRepository : UserRepository {

    private var users: MutableList<User> = mutableListOf()

    init {
        User.createOrErrors(
                UUID.randomUUID().toString(),
                "Fabri",
                "Di Napoli",
                "anemail1@test.com",
                "12345"
        ).map { this.users.add(it) }
        User.createOrErrors(
                UUID.randomUUID().toString(),
                "John",
                "Smith",
                "anemail@test.com",
                "12345"
        ).map { this.users.add(it) }
    }

    override fun findAll(): MutableList<User> = users

    override fun findBy(email: Email): User? {
        return users.firstOrNull { it.email == email }
    }

    override fun save(user: User) {
        this.users.add(user)
    }

    fun setUsers(users: MutableList<User>) {
        this.users = users
    }
}