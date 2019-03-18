package com.fabridinapoli.userapi.domain

open class DomainException(message: String?) : RuntimeException(message)

open class DomainValidationException(message: String?) : DomainException(message)
