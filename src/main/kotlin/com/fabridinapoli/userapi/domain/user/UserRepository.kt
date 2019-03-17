package com.fabridinapoli.userapi.domain.user

interface UserRepository {
    fun findAll(): MutableList<User>

    fun findBy(email: String): User?

    fun save(user: User)
}