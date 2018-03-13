package com.fabridinapoli.userapi.domain.user

import java.util.UUID

class User(val id: UserId, val name: String)

data class UserId(val id: UUID)