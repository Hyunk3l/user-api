package com.fabridinapoli.userapi.domain.user

class User(val id: UserId, val name: String)

data class UserId(val id: String)