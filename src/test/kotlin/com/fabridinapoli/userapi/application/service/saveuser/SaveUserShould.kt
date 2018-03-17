package com.fabridinapoli.userapi.application.service.saveuser

import com.fabridinapoli.userapi.domain.user.UserRepository
import com.fabridinapoli.userapi.infrastructure.domain.user.memory.InMemoryUserRepository
import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono
import java.util.UUID

class SaveUserShould {

    companion object {
        const val VALID_NAME: String = "Fabri"
        const val VALID_SURNAME: String = "Di Napoli"
        const val VALID_EMAIL: String = "fabri.dinapoli@gmail.com"
        const val VALID_PASSWORD: String = "123456"
    }

    @Test
    fun `save new user` () {
        val userRepository : UserRepository = InMemoryUserRepository()
        val saveUser = SaveUser(userRepository)
        val saveUserRequest = SaveUserRequest(
                VALID_NAME,
                VALID_SURNAME,
                VALID_EMAIL,
                VALID_PASSWORD
        )

        val saveUserResponse : Mono<SaveUserResponse> = saveUser.execute(saveUserRequest)
        UUID.fromString(saveUserResponse.block().id)
    }
}