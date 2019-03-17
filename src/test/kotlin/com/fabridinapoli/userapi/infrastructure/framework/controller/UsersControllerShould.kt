package com.fabridinapoli.userapi.infrastructure.framework.controller

import com.fabridinapoli.userapi.domain.user.Email
import com.fabridinapoli.userapi.domain.user.User
import com.fabridinapoli.userapi.domain.user.UserId
import com.fabridinapoli.userapi.infrastructure.domain.user.memory.InMemoryUserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.skyscreamer.jsonassert.Customization
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import org.skyscreamer.jsonassert.JSONCompareMode.LENIENT
import org.skyscreamer.jsonassert.RegularExpressionValueMatcher
import org.skyscreamer.jsonassert.comparator.CustomComparator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
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
        val expectedResponse = readFromResources("/responses/get_users.json")

        val response = restTemplate.getForEntity(PATH, String::class.java)

        assertThat(response).isNotNull
        assertThat(HttpStatus.OK).isEqualTo(response.statusCode)
        JSONAssert.assertEquals(expectedResponse, response.body, LENIENT)
    }

    @Test
    fun `create a new user`() {
        userRepository.setUsers(mutableListOf())
        val request = HttpEntity(RequestUser(NAME, SURNAME, EMAIL, PASSWORD))

        val response = restTemplate.postForEntity(PATH, request, String::class.java)

        assertThat(response).isNotNull
        assertThat(HttpStatus.CREATED).isEqualTo(response.statusCode)
        JSONAssert.assertEquals("""{"id":"x"}""", response.body,
                CustomComparator(JSONCompareMode.STRICT,
                        Customization("id",
                                RegularExpressionValueMatcher("([a-z0-9\\-]+)")
                        )
                )
        )
    }

    @Test
    fun `return conflict when trying to create an existing user`() {
        createAListOfUsers()
        val expectedResponse = """{"message": "User ${EMAIL} already exists"}"""
        val request = HttpEntity(RequestUser(NAME, SURNAME, EMAIL, PASSWORD))

        val response = restTemplate.postForEntity(PATH, request, String::class.java)

        assertThat(response).isNotNull
        assertThat(HttpStatus.CONFLICT).isEqualTo(response.statusCode)
        JSONAssert.assertEquals(expectedResponse, response.body, LENIENT)
    }

    @Test
    fun `return bad request when trying to create a user with non valid email`() {
        val expectedResponse = """{"messages": ["Email not valid"]}"""
        val request = HttpEntity(RequestUser(NAME, SURNAME, NON_VALID_EMAIL, PASSWORD))

        val response = restTemplate.postForEntity(PATH, request, String::class.java)

        assertThat(response).isNotNull
        assertThat(HttpStatus.BAD_REQUEST).isEqualTo(response.statusCode)
        JSONAssert.assertEquals(expectedResponse, response.body, LENIENT)
    }

    private fun readFromResources(path: String): String {
        return UsersControllerShould::class.java
                .getResource(path)
                .readText()
    }

    private fun createAListOfUsers() {
        val user = User(UserId(USER_ID), NAME, SURNAME, Email(EMAIL), PASSWORD)
        userRepository.setUsers(mutableListOf(user))
    }

    companion object {
        private const val PATH = "/v1/users"
        private const val USER_ID = "303c5cce-9c5a-4f40-97fe-80ca45fdcd86"
        private const val NAME = "Fabri"
        private const val SURNAME = "Di Napoli"
        private const val PASSWORD = "123456"
        private const val NON_VALID_EMAIL = "fake-email"
        private const val EMAIL = "some@email.com"
    }
}