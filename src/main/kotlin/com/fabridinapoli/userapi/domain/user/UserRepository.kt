package com.fabridinapoli.userapi.domain.user

interface UserRepository {
    fun findAll(): MutableList<User>
}