package com.fabridinapoli.userapi.application.service.getusers

import com.fabridinapoli.userapi.domain.user.UserRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.toFlux

class GetUsers(private val userRepository: UserRepository) {

    fun execute(): Flux<GetUsersResponse> {

        return userRepository
                .findAll()
                .map { GetUsersResponse(it.id, it.name) }
                .toFlux()
    }
}