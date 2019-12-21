package com.fabridinapoli.userapi.domain.user

sealed class DomainFailure(open val message: String)

data class EmailNotValid(override val message: String): DomainFailure(message)