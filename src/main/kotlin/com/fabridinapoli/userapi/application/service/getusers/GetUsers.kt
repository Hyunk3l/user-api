package com.fabridinapoli.userapi.application.service.getusers

import com.fabridinapoli.userapi.domain.user.UserRepository
import reactor.core.publisher.Flux

class GetUsers(private val userRepository: UserRepository) {

    fun execute(): Flux<GetUsersResponse> {
        return Flux.fromIterable(userRepository.findAll())
            .map { GetUsersResponse(it.id.value, it.name.value, it.surname.value) }
    }
}

data class GetUsersResponse(val id: String, val name: String, val surname: String)