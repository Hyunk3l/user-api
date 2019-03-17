package com.fabridinapoli.userapi.domain.user

class UserAlreadyExistsException(message: String?) : RuntimeException(message)

class EmailNotValidException(message: String?) : RuntimeException(message)