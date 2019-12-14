package com.fabridinapoli.userapi.domain.user

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.Test

class UserShould {

    @Test
    fun `be valid with random user id`() {
        val user = User(NAME, SURNAME, EMAIL, PASSWORD)

        assertThat(EMAIL).isEqualTo(user.email.value)
        assertThat(NAME).isEqualTo(user.name.value)
        assertThat(SURNAME).isEqualTo(user.surname.value)
        assertThat(PASSWORD).isEqualTo(user.password.value)
    }

    @Test
    fun `fail if mail is not valid`() {
        val throwable = catchThrowable {
            User(NAME, SURNAME, NON_VALID_EMAIL, PASSWORD)
        }

        assertThat(throwable).isInstanceOf(EmailNotValidException::class.java)
    }

    companion object {
        private const val NAME = "Fabri"
        private const val SURNAME = "Di Napoli"
        private const val EMAIL = "email@email.com"
        private const val PASSWORD = "somepassword"
        private const val NON_VALID_EMAIL = "non-valid"
    }
}