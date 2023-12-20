package com.example.kotlin_spring_homework.domain.user.service

import com.example.kotlin_spring_homework.domain.user.dto.LogInRequest
import com.example.kotlin_spring_homework.domain.user.dto.SignUpRequest
import com.example.kotlin_spring_homework.domain.user.dto.UserResponse

interface UserService {

    fun signUp(signUpRequest: SignUpRequest) : UserResponse

    fun login(logInRequest: LogInRequest) : String
}