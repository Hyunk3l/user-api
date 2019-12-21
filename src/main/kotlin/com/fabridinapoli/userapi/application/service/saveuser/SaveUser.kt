package com.fabridinapoli.userapi.application.service.saveuser

import arrow.core.Either
import com.fabridinapoli.userapi.domain.user.User
import com.fabridinapoli.userapi.domain.user.UserAlreadyExistsException
import com.fabridinapoli.userapi.domain.user.UserRepository
import java.util.*

class SaveUser(private val userRepository: UserRepository) {

    fun execute(saveUserRequest: SaveUserRequest): SaveUserResponse {
        val user = createUser(saveUserRequest)

        userRepository.findBy(user.email)?.let {
            throw UserAlreadyExistsException("User ${saveUserRequest.email} already exists")
        }

        userRepository.save(user)

        return SaveUserResponse(user.id.value)
    }

    private fun createUser(saveUserRequest: SaveUserRequest): User {
        val user = User.createOrErrors(
                UUID.randomUUID().toString(),
                saveUserRequest.name,
                saveUserRequest.surname,
                saveUserRequest.email,
                saveUserRequest.password
        )

        return when(user) {
            is Either.Right -> user.b
            else -> throw Exception("Cannot create a user")
        }
    }
}

data class SaveUserRequest(
        val name: String,
        val surname: String,
        val email: String,
        val password: String
)

data class SaveUserResponse(val id: String)
