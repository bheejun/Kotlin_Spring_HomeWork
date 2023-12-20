package com.example.kotlin_spring_homework.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(value = [UsernameAlreadyExistsException::class])
    fun handleUsernameAlreadyExistsException(e: UsernameAlreadyExistsException): ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [UserNotFoundException::class])
    fun handleUserNotFoundException(e: UserNotFoundException): ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [InvalidPasswordException::class])
    fun handleInvalidPasswordException(e: InvalidPasswordException): ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [UnauthorizedUserException::class])
    fun handleUnauthorizedUserException(e: UnauthorizedUserException): ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [CommentNotFoundException::class, PostNotFoundException::class])
    fun handleResourceNotFoundException(e: RuntimeException): ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.NOT_FOUND)
    }
}