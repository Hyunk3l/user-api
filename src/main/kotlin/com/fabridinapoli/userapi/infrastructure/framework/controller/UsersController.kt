package com.fabridinapoli.userapi.infrastructure.framework.controller

import com.fabridinapoli.userapi.application.service.getusers.GetUsers
import com.fabridinapoli.userapi.application.service.getusers.GetUsersResponse
import com.fabridinapoli.userapi.application.service.saveuser.SaveUser
import com.fabridinapoli.userapi.application.service.saveuser.SaveUserRequest
import com.fabridinapoli.userapi.application.service.saveuser.SaveUserResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/v1/users")
class UsersController(val getUsers: GetUsers, val saveUser: SaveUser) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getUsers(): Flux<GetUsersResponse> = getUsers.execute()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveUser(@RequestBody jsonUser: JsonUser): Mono<SaveUserResponse> {
        return saveUser.execute(
                SaveUserRequest(
                        jsonUser.name,
                        jsonUser.surname,
                        jsonUser.email,
                        jsonUser.password
                )
        )
    }
}