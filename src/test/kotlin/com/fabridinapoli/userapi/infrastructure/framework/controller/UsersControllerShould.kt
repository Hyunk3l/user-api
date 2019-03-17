package com.fabridinapoli.userapi.infrastructure.framework.controller

import com.fabridinapoli.userapi.domain.user.User
import com.fabridinapoli.userapi.domain.user.UserId
import com.fabridinapoli.userapi.infrastructure.domain.user.memory.InMemoryUserRepository
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import org.skyscreamer.jsonassert.JSONCompareMode.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsersControllerShould {

    @Autowired
    lateinit var userRepository: InMemoryUserRepository

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun `return a list of users`() {
        createAListOfUsers()
        val expectedResponse = UsersControllerShould::class.java
                .getResource("/responses/get_users.json")
                .readText()

        val response = restTemplate.getForEntity("/users", String::class.java)

        assertThat(response).isNotNull
        assertThat(HttpStatus.OK).isEqualTo(response.statusCode)
        JSONAssert.assertEquals(expectedResponse, response.body, LENIENT)
    }

    private fun createAListOfUsers() {
        val user = User(UserId(USER_ID), NAME, SURNAME, EMAIL, PASSWORD)
        userRepository.setUsers(mutableListOf(user))
    }

    companion object {
        const val USER_ID = "303c5cce-9c5a-4f40-97fe-80ca45fdcd86"
        const val NAME = "Fabri"
        const val SURNAME = "Di Napoli"
        const val EMAIL = "some@email.com"
        const val PASSWORD = "123456"
    }
}