package com.fabridinapoli.userapi.application.service.getusers

import reactor.core.publisher.Flux

class GetUsers {

    fun execute(): Flux<GetUsersResponse> {

        val getUsersResponseList: MutableList<GetUsersResponse> = mutableListOf(
                GetUsersResponse(1, "test 1"),
                GetUsersResponse(2, "test 2"),
                GetUsersResponse(3, "test 3"),
                GetUsersResponse(4, "test 4"),
                GetUsersResponse(5, "test 5"),
                GetUsersResponse(6, "test 6")
        )

        return Flux.fromIterable(getUsersResponseList)
    }
}