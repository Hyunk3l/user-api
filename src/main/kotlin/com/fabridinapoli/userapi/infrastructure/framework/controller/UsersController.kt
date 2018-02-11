package com.fabridinapoli.userapi.Infrastructure.framework.controller

import com.fabridinapoli.userapi.application.service.getusers.GetUsers
import com.fabridinapoli.userapi.application.service.getusers.GetUsersResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class UsersController(val getUsers: GetUsers) {

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    fun getUsers(): Flux<GetUsersResponse> {
        return getUsers.execute()
    }
}