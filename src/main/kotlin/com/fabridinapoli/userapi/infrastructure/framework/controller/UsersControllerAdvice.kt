package com.fabridinapoli.userapi.infrastructure.framework.controller

import com.fabridinapoli.userapi.domain.user.UserAlreadyExistsException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class UsersControllerAdvice {

    @ExceptionHandler(UserAlreadyExistsException::class)
    fun handleException(exception: Exception) : ResponseEntity<ErrorMessage> {
        val message = exception.message
        return ResponseEntity(ErrorMessage(message), HttpStatus.CONFLICT)
    }
}

data class ErrorMessage(val message: String?)