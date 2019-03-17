package com.fabridinapoli.userapi.application.service.saveuser

import com.fabridinapoli.userapi.domain.user.*
import com.fabridinapoli.userapi.infrastructure.domain.user.memory.InMemoryUserRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.assertj.core.api.ThrowableAssert
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
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
        UUID.fromString(saveUserResponse.id)
    }

    @Test
    fun `throw user already exists exception`() {
        userRepository.save(User(NAME, SURNAME, EMAIL.value, PASSWORD))
        val saveUserRequest = createSaveUserRequest()

        val throwable = catchThrowable {
            SaveUser(userRepository).execute(saveUserRequest)
        }

        assertThat(throwable).isInstanceOf(UserAlreadyExistsException::class.java)
    }

    @Test
    fun `throw email not valid exception`() {
        val saveUserRequest = SaveUserRequest(
                NAME,
                SURNAME,
                "non-valid-email",
                PASSWORD
        )

        val throwable = ThrowableAssert.catchThrowable {
            SaveUser(userRepository).execute(saveUserRequest)
        }

        assertThat(throwable).isInstanceOf(EmailNotValidException::class.java)
    }

    private fun createSaveUserRequest(): SaveUserRequest {
        return SaveUserRequest(
                NAME,
                SURNAME,
                EMAIL.value,
                PASSWORD
        )
    }

    companion object {
        private const val NAME = "Fabri"
        private const val SURNAME = "Di Napoli"
        private const val PASSWORD = "123456"
        private val EMAIL = Email("a.random.email@gmail.com")
    }
}