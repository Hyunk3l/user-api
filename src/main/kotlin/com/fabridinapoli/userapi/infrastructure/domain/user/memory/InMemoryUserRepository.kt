package com.fabridinapoli.userapi.infrastructure.domain.user.memory

import com.fabridinapoli.userapi.domain.user.Email
import com.fabridinapoli.userapi.domain.user.User
import com.fabridinapoli.userapi.domain.user.UserRepository

class InMemoryUserRepository : UserRepository {

    private var users: MutableList<User> = mutableListOf()

    init {
        this.users = mutableListOf(
                User("Fabri", "Di Napoli", "anemail1@test.com", "12345"),
                User("John", "Smith", "anemail@test.com", "12345")
        )
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