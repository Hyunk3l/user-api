package com.fabridinapoli.userapi.application.service.saveuser

import com.fabridinapoli.userapi.domain.user.User
import com.fabridinapoli.userapi.domain.user.UserAlreadyExistsException
import com.fabridinapoli.userapi.domain.user.UserId
import com.fabridinapoli.userapi.domain.user.UserRepository
import com.fabridinapoli.userapi.infrastructure.domain.user.memory.InMemoryUserRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.ThrowableAssert
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono
import java.util.UUID

class SaveUserShould {

    private lateinit var userRepository: UserRepository

    private lateinit var saveUser: SaveUser

    @BeforeEach
    fun setUp() {
        userRepository = InMemoryUserRepository()
        saveUser = SaveUser(userRepository)
    }

    @Test
    fun `save new user` () {
        val saveUserRequest = createSaveUserRequest()

        val saveUserResponse = saveUser.execute(saveUserRequest)
        UUID.fromString(saveUserResponse.block()?.id)
    }

    @Test
    fun `throw user already exists exception`() {
        userRepository.save(User(UserId("uuid"), VALID_NAME, VALID_SURNAME, VALID_EMAIL, VALID_PASSWORD))
        val saveUserRequest = createSaveUserRequest()

        val throwable = ThrowableAssert.catchThrowable {
            SaveUser(userRepository).execute(saveUserRequest)
        }

        assertThat(throwable).isInstanceOf(UserAlreadyExistsException::class.java)
    }

    private fun createSaveUserRequest(): SaveUserRequest {
        return SaveUserRequest(
                VALID_NAME,
                VALID_SURNAME,
                VALID_EMAIL,
                VALID_PASSWORD
        )
    }

    companion object {
        const val VALID_NAME: String = "Fabri"
        const val VALID_SURNAME: String = "Di Napoli"
        const val VALID_EMAIL: String = "a.random.email@gmail.com"
        const val VALID_PASSWORD: String = "123456"
    }
}