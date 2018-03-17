package com.fabridinapoli.userapi.application.service.getusers

import com.fabridinapoli.userapi.domain.user.User
import com.fabridinapoli.userapi.domain.user.UserId
import com.fabridinapoli.userapi.infrastructure.domain.user.memory.InMemoryUserRepository
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test
import java.util.UUID

class GetUsersShould {

    companion object {
        val VALID_USER_ID: UserId = UserId(UUID.randomUUID().toString())
        const val VALID_USER_NAME: String = "John"
        const val VALID_SURNAME: String = "Smith"
        const val VALID_EMAIL: String = "anemail@test.com"
        const val VALID_PASSWORD: String = "123456"
    }

    @Test
    fun `return a list of valid users`() {
        val inMemoryUserRepository = InMemoryUserRepository()
        inMemoryUserRepository.setUsers(createUsers())
        val expectedUsers = GetUsersResponse(VALID_USER_ID.id, VALID_USER_NAME)
        val getUsers = GetUsers(inMemoryUserRepository)

        val users = getUsers.execute()

        expectedUsers shouldEqual users.blockFirst()
    }

    private fun createUsers(): MutableList<User> {
        return mutableListOf(
                User(VALID_USER_ID, VALID_USER_NAME, VALID_SURNAME, VALID_EMAIL, VALID_PASSWORD)
        )
    }

    @Test
    fun `return an empty list if not users`() {
        val inMemoryUserRepository = InMemoryUserRepository()
        inMemoryUserRepository.setUsers(mutableListOf())
        val getUsers = GetUsers(inMemoryUserRepository)

        val users = getUsers.execute()

        users.blockFirst().shouldBeNull()
    }
}