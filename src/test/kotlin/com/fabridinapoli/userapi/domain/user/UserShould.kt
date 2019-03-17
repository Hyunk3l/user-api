package com.fabridinapoli.userapi.domain.user

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class UserShould {

    @Test
    fun `create a valid user with random user id`() {
        val user = User(NAME, SURNAME, EMAIL, PASSWORD)

        assertThat(EMAIL).isEqualTo(user.email)
        assertThat(NAME).isEqualTo(user.name)
        assertThat(SURNAME).isEqualTo(user.surname)
        assertThat(PASSWORD).isEqualTo(user.password)
    }

    companion object {
        private const val NAME = "Fabri"
        private const val SURNAME = "Di Napoli"
        private const val EMAIL = "email@email.com"
        private const val PASSWORD = "somepassword"
    }
}