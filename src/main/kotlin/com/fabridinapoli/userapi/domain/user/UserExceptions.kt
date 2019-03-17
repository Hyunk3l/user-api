package com.fabridinapoli.userapi.domain.user

import java.lang.RuntimeException

class UserAlreadyExistsException(message: String?) : RuntimeException(message)