package com.fabridinapoli.userapi.domain.user

import java.util.UUID

class User(val id: UserId,
           val name: Name,
           val surname: Surname,
           val email: Email,
           val password: Password) {

    constructor(name: String,
                surname: String,
                email: String,
                password: String) :
            this(UserId(), Name(name), Surname(surname), Email(email), Password(password))

    constructor(id: String,
                name: String,
                surname: String,
                email: String,
                password: String) :
            this(UserId(id), Name(name), Surname(surname), Email(email), Password(password))
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

data class Name(val value: String)

data class Surname(val value: String)

data class Password(val value: String)