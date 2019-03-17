package com.fabridinapoli.userapi.application.service.saveuser

import com.fabridinapoli.userapi.domain.user.Email
import com.fabridinapoli.userapi.domain.user.User
import com.fabridinapoli.userapi.domain.user.UserAlreadyExistsException
import com.fabridinapoli.userapi.domain.user.UserRepository

class SaveUser(private val userRepository: UserRepository) {

    fun execute(saveUserRequest: SaveUserRequest): SaveUserResponse {
        userRepository.findBy(Email(saveUserRequest.email))?.let {
            throw UserAlreadyExistsException("User ${saveUserRequest.email} already exists")
        }

        val user = createUser(saveUserRequest)
        userRepository.save(user)

        return SaveUserResponse(user.id.value)
    }

    private fun createUser(saveUserRequest: SaveUserRequest): User {
        return User(
                saveUserRequest.name,
                saveUserRequest.surname,
                saveUserRequest.email,
                saveUserRequest.password
        )
    }
}

data class SaveUserRequest(
        val name: String,
        val surname: String,
        val email: String,
        val password: String
)

data class SaveUserResponse(val id: String)
