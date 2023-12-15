package com.example.kotlin_spring_homework.user.service

import com.example.kotlin_spring_homework.user.dto.LogInRequest
import com.example.kotlin_spring_homework.user.dto.SignUpRequest
import com.example.kotlin_spring_homework.user.dto.UserResponse

interface UserService {

    fun signUp(signUpRequest: SignUpRequest) : UserResponse

    fun login(logInRequest: LogInRequest) : String
}