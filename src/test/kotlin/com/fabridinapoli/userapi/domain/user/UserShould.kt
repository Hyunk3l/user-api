package com.fabridinapoli.userapi.domain.user

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import kotlin.test.assertTrue

class UserShould {

    @Test
    fun `be valid with random user id`() {
        val user = User
                .createOrErrors(ID, NAME, SURNAME, EMAIL, PASSWORD)

        assertTrue(user.isRight())
        user.map {
            assertThat(EMAIL).isEqualTo(it.email.value)
            assertThat(NAME).isEqualTo(it.name.value)
            assertThat(SURNAME).isEqualTo(it.surname.value)
            assertThat(PASSWORD).isEqualTo(it.password.value)
        }
    }

    @Test
    fun `fail if mail is not valid`() {
        val user = User.createOrErrors(ID, NAME, SURNAME, NON_VALID_EMAIL, PASSWORD)

        assertTrue(user.isLeft())
        user.mapLeft { it[0] is EmailNotValid }
    }

    private companion object {
        private const val ID = "59c7b01a-598d-4ceb-bdab-91f76178b09e"
        private const val NAME = "Fabri"
        private const val SURNAME = "Di Napoli"
        private const val EMAIL = "email@email.com"
        private const val PASSWORD = "somepassword"
        private const val NON_VALID_EMAIL = "non-valid"
    }
}