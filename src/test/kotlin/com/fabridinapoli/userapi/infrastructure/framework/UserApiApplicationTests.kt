package com.fabridinapoli.userapi.infrastructure.framework

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

@SpringBootTest
@TestPropertySource(properties = [
    "spring.elasticsearch.enabled=false"
])
class UserApiApplicationTests {
    @Test
    fun contextLoads() {
    }
}
