package com.fabridinapoli.userapi.infrastructure.framework.controller

import com.fabridinapoli.userapi.application.service.getusers.GetUsers
import com.fabridinapoli.userapi.application.service.getusers.GetUsersResponse
import com.fabridinapoli.userapi.application.service.saveuser.SaveUser
import com.fabridinapoli.userapi.domain.user.User
import com.fabridinapoli.userapi.domain.user.UserId
import com.fabridinapoli.userapi.infrastructure.domain.user.memory.InMemoryUserRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object UsersControllerSpec : Spek({

    describe("get a list of valid users") {
        val user = User(
                USER_ID,
                NAME,
                SURNAME,
                EMAIL,
                PASSWORD
        )
        val userRepository = InMemoryUserRepository()
        userRepository.setUsers(mutableListOf(user))
        val expectedResponse = GetUsersResponse(ID, NAME, SURNAME)
        val getUsers = GetUsers(userRepository)
        val saveUsers = SaveUser(userRepository)
        val usersController = UsersController(getUsers, saveUsers)

        it("test /users responds with a list of users") {
            val response = usersController.getUsers()
            assertEquals(response.blockFirst(), expectedResponse)
        }
    }
})

private const val ID = "some-shitty-uuid"
private const val NAME = "Fabri"
private const val SURNAME = "Di Napoli"
private const val EMAIL = "some.email@email.com"
private const val PASSWORD = "somepwd"
private val USER_ID = UserId(ID)
