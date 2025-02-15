package com.fabridinapoli.userapi.infrastructure.framework.controller

import com.fabridinapoli.userapi.domain.user.User
import com.fabridinapoli.userapi.infrastructure.domain.user.memory.InMemoryUserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class UsersControllerShould {
    @Autowired
    lateinit var userRepository: InMemoryUserRepository
    
    @Autowired
    lateinit var webTestClient: WebTestClient
    
    @BeforeEach
    fun setup() {
        userRepository.setUsers(mutableListOf())
    }

    @Test
    fun `return a list of users`() {
        createAListOfUsers()
        val expectedResponse = readFromResources("/responses/get_users.json")
        
        webTestClient.get()
            .uri(PATH)
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .json(expectedResponse)
    }

    @Test
    fun `create a new user`() {
        val request = RequestUser(NAME, SURNAME, EMAIL, PASSWORD)
        
        webTestClient.post()
            .uri(PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .exchange()
            .expectStatus().isCreated
            .expectBody()
            .jsonPath("$.id").exists()
            .jsonPath("$.id").value { id: String ->
                assertThat(id).matches("[a-z0-9\\-]+")
            }
    }

    @Test
    fun `return conflict when trying to create an existing user`() {
        createAListOfUsers()
        val expectedResponse = """{"message": "User $EMAIL already exists"}"""
        val request = RequestUser(NAME, SURNAME, EMAIL, PASSWORD)
        
        webTestClient.post()
            .uri(PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .exchange()
            .expectStatus().isEqualTo(409)
            .expectBody()
            .json(expectedResponse)
    }

    @Test
    fun `return bad request when trying to create a user with non valid email`() {
        val expectedResponse = """{"messages": ["Email not valid"]}"""
        val request = RequestUser(NAME, SURNAME, NON_VALID_EMAIL, PASSWORD)
        
        webTestClient.post()
            .uri(PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .exchange()
            .expectStatus().isBadRequest
            .expectBody()
            .json(expectedResponse)
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