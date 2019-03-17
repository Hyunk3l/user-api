package com.fabridinapoli.userapi.application.service.saveuser

import com.fabridinapoli.userapi.domain.user.User
import com.fabridinapoli.userapi.domain.user.UserAlreadyExistsException
import com.fabridinapoli.userapi.domain.user.UserId
import com.fabridinapoli.userapi.domain.user.UserRepository
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono
import java.util.UUID

class SaveUser(private val userRepository: UserRepository) {

    fun execute(saveUserRequest: SaveUserRequest): Mono<SaveUserResponse> {

        userRepository.findBy(saveUserRequest.email)?.let {
            throw UserAlreadyExistsException("User ${saveUserRequest.email} already exists")
        }

        val user = createUser(saveUserRequest)
        userRepository.save(user)

        return SaveUserResponse(user.id.value).toMono()
    }

    private fun createUser(saveUserRequest: SaveUserRequest): User {
        return User(
                UserId(UUID.randomUUID().toString()),
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
