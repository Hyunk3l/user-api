package com.fabridinapoli.userapi.domain.user

import java.util.UUID

class User(val id: UserId,
           val name: String,
           val surname: String,
           val email: String,
           val password: String) {

    constructor(name: String,
                surname: String,
                email: String,
                password: String) : this(UserId(), name, surname, email, password)
}

data class UserId(val value: String = UUID.randomUUID().toString())