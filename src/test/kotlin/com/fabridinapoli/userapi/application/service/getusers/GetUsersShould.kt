package com.fabridinapoli.userapi.application.service.getusers

import com.fabridinapoli.userapi.domain.user.Email
import com.fabridinapoli.userapi.domain.user.User
import com.fabridinapoli.userapi.domain.user.UserId
import com.fabridinapoli.userapi.infrastructure.domain.user.memory.InMemoryUserRepository
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test
import java.util.UUID

class GetUsersShould {

    @Test
    fun `return a list of valid users`() {
        val inMemoryUserRepository = InMemoryUserRepository()
        inMemoryUserRepository.setUsers(createUsers())
        val expectedUsers = GetUsersResponse(VALID_USER_ID.value, VALID_NAME, VALID_SURNAME)
        val getUsers = GetUsers(inMemoryUserRepository)

        val users = getUsers.execute()

        expectedUsers shouldEqual users.blockFirst()
    }

    private fun createUsers(): MutableList<User> {
        return mutableListOf(
                User(VALID_USER_ID, VALID_NAME, VALID_SURNAME, VALID_EMAIL, VALID_PASSWORD)
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

    companion object {
        private const val VALID_NAME: String = "John"
        private const val VALID_SURNAME: String = "Smith"
        private const val VALID_PASSWORD: String = "123456"
        private val VALID_USER_ID: UserId = UserId(UUID.randomUUID().toString())
        private val VALID_EMAIL = Email("anemail@test.com")
    }
}