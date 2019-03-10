package com.fabridinapoli.userapi.domain.user

class User(val id: UserId, val name: String, val surname: String, val email: String, val password: String)

data class UserId(val value: String)