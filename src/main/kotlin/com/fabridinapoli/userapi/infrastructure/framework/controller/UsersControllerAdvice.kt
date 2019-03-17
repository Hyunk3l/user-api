package com.fabridinapoli.userapi.infrastructure.framework.controller

import com.fabridinapoli.userapi.domain.user.EmailNotValidException
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

    @ExceptionHandler(EmailNotValidException::class)
    fun handleValidationExceptions(exception: Exception) : ResponseEntity<ErrorMessages> {
        val messages = listOf(exception.message as String)
        return ResponseEntity(ErrorMessages(messages), HttpStatus.BAD_REQUEST)
    }
}

data class ErrorMessage(val message: String?)

data class ErrorMessages(val messages: List<String>?)