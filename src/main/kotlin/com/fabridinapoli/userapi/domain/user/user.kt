package com.fabridinapoli.userapi.domain.user

import arrow.core.Either
import arrow.core.Left
import arrow.core.Right
import com.fabridinapoli.userapi.domain.DomainException
import java.util.*

data class User private constructor(val id: UserId,
                                    val name: Name,
                                    val surname: Surname,
                                    val email: Email,
                                    val password: Password) {

    companion object {
        fun createOrErrors(id: String, name: String, surname: String, email: String, password: String): Either<List<DomainFailure>, User> {
            val errors = mutableListOf<DomainFailure>()
            val eitherEmail = Email.of(email)

            return if (errors.isEmpty()) {
                Right(User(
                        UserId(id),
                        Name(name),
                        Surname(surname),
                        when(eitherEmail) {
                            is Either.Right -> eitherEmail.b
                            else -> throw Exception("aaa")
                        },
                        Password(password)
                ))
            } else Left(errors)
        }
    }
}

data class UserId(val value: String = UUID.randomUUID().toString())

data class Email private constructor(val value: String) {
    init {
        if (!EMAIL_VALIDATION_REGEX.toRegex().matches(value)) {
            throw EmailNotValidException("Email not valid")
        }
    }

    companion object {
        fun of(email: String): Either<DomainFailure, Email> = try {
            Right(Email(email))
        } catch (e: DomainException) {
            Left(EmailNotValid(e.message!!))
        }

        private const val EMAIL_VALIDATION_REGEX = """^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+${'$'}"""
    }
}

data class Name(val value: String)

data class Surname(val value: String)

data class Password(val value: String)