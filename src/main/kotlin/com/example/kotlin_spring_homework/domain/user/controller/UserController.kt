package com.example.kotlin_spring_homework.domain.user.controller

import com.example.kotlin_spring_homework.domain.user.dto.LogInRequest
import com.example.kotlin_spring_homework.domain.user.dto.SignUpRequest
import com.example.kotlin_spring_homework.domain.user.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController (private val userService: UserService){

    @PostMapping("/signup")
    fun signUp(@Valid @RequestBody signUpRequest: SignUpRequest): ResponseEntity<String> {
        userService.signUp(signUpRequest)
        return ResponseEntity("User registered successfully", HttpStatus.OK)
    }

    @PostMapping("/login")
    fun logIn(@RequestBody logInRequest: LogInRequest) : ResponseEntity<String>{
        userService.login(logInRequest)
        return ResponseEntity("Login success", HttpStatus.OK)
    }



}