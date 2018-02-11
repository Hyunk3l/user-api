package com.fabridinapoli.userapi.application.service.getusers

import com.fabridinapoli.userapi.infrastructure.domain.user.InMemoryUserRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GetUsersShould {

    @Test
    fun `return a list of valid users`() {
        val inMemoryUserRepository = InMemoryUserRepository()
        val expectedUsers = GetUsersResponse(1, "Fabri")
        val getUsers = GetUsers(inMemoryUserRepository)

        val users = getUsers.execute()

        assertEquals(expectedUsers, users.blockFirst())
    }
}