package com.fabridinapoli.userapi.domain.user

import com.fabridinapoli.userapi.domain.DomainException
import com.fabridinapoli.userapi.domain.DomainValidationException

class UserAlreadyExistsException(message: String?) : DomainException(message)

class EmailNotValidException(message: String?) : DomainValidationException(message)