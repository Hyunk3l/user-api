package com.fabridinapoli.userapi.application.service.saveuser

import com.fabridinapoli.userapi.domain.user.User
import com.fabridinapoli.userapi.domain.user.UserId
import com.fabridinapoli.userapi.domain.user.UserRepository
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono
import java.util.UUID

class SaveUser(private val userRepository: UserRepository) {

    fun execute(saveUserRequest: SaveUserRequest): Mono<SaveUserResponse> {
        val user = User(
                UserId(UUID.randomUUID().toString()),
                saveUserRequest.name,
                saveUserRequest.surname,
                saveUserRequest.email,
                saveUserRequest.password
        )
        userRepository.save(user)

        return SaveUserResponse(user.id.value).toMono()
    }
}

data class SaveUserRequest(
        val name: String,
        val surname: String,
        val email: String,
        val password: String
)

data class SaveUserResponse(val id: String)
