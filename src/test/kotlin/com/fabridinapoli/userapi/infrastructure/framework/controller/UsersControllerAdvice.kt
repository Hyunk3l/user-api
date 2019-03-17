package com.fabridinapoli.userapi.infrastructure.framework.controller

import com.fabridinapoli.userapi.domain.user.UserAlreadyExistsException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.Exception

@ControllerAdvice
class UsersControllerAdvice {

    @ExceptionHandler(UserAlreadyExistsException::class)
    fun handleException(exception: Exception) : ResponseEntity<String> {
        return ResponseEntity("", HttpStatus.CONFLICT)
    }
}