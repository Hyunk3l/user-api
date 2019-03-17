package com.fabridinapoli.userapi.domain.user

import java.util.UUID

class User(val id: UserId,
           val name: String,
           val surname: String,
           val email: Email,
           val password: String) {

    constructor(name: String,
                surname: String,
                email: String,
                password: String) : this(UserId(), name, surname, Email(email), password)
}

data class UserId(val value: String = UUID.randomUUID().toString())

data class Email(val value: String) {
    init {
        if (!EMAIL_VALIDATION_REGEX.toRegex().matches(value)) {
            throw EmailNotValidException("Email not valid")
        }
    }

    companion object {
        private const val EMAIL_VALIDATION_REGEX = """^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+${'$'}"""
    }
}