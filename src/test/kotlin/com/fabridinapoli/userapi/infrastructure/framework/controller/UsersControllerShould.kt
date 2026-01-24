package com.fabridinapoli.userapi.infrastructure.framework.controller

import com.fabridinapoli.userapi.domain.user.User
import com.fabridinapoli.userapi.infrastructure.domain.user.memory.InMemoryUserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.Customization
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import org.skyscreamer.jsonassert.JSONCompareMode.LENIENT
import org.skyscreamer.jsonassert.RegularExpressionValueMatcher
import org.skyscreamer.jsonassert.comparator.CustomComparator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.web.reactive.server.WebTestClient


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsersControllerShould {

    @Autowired
    lateinit var userRepository: InMemoryUserRepository

    lateinit var webTestClient: WebTestClient

    @LocalServerPort
    var port: Int = 0

    @BeforeEach
    fun setUp() {
        webTestClient = WebTestClient.bindToServer()
                .baseUrl("http://localhost:$port")
                .build()
    }

    @Test
    fun `return a list of users`() {
        createAListOfUsers()
        val expectedResponse = readFromResources("/responses/get_users.json")

        val responseBody = webTestClient.get()
                .uri(PATH)
                .exchange()
                .expectStatus().isOk
                .expectBody(String::class.java)
                .returnResult()
                .responseBody

        assertThat(responseBody).isNotNull()
        JSONAssert.assertEquals(expectedResponse, responseBody, LENIENT)
    }

    @Test
    fun `create a new user`() {
        userRepository.setUsers(mutableListOf())
        val request = RequestUser(NAME, SURNAME, EMAIL, PASSWORD)

        val responseBody = webTestClient.post()
                .uri(PATH)
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated
                .expectBody(String::class.java)
                .returnResult()
                .responseBody

        assertThat(responseBody).isNotNull()
        JSONAssert.assertEquals("""{"id":"x"}""", responseBody,
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
        val expectedResponse = """{"message": "User $EMAIL already exists"}"""
        val request = RequestUser(NAME, SURNAME, EMAIL, PASSWORD)

        val responseBody = webTestClient.post()
                .uri(PATH)
                .bodyValue(request)
                .exchange()
                .expectStatus().isEqualTo(org.springframework.http.HttpStatus.CONFLICT)
                .expectBody(String::class.java)
                .returnResult()
                .responseBody

        assertThat(responseBody).isNotNull()
        JSONAssert.assertEquals(expectedResponse, responseBody, LENIENT)
    }

    @Test
    fun `return bad request when trying to create a user with non valid email`() {
        val expectedResponse = """{"messages": ["Email not valid"]}"""
        val request = RequestUser(NAME, SURNAME, NON_VALID_EMAIL, PASSWORD)

        val responseBody = webTestClient.post()
                .uri(PATH)
                .bodyValue(request)
                .exchange()
                .expectStatus().isBadRequest
                .expectBody(String::class.java)
                .returnResult()
                .responseBody

        assertThat(responseBody).isNotNull()
        JSONAssert.assertEquals(expectedResponse, responseBody, LENIENT)
    }

    private fun readFromResources(path: String): String {
        return UsersControllerShould::class.java
                .getResource(path)
                .readText()
    }

    private fun createAListOfUsers() {
        val user = User(USER_ID, NAME, SURNAME, EMAIL, PASSWORD)
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
